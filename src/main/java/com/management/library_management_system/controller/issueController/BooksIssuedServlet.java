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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
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

                List<BookData> booksWithTwoDaysLeft = new ArrayList<>();
                LocalDate today = LocalDate.now();

                for (BookData book : issuedBooks) {
                    Date returnDate = book.getReturnDate();
                    if (returnDate != null) {
                        LocalDate returnLocalDate = new java.sql.Date(returnDate.getTime()).toLocalDate();
                        if (!returnLocalDate.isBefore(today) && !returnLocalDate.isAfter(today.plusDays(2))) {
                            booksWithTwoDaysLeft.add(book);
                        }
                    }
                }


                String page = request.getParameter("page");
                if ("renew".equals(page)) {
                    request.setAttribute("issuedBooks", booksWithTwoDaysLeft);
                    request.getRequestDispatcher("renewBook.jsp").forward(request, response);
                } else {
                    request.setAttribute("issuedBooks", issuedBooks);
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
