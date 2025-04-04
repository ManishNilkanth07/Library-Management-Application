 
package com.management.library_management_system.DAO;

import com.management.library_management_system.Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkingSlotDAO {
    
    private static final String INSERTQUERY = "INSERT INTO (slotNumber) VALUES(?)";
    
    public int createParkingSlot(String slotNumber)
    {
        try(Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(INSERTQUERY))
        {
            statement.setString(1,slotNumber);
            
            return statement.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(ParkingSlotDAO.class.getName()).log(Level.SEVERE,"error occur during creating parking slot", ex);
        }
        return 0;
    }
    
}
