package com.management.library_management_system.DAO;

import com.management.library_management_system.Utils.BookData;
import com.management.library_management_system.Utils.DBConnection;
import com.management.library_management_system.model.Book;
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

    private static final String INSERTQUERY = "INSERT INTO issue (book_id, student_id, issue_date, return_date) VALUES (?, ?, ?, ?)";
    private static final String GETQUERY = "SELECT * FROM issue WHERE issue_id=?";
    private static final String UPDATEQUERY = "UPDATE issue SET book_id = ?, student_id = ?, issue_date = ?, return_date = ? WHERE issue_id=?";
    private static final String DELETQUERY = "DELETE FROM issue WHERE issue_id=?";
    private static final String GETBYSTUDENTID = "SELECT * FROM issue WHERE student_id=?";
    private static final String CHECKBOOKQUANTITY = "SELECT quantity FROM book WHERE book_id=?";
    private static final String MINUSBOOKQUANTITY = "UPDATE book SET quantity = quantity - 1 WHERE book_id=?";
    private static final String PLUSBOOKQUANTITY = "UPDATE book SET quantity = quantity + 1 WHERE book_id=?";
    private static final String GETBOOKIQUERY = "SELECT book_id FROM issue WHERE issue_id=?";
    private static final String GETALLISSUE = "SELECT * FROM issue";

    private static final Logger LOGGER = Logger.getLogger(IssueDAO.class.getName());

    public List<BookData> getAllIssuedBooks(List<Issue> issues) {
        List<BookData> bookData = new ArrayList<>();
        if (issues == null || issues.isEmpty()) {
            return bookData;
        }

        BookDAO bookDao = new BookDAO();
        for (Issue issue : issues) {
            Book book = bookDao.getBookById(issue.getBookId());

            if (book != null) {
                BookData bookDetails = new BookData();
                bookDetails.setIssueId(issue.getIssueId());
                bookDetails.setBookId(book.getBookId());
                bookDetails.setName(book.getName());
                bookDetails.setAuthor(book.getAuthor());
                bookDetails.setEdition(book.getEdition());
                bookDetails.setIssueDate(issue.getIssueDate());
                bookDetails.setReturnDate(issue.getReturnDate());

                bookData.add(bookDetails);
            }
        }
        return bookData;
    }

    public List<Issue> getAllIssueByStudentId(int studentId) {
        List<Issue> issues = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(GETBYSTUDENTID)) {

            statement.setInt(1, studentId);
            try (ResultSet set = statement.executeQuery()) {
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
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return issues;
    }

    public int returnBook(int issueId) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement getBookStmt = connection.prepareStatement(GETBOOKIQUERY)) {

            getBookStmt.setInt(1, issueId);

            try (ResultSet set = getBookStmt.executeQuery()) {
                int bookId = -1;

                if (set.next()) {
                    bookId = set.getInt("book_id");
                }

                if (bookId != -1) {
                    deleteIssue(issueId);
                    try (PreparedStatement updateBookStmt = connection.prepareStatement(PLUSBOOKQUANTITY)) {
                        updateBookStmt.setInt(1, bookId);
                        return updateBookStmt.executeUpdate();
                    }
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
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
                            updateBookStatement.setInt(1, issue.getBookId());
                            updateBookStatement.executeUpdate();
                        }
                        return response;
                    }
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public Issue getIssueByIssueId(int issueId) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(GETQUERY)) {

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
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public int updateIssue(Issue issue) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATEQUERY)) {

            statement.setInt(1, issue.getBookId());
            statement.setInt(2, issue.getStudentId());
            statement.setDate(3, (Date) issue.getIssueDate());
            statement.setDate(4, (Date) issue.getReturnDate());
            statement.setInt(5, issue.getIssueId());

            return statement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return 0;
    }

    public void deleteIssue(int issueId) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETQUERY)) {

            statement.setInt(1, issueId);
            statement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
    }

    public List<Issue> getAllIssues() {
        List<Issue> issueList = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(GETALLISSUE)) {

            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
                    Issue issue = new Issue.IssueBuilder()
                            .setIssueId(set.getInt("issue_id"))
                            .setBookId(set.getInt("book_id"))
                            .setStudentId(set.getInt("student_id"))
                            .setIssueDate(set.getDate("issue_date"))
                            .setReturnDate(set.getDate("return_date"))
                            .build();

                    issueList.add(issue);
                }
                return issueList;
            } catch (SQLException ex) {
                LOGGER.log(Level.SEVERE, null, ex);
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, null, ex);
        }
        return null;
    }
}
