package com.management.library_management_system.controller.adminController;
 
import com.management.library_management_system.DAO.AdminDAO;
import com.management.library_management_system.model.Admin;
 
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
 

@WebServlet(name = "AdminRegistration", urlPatterns = {"/AdminRegistration"})
public class AdminRegistration extends HttpServlet {

    AdminDAO adminDao = new AdminDAO();
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String name = request.getParameter("name");

        String email = request.getParameter("email");

        String password = request.getParameter("password");

        String role = request.getParameter("role");
        
        String address = request.getParameter("address");
        
        String libraryName = request.getParameter("libraryName");
        
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

}
