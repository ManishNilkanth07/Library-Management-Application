package com.management.library_management_system.DAO;

import com.management.library_management_system.Utils.DBConnection;
import com.management.library_management_system.model.Student;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class StudentDAO {

    private static final String INSERTQUERY = "INSERT INTO student (name, email, password, role, membership_number) VALUES (?, ?, ?, ?, ?)";
    private static final String GETQUERY = "SELECT name, email, password, role, membership_number FROM student WHERE id = ?";
    private static final String UPDATEQUERY = "UPDATE student SET name = ? WHERE id = ?";
    private static final String DELETQUERY = "DELETE FROM student WHERE id = ?";
    private static final String LOGINQUERY = "SELECT * FROM student WHERE membership_number = ? AND password = ?";
    
    private static final Logger LOGGER = Logger.getLogger(StudentDAO.class.getName());

    public int createStudent(Student student) {
        try (Connection connection = DBConnection.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(INSERTQUERY)) {
            
            statement.setString(1, student.getName());
            statement.setString(2, student.getEmail());
            statement.setString(3, student.getPassword());
            statement.setString(4, student.getRole());
            statement.setString(5, getMembershipNumber(student.getName()));
            
            return statement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while creating student", ex);
        }
        return 0;
    }

    public Student getStudentById(int studentId) {
        try (Connection connection = DBConnection.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(GETQUERY)) {

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
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while fetching student by ID", ex);
        }
        return null;
    }

    public int deleteStudentById(int studentId) {
        try (Connection connection = DBConnection.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(DELETQUERY)) {

            statement.setInt(1, studentId);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while deleting student", ex);
        }
        return 0;
    }

    public int updateStudentById(Student student) {
        try (Connection connection = DBConnection.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(UPDATEQUERY)) {

            statement.setString(1, student.getName());
            statement.setInt(2, student.getStudentId());
            return statement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while updating student", ex);
        }
        return 0;
    }

    public Student loginStudent(String membershipNumber, String password) {
        try (Connection connection = DBConnection.getConnection(); 
             PreparedStatement statement = connection.prepareStatement(LOGINQUERY)) {

            statement.setString(1, membershipNumber);
            statement.setString(2, password);

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
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error occurred while student login", ex);
        }
        return null;
    }

    public String getMembershipNumber(String name) {
        int number = (int) (Math.random() * 10000);
        return name.substring(0, Math.min(3, name.length())) + number;
    }
}
