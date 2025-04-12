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

    private static final String INSERTQUERY = "INSERT INTO book (name, author, edition, quantity) VALUES (?, ?, ?, ?)";
    private static final String GETQUERY = "SELECT * FROM book WHERE book_id=?";
    private static final String GETALLQUERY = "SELECT * FROM book";
    private static final String UPDATEQUERY = "UPDATE book SET name=?, author=?, edition=?, quantity=? WHERE book_id=?";
    private static final String DELETQUERY = "DELETE FROM book WHERE book_id=?";
    private static final String SEARCHBOOKBYNAMEORAUTHOR = "SELECT * FROM book WHERE name=? or author=?";
    private static final String GET_BOOK_NAME_BY_BOOK_ID = "SELECT name FROM book WHERE book_id=?";

    private static final Logger LOGGER = Logger.getLogger(BookDAO.class.getName());

    public int createBook(Book book) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(INSERTQUERY)) {

            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getEdition());
            statement.setInt(4, book.getQuantity());

            return statement.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error during book creation", ex);
        }
        return 0;
    }

    public Book getBookById(int bookId) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(GETQUERY)) {

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
            LOGGER.log(Level.SEVERE, "Error during fetching book by ID", ex);
        }
        return null;
    }

    public List<Book> getAllBooks() {
        List<Book> books = new ArrayList<>();
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(GETALLQUERY); ResultSet set = statement.executeQuery()) {

            while (set.next()) {
                Book book = new Book.BookBuilder()
                        .setBookId(set.getInt("book_id"))
                        .setName(set.getString("name"))
                        .setAuthor(set.getString("author"))
                        .setEdition(set.getString("edition"))
                        .setQuantity(set.getInt("quantity"))
                        .build();
                books.add(book);
            }

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error during fetching all books", ex);
        }
        return books;
    }

    public int updateBook(Book book) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(UPDATEQUERY)) {

            statement.setString(1, book.getName());
            statement.setString(2, book.getAuthor());
            statement.setString(3, book.getEdition());
            statement.setInt(4, book.getQuantity());
            statement.setInt(5, book.getBookId());

            return statement.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error during updating book", ex);
        }
        return 0;
    }

    public int deleteBookById(int bookId) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETQUERY)) {

            statement.setInt(1, bookId);
            return statement.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error during deleting book", ex);
        }
        return 0;
    }

    public List<Book> searchBookByNameOrAuthor(String name, String author) {
        List<Book> books = new ArrayList<>();

        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(SEARCHBOOKBYNAMEORAUTHOR)) {
            statement.setString(1, name);
            statement.setString(2, author);

            try (ResultSet set = statement.executeQuery()) {
                while (set.next()) {
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
            LOGGER.log(Level.SEVERE, "Error during searching books", ex);
        }
        return books;
    }

    public String getBookNameById(int returnedBookId) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(GET_BOOK_NAME_BY_BOOK_ID)) {

            statement.setInt(1, returnedBookId);

            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    return set.getString("name");
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error during fetching book name by ID", ex);
        }
        return null;
    }
}
