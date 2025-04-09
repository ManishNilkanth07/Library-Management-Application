package com.management.library_management_system.controller.studentController;

import com.management.library_management_system.DAO.StudentDAO;
import com.management.library_management_system.Utils.Validation;
import com.management.library_management_system.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "StudentLogin", urlPatterns = {"/StudentLogin"})
public class StudentLogin extends HttpServlet {

    private final StudentDAO studentDao;

    public StudentLogin() {
        this.studentDao = new StudentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String membershipNumber = request.getParameter("membershipNumber");
        String password = request.getParameter("password");

        if (Validation.isValidPassword(password) && Validation.isValidMembershipNumber(membershipNumber)) 
        {
            Student student = studentDao.loginStudent(membershipNumber, password);

            if (student != null) 
            {
                HttpSession session = request.getSession(true);
                session.setAttribute("studentId", student.getStudentId());
                try
                {
                    response.sendRedirect("studentDashboard.jsp");
                }
                catch (IOException ex) {
                    Logger.getLogger(StudentLogin.class.getName()).log(Level.SEVERE, "Error redirecting to dashboard", ex);
                }
            } 
            else
            {
                try 
                {
                    response.sendRedirect("studentLogin.jsp?error=Invalid Credentials");
                } 
                catch (IOException ex)
                {
                    Logger.getLogger(StudentLogin.class.getName()).log(Level.SEVERE, "Error during login", ex);
                }
            }
        }
    }
}
