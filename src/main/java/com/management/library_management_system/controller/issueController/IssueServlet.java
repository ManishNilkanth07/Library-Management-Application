package com.management.library_management_system.controller.issueController;

import com.management.library_management_system.DAO.IssueDAO;
import com.management.library_management_system.model.Issue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "IssueServlet", urlPatterns = {"/IssueServlet"})
public class IssueServlet extends HttpServlet {

    private final IssueDAO issueDao;

    public IssueServlet() {
        this.issueDao = new IssueDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null && session.getAttribute("studentId") != null) {
            String studentIdStr = session.getAttribute("studentId").toString();

            int studentId = Integer.parseInt(studentIdStr);
            int bookId = Integer.parseInt(request.getParameter("book_id"));

            LocalDate issueLocalDate = LocalDate.now();
            LocalDate returnLocalDate = issueLocalDate.plusDays(8);

            Date issueDate = Date.valueOf(issueLocalDate);
            Date returnDate = Date.valueOf(returnLocalDate);

            Issue issue = new Issue.IssueBuilder()
                    .setBookId(bookId)
                    .setStudentId(studentId)
                    .setIssueDate(issueDate)
                    .setReturnDate(returnDate)
                    .build();

            try {
                issueDao.issueBook(issue);
                response.sendRedirect("studentDashboard.jsp");
            } catch (SQLException ex) {
                Logger.getLogger(IssueServlet.class.getName()).log(Level.SEVERE, "Error while issuing book to student with ID: " + studentId, ex);
                response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "An error occurred while processing your request.");
            }
        }
    }
}
