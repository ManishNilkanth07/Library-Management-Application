package com.management.library_management_system.controller.bookController;

import com.management.library_management_system.DAO.BookDAO;
import com.management.library_management_system.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "GetAllBooksServlet", urlPatterns = {"/GetAllBooksServlet"})
public class GetAllBooksServlet extends HttpServlet {

    private final BookDAO bookDao;

    public GetAllBooksServlet() {
        this.bookDao = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Book> bookList = null;

        try {
            bookList = bookDao.getAllBooks();
        } 
        catch (Exception e)
        {
            Logger.getLogger(GetAllBooksServlet.class.getName()).log(Level.SEVERE, "Error fetching books", e);
            response.sendRedirect("adminDashboard.jsp?error=Failed to fetch books");
            return;
        }

        if (bookList != null && !bookList.isEmpty()) 
        {
            request.setAttribute("bookList", bookList);
            request.getRequestDispatcher("viewBooks.jsp").forward(request, response);
        }
        else 
        {
            response.sendRedirect("adminDashboard.jsp?error=No books available");
        }
    }
}
