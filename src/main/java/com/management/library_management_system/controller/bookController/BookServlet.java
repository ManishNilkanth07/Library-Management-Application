 
package com.management.library_management_system.controller.bookController;

import com.management.library_management_system.DAO.BookDAO;
import com.management.library_management_system.model.Book;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

 
@WebServlet(name = "BookServlet", urlPatterns = {"/BookServlet"})
public class BookServlet extends HttpServlet {

    private final BookDAO bookDao;
    
    public BookServlet()
    {
        this.bookDao = new BookDAO();
    }
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
        String action = request.getParameter("action");
        
        
        if(null != action)
        switch (action) {
            case "addBook":
                addBook(request,response);
                break;
            case "editBook":
                editBook(request,response);
                break;
            case "deletBook":
                deleteBook(request,response);
                break;
            default:
                break;
        }
    }

    private void editBook(HttpServletRequest request, HttpServletResponse response) {
         try {
            int bookId = Integer.parseInt(request.getParameter("book_id"));
             
            String name = request.getParameter("name");
            
            String author = request.getParameter("author");
            
            String edition = request.getParameter("edition");
            
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            Book book = new Book.BookBuilder()
                    .setBookId(bookId)
                    .setName(name)
                    .setAuthor(author)
                    .setEdition(edition)
                    .setQuantity(quantity)
                    .build();
            
            bookDao.updateBook(book);
            response.sendRedirect("adminDashboard.jsp");
        } catch (IOException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void deleteBook(HttpServletRequest request, HttpServletResponse response) {
        
        try
        {
            int bookId = Integer.parseInt(request.getParameter("book_id"));
            
            bookDao.deleteBookById(bookId);
            response.sendRedirect("adminDashboard.jsp");
        }catch (IOException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private void addBook(HttpServletRequest request, HttpServletResponse response) {
        
        try {
            String name = request.getParameter("name");
            
            String author = request.getParameter("author");
            
            String edition = request.getParameter("edition");
            
            int quantity = Integer.parseInt(request.getParameter("quantity"));
            
            Book book = new Book.BookBuilder()
                    .setName(name)
                    .setAuthor(author)
                    .setEdition(edition)
                    .setQuantity(quantity)
                    .build();
            
            bookDao.createBook(book);
            response.sendRedirect("adminDashboard.jsp");
        } catch (IOException ex) {
            Logger.getLogger(BookServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    

    
}
