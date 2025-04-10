package com.management.library_management_system.controller.bookController;

import com.management.library_management_system.DAO.BookDAO;
import com.management.library_management_system.DAO.IssueDAO;
import com.management.library_management_system.model.Book;
import com.management.library_management_system.model.Issue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BookStatsServlet", urlPatterns = {"/BookStatsServlet"})
public class BookStatsServlet extends HttpServlet {

    private final BookDAO bookDAO;
    private final IssueDAO issueDAO;

    public BookStatsServlet() {
        this.bookDAO = new BookDAO();
        this.issueDAO = new IssueDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("adminId") != null) {

            int totalBooks = -1;
            int issuedBooks = -1;
            List<Book> bookList = bookDAO.getAllBooks();

            List<Issue> issueList = issueDAO.getAllIssues();

            totalBooks = bookList.size();

            issuedBooks = issueList.size();

            response.getWriter().println("total books :" + totalBooks);
            response.getWriter().println("total issed books :" + issuedBooks);
            System.out.println(totalBooks);
            System.out.println(issuedBooks);
            request.setAttribute("totalBooks", totalBooks);
            request.setAttribute("issuedBooks", issuedBooks);

            request.getRequestDispatcher("/adminDashboard.jsp").forward(request, response);
        } else {
            response.sendRedirect("adminLogin.jsp?error=session expired");
        }
    }
}
