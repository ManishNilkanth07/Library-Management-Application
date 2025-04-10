package com.management.library_management_system.controller.bookController;

import com.management.library_management_system.DAO.BookDAO;
import com.management.library_management_system.controller.renewalController.RenewalServlet;
import com.management.library_management_system.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "SearchBooks", urlPatterns = {"/SearchBooks"})
public class SearchBooks extends HttpServlet {

    private final BookDAO bookDAO;

    public SearchBooks() {
        this.bookDAO = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("studentId") != null) {
            String name = request.getParameter("name");
            String author = request.getParameter("author");

            List<Book> books = bookDAO.searchBookByNameOrAuthor(name, author);

            try {

                if (books != null) {
                    request.setAttribute("books", books);
                    request.getRequestDispatcher("search.jsp").forward(request, response);
                } else {
                    response.sendRedirect("search.jsp?NotFound=books not found");
                }
            } catch (IOException ex) {
                Logger.getLogger(RenewalServlet.class.getName()).log(Level.SEVERE, "An error occurred while searching the book", ex);
            }
        }
    }
}
