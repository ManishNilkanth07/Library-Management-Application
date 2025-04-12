package com.management.library_management_system.controller.reservationController;

import com.management.library_management_system.DAO.ReservationDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

import java.io.IOException;

@WebServlet(name = "ReservationServlet", urlPatterns = {"/ReservationServlet"})
public class ReservationServlet extends HttpServlet {

    private final ReservationDAO reservationDao;

    public ReservationServlet() {
        this.reservationDao = new ReservationDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("studentId") == null) {
            response.sendRedirect("studentLogin.jsp?error=sessionExpired");
            return;
        }

        int studentId = Integer.parseInt(session.getAttribute("studentId").toString());
        String bookIdParam = request.getParameter("book_id");

        int bookId = Integer.parseInt(bookIdParam);

        if (reservationDao.hasAlreadyReserved(studentId, bookId)) {
            response.sendRedirect("studentDashboard.jsp?error=alreadyReserved");
            return;
        }

        boolean success = reservationDao.createReservation(studentId, bookId);
        if (success) {
            response.sendRedirect("studentDashboard.jsp?reserved=1");
        } else {
            response.sendRedirect("studentDashboard.jsp?error=reserveFailed");
        }
    }
}
