package com.management.library_management_system.controller.renewalController;

import com.management.library_management_system.DAO.RenewalDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "RenewalServlet", urlPatterns = {"/RenewalServlet"})
public class RenewalServlet extends HttpServlet {

    private final RenewalDAO renewalDao;

    public RenewalServlet() {
        this.renewalDao = new RenewalDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        try {
            int issueId = Integer.parseInt(request.getParameter("issue_id"));
            LocalDate localDate = LocalDate.now();
            Date renewalDate = Date.valueOf(localDate);

            renewalDao.renewBook(issueId, renewalDate);

            response.sendRedirect("studentDashboard.jsp?success=book renewed successfully");

        } catch (IOException ex) {
            Logger.getLogger(RenewalServlet.class.getName()).log(Level.SEVERE, "An error occurred while renewing the book", ex);
        }
    }
}
