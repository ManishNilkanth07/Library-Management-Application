package com.management.library_management_system.DAO;

import com.management.library_management_system.Utils.DBConnection;
import com.management.library_management_system.model.Issue;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IssueDAO {

    private static final String INSERTQUERY = "INSERT INTO issue (book_id,student_id,issue_date,return_date) VALUES (?,?,?,?)";

    private static final String GETQUERY = "SELECT * FROM issue where id=?";

    private static final String UPDATEQUERY = "UPDATE issue SET book_id = ?, student_id = ?,issue_date = ?,return_date = ? WHERE id=?";

    private static final String DELETQUERY = "DELETE from issue where id=?";

    private static final String GETBYSTUDENTID = "SELECT * FROM issue where student_id=?";

    private static final String CHECKBOOKQUANTITY = "SELECT quantity from book where book_id = ?";

    private static final String MINUSBOOKQUANTITY = "UPDATE book SET quantity = quantity - 1 where book_id = ?";
    
    private static final String PLUSBOOKQUANTITY = "UPDATE book SET quantity = quantity + 1 where book_id = ?";
    
    private static final String GETBOOKIQUERY = "SELECT book_id from issue where issue_Id = ?";

    public List<Issue> getAllIssueByStudentId(int studentId) {
        Connection connection = null;

        PreparedStatement statement = null;

        ResultSet set = null;

        List<Issue> issues = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();

            statement = connection.prepareStatement(GETBYSTUDENTID);

            statement.setInt(1, studentId);
            try {
                set = statement.executeQuery();
                while (set.next()) {
                    Issue issue = new Issue.IssueBuilder()
                            .setIssueId(set.getInt("issue_id"))
                            .setBookId(set.getInt("book_id"))
                            .setStudentId(set.getInt("student_id"))
                            .setIssueDate(set.getDate("issue_date"))
                            .setReturnDate(set.getDate("return_date"))
                            .build();

                    issues.add(issue);
                }
            } catch (SQLException ex) {
                Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, statement, set);
        }

        return issues;
    }
    public int returnBook(int issueId)
    {
        try(Connection connection = DBConnection.getConnection(); PreparedStatement getBookStmt = connection.prepareStatement(GETBOOKIQUERY))
        {
            getBookStmt.setInt(1, issueId);
            
            try(ResultSet set = getBookStmt.executeQuery())
            {
                int bookId = -1;
                
                if(set.next())
                {
                    bookId = set.getInt("book_id");
                }
                
                if(bookId != -1)
                {
                    deletIssue(issueId);
                    try(PreparedStatement updateBookStmt = connection.prepareStatement(PLUSBOOKQUANTITY))
                    {
                        updateBookStmt.setInt(1, bookId);
                        return updateBookStmt.executeUpdate();
                    }
                }
            }
        } 
        catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }
    public int issueBook(Issue issue) throws SQLException {

        try (Connection connection = DBConnection.getConnection(); PreparedStatement checkQuantityStatement = connection.prepareStatement(CHECKBOOKQUANTITY)) {
            checkQuantityStatement.setInt(1, issue.getBookId());

            try (ResultSet set = checkQuantityStatement.executeQuery()) {
                if (set.next() && set.getInt("quantity") > 0) {
                    try (PreparedStatement issueBookStatement = connection.prepareStatement(INSERTQUERY)) {
                        issueBookStatement.setInt(1, issue.getBookId());
                        issueBookStatement.setInt(2, issue.getStudentId());
                        issueBookStatement.setDate(3, (Date) issue.getIssueDate());
                        issueBookStatement.setDate(4, (Date) issue.getReturnDate());
                        
                        int response = issueBookStatement.executeUpdate();
                        
                        try (PreparedStatement updateBookStatement = connection.prepareStatement(MINUSBOOKQUANTITY)) {
                            updateBookStatement.setInt(1,issue.getBookId());
                            updateBookStatement.executeUpdate();
                        }
                        return response;
                    }
 
                }
            }

        }
        catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Issue getIssueByIssueId(int issueId) throws SQLException {
        Connection connection = null;

        PreparedStatement statement = null;

        try {
            connection = DBConnection.getConnection();
            statement = connection.prepareStatement(GETQUERY);
            statement.setInt(1, issueId);

            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    return new Issue.IssueBuilder()
                            .setIssueId(set.getInt("issue_id"))
                            .setBookId(set.getInt("book_id"))
                            .setStudentId(set.getInt("student_id"))
                            .setIssueDate(set.getDate("issue_date"))
                            .setReturnDate(set.getDate("return_date"))
                            .build();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, statement, null);
        }
        return null;
    }

    public int updateIssue(Issue issue) {
        Connection connection = null;

        PreparedStatement statement = null;

        try {
            connection = DBConnection.getConnection();

            statement = connection.prepareStatement(UPDATEQUERY);

            statement.setInt(1, issue.getBookId());
            statement.setInt(2, issue.getStudentId());
            statement.setDate(3, (Date) issue.getIssueDate());
            statement.setDate(4, (Date) issue.getReturnDate());
            statement.setInt(5, issue.getIssueId());

            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, statement, null);
        }
        return 0;

    }

    public int deletIssue(int issueId) {
        Connection connection = null;

        PreparedStatement statement = null;

        try {
            connection = DBConnection.getConnection();

            statement = connection.prepareStatement(DELETQUERY);

            statement.setInt(1, issueId);
        } catch (SQLException ex) {
            Logger.getLogger(IssueDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, statement, null);
        }
        return 0;
    }
}
