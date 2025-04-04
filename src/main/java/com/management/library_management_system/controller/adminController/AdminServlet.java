package com.management.library_management_system.controller.adminController;

import com.management.library_management_system.DAO.AdminDAO;
import com.management.library_management_system.DAO.ParkingSlotDAO;
import com.management.library_management_system.Utils.Validation;
import com.management.library_management_system.model.Admin;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AdminServlet", urlPatterns = {"/AdminServlet"})
public class AdminServlet extends HttpServlet {

    private final AdminDAO adminDao;

    private final ParkingSlotDAO parkingSlotDao;

    public AdminServlet() {
        this.adminDao = new AdminDAO();
        this.parkingSlotDao = new ParkingSlotDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String action = request.getParameter("action");

        if ("login".equals(action)) {
            loginAdmin(request, response);
        } else if ("addParkingSlot".endsWith(action)) {
            addParkingSlot(request, response);
        }
    }

    private void loginAdmin(HttpServletRequest request, HttpServletResponse response) {

        String membershipNumber = request.getParameter("membershipNumber");
        String password = request.getParameter("password");

        if (Validation.isValidPassword(password) && Validation.isValidMembershipNumber(membershipNumber)) {
            Admin admin = adminDao.loginAdmin(membershipNumber, password);

            if (admin != null) {
                try {
                    response.sendRedirect("adminDashboard.jsp");
                } catch (IOException ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    response.sendRedirect("adminLogin.jsp?error=Invalid Credentials");
                } catch (IOException ex) {
                    Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    private void addParkingSlot(HttpServletRequest request, HttpServletResponse response) {

        String slotNumber = request.getParameter("slotNumber");

        int createParkingSlot = parkingSlotDao.createParkingSlot(slotNumber);

        if (createParkingSlot != 0) {

            try {
                response.sendRedirect("adminDashboard.jsp");
            } catch (IOException ex) {
                Logger.getLogger(AdminServlet.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
    }

}
