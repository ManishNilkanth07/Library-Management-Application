package com.management.library_management_system.controller.ParkingSlotController;

import com.management.library_management_system.DAO.ParkingSlotDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ParkingSlotServlet", urlPatterns = {"/ParkingSlotServlet"})
public class ParkingSlotServlet extends HttpServlet {

    private final ParkingSlotDAO parkingSlotDao;

    public ParkingSlotServlet() {
        parkingSlotDao = new ParkingSlotDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");

        if ("addSlot".equals(action)) 
        {
            addParkingSlot(request, response);
        } 
        else if ("deleteSlot".equals(action)) 
        {
            deleteParkingSlot(request, response);
        }
    }

    private void addParkingSlot(HttpServletRequest request, HttpServletResponse response) throws IOException{
        String slotNumber = request.getParameter("slotNumber");
        try {
            parkingSlotDao.createParkingSlot(slotNumber);
            response.sendRedirect("adminDashboard.jsp");
        }
        catch (IOException ex) {
            Logger.getLogger(ParkingSlotServlet.class.getName()).log(Level.SEVERE, "Error creating parking slot with slot number: " + slotNumber, ex);
            response.sendRedirect("errorPage.jsp");
        }
    }

    private void deleteParkingSlot(HttpServletRequest request, HttpServletResponse response) {
        try 
        {
            int slotId = Integer.parseInt(request.getParameter("slotId"));
            parkingSlotDao.deleteParkingSlot(slotId);
            response.sendRedirect("adminDashboard.jsp");
        } 
        catch (NumberFormatException | IOException ex) {
            Logger.getLogger(ParkingSlotServlet.class.getName()).log(Level.SEVERE, "Invalid slot ID format", ex);
            try {
                response.sendRedirect("errorPage.jsp");
            } catch (IOException ex1) {
                Logger.getLogger(ParkingSlotServlet.class.getName()).log(Level.SEVERE, "faild to redirect error page", ex1);
            }
        }
    }
}
