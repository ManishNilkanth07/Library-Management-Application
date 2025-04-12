package com.management.library_management_system.controller.renewalController;

import com.management.library_management_system.DAO.IssueDAO;
import com.management.library_management_system.DAO.RenewalDAO;
import com.management.library_management_system.DAO.ReservationDAO;
import com.management.library_management_system.model.Issue;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;

@WebServlet(name = "RenewalServlet", urlPatterns = {"/RenewalServlet"})
public class RenewalServlet extends HttpServlet {

    private final IssueDAO issueDao = new IssueDAO();
    private final ReservationDAO reservationDao = new ReservationDAO();
    private final RenewalDAO renewalDAO = new RenewalDAO();
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("studentId") == null) {
            response.sendRedirect("studentLogin.jsp");
            return;
        }

        int studentId = Integer.parseInt(session.getAttribute("studentId").toString());
        int issueId = Integer.parseInt(request.getParameter("issue_id"));

        Issue issue = issueDao.getIssueByIssueId(issueId);
        if (issue == null || reservationDao.isReservedByAnotherUser(issue.getBookId(), studentId)) {
            response.sendRedirect("studentDashboard.jsp?renewal=failed!");
            return;
        }

        boolean success = issueDao.updateReturnDate(issueId, Date.valueOf(LocalDate.now().plusDays(8)));
        renewalDAO.renewBook(issueId , Date.valueOf(LocalDate.now()));
        response.sendRedirect("studentDashboard.jsp?renewal=" + (success ? "success" : "failed"));
    }
}
