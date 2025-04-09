package com.management.library_management_system.DAO;

import com.management.library_management_system.Utils.DBConnection;
import com.management.library_management_system.model.Admin;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AdminDAO {

    private static final String INSERTQUERY = "INSERT INTO admin (name, email, password, role, address, membership_number, library_name) VALUES (?, ?, ?, ?, ?, ?, ?)";
    private static final String GETQUERY = "SELECT admin_id, name, email, password, role, address, membership_number, library_name FROM admin WHERE id=?";
    private static final String UPDATEQUERY = "UPDATE admin SET name=?, address=?, library_name=? WHERE id=?";
    private static final String DELETQUERY = "DELETE FROM admin WHERE id=?";
    private static final String LOGINQUERY = "SELECT * FROM admin WHERE membership_number=? AND password=?";

    private static final Logger LOGGER = Logger.getLogger(AdminDAO.class.getName());

    public int createAdmin(Admin admin) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(INSERTQUERY)) {

            statement.setString(1, admin.getName());
            statement.setString(2, admin.getEmail());
            statement.setString(3, admin.getPassword());
            statement.setString(4, admin.getRole());
            statement.setString(5, admin.getAddress());
            statement.setString(6, getMembershipNumber(admin.getName()));
            statement.setString(7, admin.getLibraryName());

            return statement.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error during creating admin", ex);
        }
        return 0;
    }

    public Admin loginAdmin(String membershipNumber, String password) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(LOGINQUERY)) {

            statement.setString(1, membershipNumber);
            statement.setString(2, password);

            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    return new Admin.AdminBuilder()
                            .setAdminId(set.getInt("admin_id"))
                            .setName(set.getString("name"))
                            .setEmail(set.getString("email"))
                            .setPassword(set.getString("password"))
                            .setRole(set.getString("role"))
                            .setLibraryName(set.getString("library_name"))
                            .setMembershipNumber(set.getString("membership_number"))
                            .setAddress(set.getString("address"))
                            .build();
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error during admin login", ex);
        }
        return null;
    }

    public Admin getAdminById(int adminId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(GETQUERY)) {

            statement.setInt(1, adminId);

            try (ResultSet set = statement.executeQuery()) {
                if (set.next()) {
                    return new Admin.AdminBuilder()
                            .setAdminId(set.getInt("admin_id"))
                            .setName(set.getString("name"))
                            .setEmail(set.getString("email"))
                            .setPassword(set.getString("password"))
                            .setRole(set.getString("role"))
                            .setAddress(set.getString("address"))
                            .setMembershipNumber(set.getString("membership_number"))
                            .setLibraryName(set.getString("library_name"))
                            .build();
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error fetching admin by ID", ex);
        }
        return null;
    }

    public int deleteAdminById(int adminId) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(DELETQUERY)) {

            statement.setInt(1, adminId);
            return statement.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error during deleting admin", ex);
        }
        return 0;
    }

    public int updateAdminById(Admin admin) {
        try (Connection connection = DBConnection.getConnection();
             PreparedStatement statement = connection.prepareStatement(UPDATEQUERY)) {

            statement.setString(1, admin.getName());
            statement.setString(2, admin.getAddress());
            statement.setString(3, admin.getLibraryName());
            statement.setInt(4, admin.getAdminId());

            return statement.executeUpdate();

        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error during updating admin", ex);
        }
        return 0;
    }

    public String getMembershipNumber(String name) {
        int number = (int) (Math.random() * 10000);
        return name.substring(0, Math.min(3, name.length())) + String.format("%04d", number);
    }
}
