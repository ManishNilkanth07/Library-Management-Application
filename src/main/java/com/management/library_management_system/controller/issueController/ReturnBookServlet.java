 
package com.management.library_management_system.controller.issueController;
 
import com.management.library_management_system.DAO.IssueDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet(name = "ReturnBookServlet", urlPatterns = {"/ReturnBookServlet"})
public class ReturnBookServlet extends HttpServlet {

    private final IssueDAO issueDao;
    
    public ReturnBookServlet()
    {
        this.issueDao = new IssueDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
      
        int issueId = Integer.parseInt(request.getParameter("issue_id"));
        issueDao.returnBook(issueId);
        response.sendRedirect("studentDashboard.jsp");
    }

    
}
