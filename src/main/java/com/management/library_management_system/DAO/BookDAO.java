package com.management.library_management_system.DAO;

import com.management.library_management_system.Utils.DBConnection;
import com.management.library_management_system.model.Book;
 
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BookDAO {

    private static final String INSERTQUERY = "INSERT INTO book (name,author,edition,quantity) VALUES (?,?,?,?)";

    private static final String GETQUERY = "SELECT * FROM book where id=?";

    private static final String GETALLQUERY = "SELECT * FROM book";

    private static final String UPDATEQUERY = "UPDATE book SET name=?,author=?,edition=?,quantity=? WHERE id=?";

    private static final String DELETQUERY = "DELETE from book where id=?";

    public int createBook(Book book) {
        Connection connection = null;

        PreparedStatement statement = null;

        try {
            connection = DBConnection.getConnection();

            statement = connection.prepareStatement(INSERTQUERY);

            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getEdition());
            statement.setInt(4, book.getQuantity());
            statement.setInt(5, book.getBookId());

            statement.executeUpdate();

        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, statement, null);
        }
        return 0;
    }

    public Book getBookById(int bookId) {
        Connection connection = null;

        PreparedStatement statement = null;
       
        try {
            connection = DBConnection.getConnection();

            statement = connection.prepareStatement(GETQUERY);

            
            statement.setInt(1, bookId);
            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    return new Book.BookBuilder()
                            .setBookId(set.getInt("book_id"))
                            .setName(set.getString("name"))
                            .setAuthor(set.getString("author"))
                            .setEdition(set.getString("edition"))
                            .setQuantity(set.getInt("quantity"))
                            .build();
                }
            }
        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, statement , null);
        }
        return null;
    }

    public List<Book> getAllBooks() {
        Connection connection = null;

        PreparedStatement statement = null;

        List<Book> books = new ArrayList<>();

        try {
            connection = DBConnection.getConnection();

            statement = connection.prepareStatement(GETALLQUERY);

            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) 
                {
                  Book book = new Book.BookBuilder()
                            .setBookId(set.getInt("book_id"))
                            .setName(set.getString("name"))
                            .setAuthor(set.getString("author"))
                            .setEdition(set.getString("edition"))
                            .setQuantity(set.getInt("quantity"))
                            .build();
                  books.add(book);
                }
                
            }

        } catch (SQLException ex) {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.closeConnection(connection, statement, null);
        }
        return books;
    }
    
    public int updateBook(Book book)
    {
        Connection connection = null;
        
        PreparedStatement statement = null;
        
        try
        {
            connection = DBConnection.getConnection();
            
            statement = connection.prepareStatement(UPDATEQUERY);
            
            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getEdition());
            statement.setInt(4, book.getQuantity());
            statement.setInt(5, book.getBookId());
            
            return statement.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            DBConnection.closeConnection(connection, statement, null);
        }
        return 0;
    }
    
    public int deleteBookById(int bookId)
    {
        Connection connection = null;
        
        PreparedStatement statement = null;
        
        try
        {
            connection = DBConnection.getConnection();
            
            statement = connection.prepareStatement(DELETQUERY);
            
            statement.setInt(1, bookId);
            return statement.executeUpdate();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(BookDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            DBConnection.closeConnection(connection, statement, null);
        }
        return 0;
    }
}
