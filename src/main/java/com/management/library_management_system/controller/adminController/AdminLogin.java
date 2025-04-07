 
package com.management.library_management_system.controller.adminController;
 
import com.management.library_management_system.DAO.AdminDAO;
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

@WebServlet(name = "AdminLogin", urlPatterns = {"/AdminLogin"})
public class AdminLogin extends HttpServlet {

     private final AdminDAO adminDao;

    public AdminLogin() {
        this.adminDao = new AdminDAO();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String membershipNumber = request.getParameter("membershipNumber");
        String password = request.getParameter("password");

        if (Validation.isValidPassword(password) && Validation.isValidMembershipNumber(membershipNumber)) {
            Admin admin = adminDao.loginAdmin(membershipNumber, password);

            if (admin != null) {
                try {
                    response.sendRedirect("adminDashboard.jsp");
                } catch (IOException ex) {
                    Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    response.sendRedirect("adminLogin.jsp?error=Invalid Credentials");
                } catch (IOException ex) {
                    Logger.getLogger(AdminLogin.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
}
