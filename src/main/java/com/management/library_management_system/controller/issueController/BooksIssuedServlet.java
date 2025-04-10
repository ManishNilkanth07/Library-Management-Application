package com.management.library_management_system.controller.issueController;

import com.management.library_management_system.DAO.IssueDAO;
import com.management.library_management_system.Utils.BookData;
import com.management.library_management_system.model.Issue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.util.List;

@WebServlet(name = "BooksIssuedServlet", urlPatterns = {"/BooksIssuedServlet"})
public class BooksIssuedServlet extends HttpServlet {

    private final IssueDAO issueDao;

    public BooksIssuedServlet() {
        this.issueDao = new IssueDAO();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("studentId") != null) {
            String id = session.getAttribute("studentId").toString();
            int studentId = Integer.parseInt(id);

            try {
                List<Issue> issues = issueDao.getAllIssueByStudentId(studentId);
                List<BookData> issuedBooks = issueDao.getAllIssuedBooks(issues);
                request.setAttribute("issuedBooks", issuedBooks);

                String page = request.getParameter("page");
                if ("renew".equals(page)) {
                    request.getRequestDispatcher("renewBook.jsp").forward(request, response);
                } else {
                    request.getRequestDispatcher("booksIssued.jsp").forward(request, response);
                }

            } catch (NumberFormatException e) {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid student ID format.");
            }
        } else {
            response.sendRedirect("studentLogin.jsp?error=session expired");
        }
    }
}
