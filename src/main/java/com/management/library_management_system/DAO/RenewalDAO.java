 
package com.management.library_management_system.DAO;

import com.management.library_management_system.Utils.DBConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RenewalDAO {
    
    private static final String INSERTQUERY = "INSER INTO renewal(issue_id,renewal_date) VALUES(?,?)";
    private static final String CHECKISSUE = "SELECT issue_id from renewal where issue_id = ?";
    
    public int renewBook(int issueId, Date renewalDate)
    {
        try(Connection connection = DBConnection.getConnection(); PreparedStatement checkStatement = connection.prepareStatement(CHECKISSUE))
        {
            checkStatement.setInt(1, issueId);
            
            try(ResultSet set = checkStatement.executeQuery())
            {
                if(!set.next())
                {
                    try(PreparedStatement renewalStatement = connection.prepareStatement(INSERTQUERY))
                    {
                        renewalStatement.setInt(1, issueId);
                        renewalStatement.setDate(2, renewalDate);
                        renewalStatement.executeUpdate();
                    }
                }
            }
        }
        catch (SQLException ex) {
            Logger.getLogger(RenewalDAO.class.getName()).log(Level.SEVERE,"error occur during renewal book", ex);
        }
        return 0;
    }
}
