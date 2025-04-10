package com.management.library_management_system.controller.bookController;

import com.management.library_management_system.DAO.BookDAO;
import com.management.library_management_system.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "BookServlet", urlPatterns = {"/BookServlet"})
public class BookServlet extends HttpServlet {

    private final BookDAO bookDao;

    public BookServlet() {
        this.bookDao = new BookDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if (action != null) {
            switch (action) {
                case "addBook":
                    addBook(request, response);
                    break;
                case "editBook":
                    editBook(request, response);
                    break;
                case "deleteBook":
                    deleteBook(request, response);
                    break;
                default:
                    break;
            }
        } else {
            response.sendRedirect("adminDashboard.jsp?error=Invalid action");
        }
    }

    private void editBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int bookId = Integer.parseInt(request.getParameter("book_id"));
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String edition = request.getParameter("edition");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            Book book = new Book.BookBuilder()
                    .setBookId(bookId)
                    .setName(name)
                    .setAuthor(author)
                    .setEdition(edition)
                    .setQuantity(quantity)
                    .build();

            int updateBook = bookDao.updateBook(book);
            if (updateBook != 0) {
                response.sendRedirect("adminDashboard.jsp?success=Book updated successfully");
            } else {
                response.sendRedirect("adminDashboard.jsp?error=Failed to update book");
            }

        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, "Error updating book", ex);
            response.sendRedirect("adminDashboard.jsp?error=Failed to update book");
        }
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            int bookId = Integer.parseInt(request.getParameter("book_id"));
            int deleteBook = bookDao.deleteBookById(bookId);
            if (deleteBook != 0) {
                response.sendRedirect("adminDashboard.jsp?success=Book deleted successfully");
            } else {
                response.sendRedirect("adminDashboard.jsp?error=Failed to delete book");
            }
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, "Error deleting book", ex);
            response.sendRedirect("adminDashboard.jsp?error=Failed to delete book");
        }
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
        try {
            String name = request.getParameter("name");
            String author = request.getParameter("author");
            String edition = request.getParameter("edition");
            int quantity = Integer.parseInt(request.getParameter("quantity"));

            Book book = new Book.BookBuilder()
                    .setName(name)
                    .setAuthor(author)
                    .setEdition(edition)
                    .setQuantity(quantity)
                    .build();

            int bookId = bookDao.createBook(book);
            if (bookId != 0) {
                response.sendRedirect("adminDashboard.jsp?success=Book added successfully");
            } else {
                response.sendRedirect("adminDashboard.jsp?error=Failed to add book");
            }
        } catch (IOException | NumberFormatException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, "Error adding book", ex);
            response.sendRedirect("adminDashboard.jsp?error=Failed to add book");
        }
    }
}
