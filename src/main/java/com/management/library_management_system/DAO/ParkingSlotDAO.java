 
package com.management.library_management_system.DAO;

import com.management.library_management_system.Utils.DBConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class ParkingSlotDAO {
    
    private static final String INSERTQUERY = "INSERT INTO (slotNumber) VALUES(?)";
    
    private static final String DELETEQUERY = "DELETE from parking_slot where id=?";
    
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
    
    public int DeleteParkingSlot(int parkingSlotId)
    {
        try(Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(DELETEQUERY))
        {
            statement.setInt(1, parkingSlotId);
            return statement.executeUpdate();
        } 
        catch (SQLException ex) {
            Logger.getLogger(ParkingSlotDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    
        return 0;
    }
    
}
