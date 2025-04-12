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
import java.util.stream.Collectors;

@WebServlet(name = "GetAllBooksServlet", urlPatterns = {"/GetAllBooksServlet"})
public class GetAllBooksServlet extends HttpServlet {

    private final BookDAO bookDao;

    public GetAllBooksServlet() {
        this.bookDao = new BookDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        List<Book> bookList = null;
        bookList = bookDao.getAllBooks();

        if (bookList != null && !bookList.isEmpty()) {
            String page = request.getParameter("page");

            if ("issue".equals(page)) {

                List<Book> issueBooks = bookList.stream()
                        .filter(book -> book.getQuantity() > 0)
                        .collect(Collectors.toList());
                request.setAttribute("bookList", issueBooks);
                request.getRequestDispatcher("issueBook.jsp").forward(request, response);
            }
            else if ("reserve".equals(page)) {

                List<Book> reserveBooks = bookList.stream()
                        .filter(book -> book.getQuantity() == 0)
                        .collect(Collectors.toList());
                request.setAttribute("bookList", reserveBooks);
                request.getRequestDispatcher("reserveBook.jsp").forward(request, response);
            }
            else {
                request.setAttribute("bookList", bookList);
                request.getRequestDispatcher("viewBooks.jsp").forward(request, response);
            }
        }
        else {
            response.sendRedirect("adminDashboard.jsp?error=No books available");
        }
    }
}
