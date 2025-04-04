 
package com.management.library_management_system.DAO;

import com.management.library_management_system.Utils.DBConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RenewalDAO {
    
    private static final String INSERTQUERY = "INSER INTO Renewal(issue_id,renewal_date) VALUES(?,?)";
    
    public int renewBook(int issueId, Date renewalDate)
    {
        try(Connection connection = DBConnection.getConnection(); PreparedStatement statement = connection.prepareStatement(INSERTQUERY))
        {
            statement.setString(1, INSERTQUERY);
            return statement.executeUpdate();
        }
        catch (SQLException ex) {
            Logger.getLogger(RenewalDAO.class.getName()).log(Level.SEVERE,"error occur during renewal book", ex);
        }
        return 0;
    }
}
