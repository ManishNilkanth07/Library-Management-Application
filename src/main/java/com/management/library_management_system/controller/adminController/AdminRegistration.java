package com.management.library_management_system.controller.adminController;

import com.management.library_management_system.DAO.AdminDAO;
import com.management.library_management_system.Utils.Validation;
import com.management.library_management_system.controller.studentController.StudentRegistration;
import com.management.library_management_system.model.Admin;

import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "AdminRegistration", urlPatterns = {"/AdminRegistration"})
public class AdminRegistration extends HttpServlet {

    private final AdminDAO adminDao;

    public AdminRegistration() {
        this.adminDao = new AdminDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String name = request.getParameter("name");

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        String role = request.getParameter("role");

        String address = request.getParameter("address");

        String libraryName = request.getParameter("libraryName");

        if (Validation.isValidName(name) && Validation.isValidEmail(email) && Validation.isValidPassword(password)) {
            try {
                Admin admin = new Admin.AdminBuilder()
                        .setName(name)
                        .setEmail(email)
                        .setPassword(password)
                        .setAddress(address)
                        .setLibraryName(libraryName)
                        .setRole("admin".equals(role) ? role : "admin")
                        .build();
                adminDao.createAdmin(admin);
                response.sendRedirect("index.html");
            } 
            catch (IOException e) {
                response.getWriter().print(e);
                Logger.getLogger(StudentRegistration.class.getName()).log(Level.SEVERE, null, e);
            }

        }

    }

}
