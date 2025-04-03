package com.management.librarymanagement.Utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConnection {

    private DBConnection() {
    }

    public static Connection getConnection() {
        Connection connection = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/library_management";
            String USERNAME = "root";
            String PASSWORD = "Manish@626474";

            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return connection;
    }

    public static void closeConnection(Connection connection, PreparedStatement statement, ResultSet resultSet) {
        try 
        {
            if (resultSet != null) resultSet.close();
        }
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try 
        {
            if (statement != null) statement.close();
        } 
        catch (SQLException ex)
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
        try 
        {
            if (connection != null) connection.close();
        } 
        catch (SQLException ex) 
        {
            Logger.getLogger(DBConnection.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
