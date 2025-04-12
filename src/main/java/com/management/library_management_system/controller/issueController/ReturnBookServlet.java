package com.management.library_management_system.controller.issueController;

import com.management.library_management_system.DAO.BookDAO;
import com.management.library_management_system.DAO.IssueDAO;
import com.management.library_management_system.DAO.ReservationDAO;
import com.management.library_management_system.DAO.StudentDAO;
import com.management.library_management_system.Utils.Email;
import com.management.library_management_system.model.Issue;
import com.management.library_management_system.model.Reservation;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

@WebServlet(name = "ReturnBookServlet", urlPatterns = {"/ReturnBookServlet"})
public class ReturnBookServlet extends HttpServlet {

    private final IssueDAO issueDao;
    private final ReservationDAO reservationDao;
    private final BookDAO bookDao;
    private final StudentDAO studentDAO;

    public ReturnBookServlet() {
        this.issueDao = new IssueDAO();
        this.reservationDao = new ReservationDAO();
        this.bookDao = new BookDAO();
        this.studentDAO = new StudentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            int issueId = Integer.parseInt(request.getParameter("issue_id"));
            int returnedBookId = issueDao.returnBook(issueId);

            List<Reservation> reservations = reservationDao.getReservationsByBookId(returnedBookId);

            if (!reservations.isEmpty()) {
                Reservation reservation = reservations.get(0);

                LocalDate issueDate = LocalDate.now();
                LocalDate returnDate = issueDate.plusDays(8);

                Issue autoIssued = new Issue.IssueBuilder()
                        .setBookId(returnedBookId)
                        .setStudentId(reservation.getStudentId())
                        .setIssueDate(Date.valueOf(issueDate))
                        .setReturnDate(Date.valueOf(returnDate))
                        .build();

                issueDao.issueBook(autoIssued);
                reservationDao.deleteReservation(reservation.getId());

                String emailAddress = studentDAO.getStudentEmailById(reservation.getStudentId());
                String bookName = bookDao.getBookNameById(returnedBookId);

                String subject = " Book Issued: " + bookName;
                String message = "Dear Student,\n\n" +
                        "The book you reserved has been issued to your account.\n\n" +
                        " Book Name: " + bookName + "\n" +
                        " Issue Date: " + issueDate + "\n" +
                        " Return Date: " + returnDate + "\n\n" +
                        "Please make sure to return the book on or before the return date to avoid fines.\n\n" +
                        "Thank you,\nLibrary Management System";

                Email.sendEmail(emailAddress, subject, message);
                response.sendRedirect("studentDashboard.jsp?success=return successfully");
            }
            else
            {
                response.sendRedirect("studentDashboard.jsp?error=Return+Failed");
            }


        } catch (Exception e) {
            e.printStackTrace();
            response.sendRedirect("studentDashboard.jsp?error=Return+Failed");
        }
    }
}
