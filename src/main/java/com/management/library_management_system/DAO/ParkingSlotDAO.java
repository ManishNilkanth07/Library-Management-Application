package com.management.library_management_system.DAO;

import com.management.library_management_system.Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkingSlotDAO {

    private static final String INSERTQUERY = "INSERT INTO parking_slot (slot_number) VALUES (?)";
    private static final String DELETEQUERY = "DELETE FROM parking_slot WHERE id = ?";

    private static final Logger LOGGER = Logger.getLogger(ParkingSlotDAO.class.getName());

    public int createParkingSlot(String slotNumber) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(INSERTQUERY)) {

            statement.setString(1, slotNumber);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error occurred during creating parking slot", ex);
        }
        return 0;
    }

    public int deleteParkingSlot(int parkingSlotId) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETEQUERY)) {

            statement.setInt(1, parkingSlotId);
            return statement.executeUpdate();
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error occurred during deleting parking slot", ex);
        }
        return 0;
    }
}
