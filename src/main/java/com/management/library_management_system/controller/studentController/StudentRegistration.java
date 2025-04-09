package com.management.library_management_system.controller.studentController;

import com.management.library_management_system.DAO.StudentDAO;
import com.management.library_management_system.Utils.Validation;
import com.management.library_management_system.model.Student;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "StudentRegistration", urlPatterns = {"/StudentRegistration"})
public class StudentRegistration extends HttpServlet {

    private final StudentDAO studentDao;

    public StudentRegistration() {
        this.studentDao = new StudentDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        if (Validation.isValidName(name) && Validation.isValidEmail(email) && Validation.isValidPassword(password)) {
            Student student = new Student.StudentBuilder()
                    .setName(name)
                    .setEmail(email)
                    .setPassword(password)
                    .setRole("student".equals(role) ? role : "student")
                    .build();
            int studentId = studentDao.createStudent(student);

            if (studentId != 0) {
                try {
                    response.sendRedirect("studentLogin.jsp");
                } catch (IOException ex) {
                    Logger.getLogger(StudentRegistration.class.getName()).log(Level.SEVERE, "Error redirecting to student login page", ex);
                }
            } else {
                response.sendRedirect("studentRegistration.jsp");
            }
        } else {
            response.sendRedirect("studentRegistration.jsp?error=Registration failed!");
        }
    }
}
