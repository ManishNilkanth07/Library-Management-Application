package com.management.library_management_system.DAO;

import com.management.library_management_system.model.Student;
import com.management.librarymanagement.Utils.DBConnection;
 
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {

    private static final String INSERTQUERY = "INSERT INTO student (name,email,password,role,membership_number) VALUES (?,?,?,?,?)";

    private static final String GETQUERY = "SELECT name,email,password,role,membership_number FROM student where id=?";

    private static final String UPDATEQUERY = "UPDATE student SET name=? WHERE id=?";

    private static final String DELETQUERY = "DELETE from student where id=?";

    public int createStudent(Student student) {
        
        Connection connection = null;
        PreparedStatement statement = null;
        
        try  
        {
            connection = DBConnection.getConnection();
            
            statement = connection.prepareStatement(INSERTQUERY);
            
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPassword());
            statement.setString(4, student.getRole());
            statement.setString(5, getMembershipNumber(student.getName()));
            
            int studentId = statement.executeUpdate();
            return studentId;
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            DBConnection.closeConnection(connection , statement, null);
        }
        return 0;
    }

    public Student getStudentById(int studentId){
        
        Connection connection = null;
        
        PreparedStatement statement = null;
        
        
        
        try  
        {
            connection = DBConnection.getConnection();
            
            statement = connection.prepareStatement(GETQUERY);
            
            statement.setInt(1, studentId);

            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    return new Student.StudentBuilder()
                            .setStudentId(set.getInt("student_id"))
                            .setName(set.getString("name"))
                            .setEmail(set.getString("email"))
                            .setPassword(set.getString("password"))
                            .setRole(set.getString("role"))
                            .setMembershipNumber(set.getString("membership_number"))
                            .build();
                }
            }
        }
        catch (SQLException ex)
        {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            DBConnection.closeConnection(connection , statement , null);
        }
        return null;
    }

    public int deleteStudentById(int studentId){
        
        Connection connection = null;
        PreparedStatement statement = null;
        try
        {
            connection = DBConnection.getConnection();
            
            statement = connection.prepareStatement(DELETQUERY);
            statement.setInt(1, studentId);
            return statement.executeUpdate();
        }
         catch (SQLException ex)
        {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            DBConnection.closeConnection(connection , statement, null);
        }
        return 0;
    }

    public int updateStudentById(Student student){
        Connection connection = null;
        PreparedStatement statement = null;
        try  
        {
            connection = DBConnection.getConnection();
            
            statement = connection.prepareStatement(UPDATEQUERY);
            
            statement.setString(1, student.getName());
            statement.setInt(2, student.getStudentId());
            return statement.executeUpdate();
        }
        catch (SQLException ex)
        {
            Logger.getLogger(StudentDAO.class.getName()).log(Level.SEVERE, null, ex);
        } 
        finally
        {
            DBConnection.closeConnection(connection , statement, null);
        }
        return 0;
    }
    
    public String getMembershipNumber(String name)
    {
        int number = (int) (Math.random() * 10000);
        return name.substring(0, Math.min(3, name.length())) + number;
    }
}
