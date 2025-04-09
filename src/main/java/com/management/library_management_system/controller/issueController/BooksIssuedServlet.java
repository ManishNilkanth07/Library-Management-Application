package com.management.library_management_system.controller.issueController;

import com.management.library_management_system.DAO.IssueDAO;
import com.management.library_management_system.Utils.BookData;
import com.management.library_management_system.model.Issue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
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
        String studentIdParam = request.getParameter("studentId");

        if (studentIdParam != null && !studentIdParam.trim().isEmpty()) 
        {
            try {
                int studentId = Integer.parseInt(studentIdParam);

                List<Issue> issues = issueDao.getAllIssueByStudentId(studentId);
                List<BookData> issuedBooks = issueDao.getAllIssuedBooks(issues);

                request.setAttribute("issuedBooks", issuedBooks);
                request.getRequestDispatcher("booksIssued.jsp").forward(request, response);
            } 
            catch (NumberFormatException e) 
            {
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid student ID format.");
            }
        } 
        else 
        {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Student ID is required.");
        }
    }
}
