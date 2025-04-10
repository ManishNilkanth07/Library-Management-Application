package com.management.library_management_system.DAO;

import com.management.library_management_system.Utils.DBConnection;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RenewalDAO {

    private static final String INSERTQUERY = "INSERT INTO renewal(issue_id, renewal_date) VALUES(?, ?)";
    private static final String CHECKISSUE = "SELECT issue_id FROM renewal WHERE issue_id = ?";

    private static final Logger LOGGER = Logger.getLogger(RenewalDAO.class.getName());

    public int renewBook(int issueId, Date renewalDate) {
        try (Connection connection = DBConnection.getConnection(); PreparedStatement checkStatement = connection.prepareStatement(CHECKISSUE)) {

            checkStatement.setInt(1, issueId);

            try (ResultSet set = checkStatement.executeQuery()) {
                if (!set.next()) {
                    try (PreparedStatement renewalStatement = connection.prepareStatement(INSERTQUERY)) {
                        renewalStatement.setInt(1, issueId);
                        renewalStatement.setDate(2, renewalDate);
                        return renewalStatement.executeUpdate();
                    }
                }
            }
        } catch (SQLException ex) {
            LOGGER.log(Level.SEVERE, "Error occurred during renewal of book", ex);
        }
        return 0;
    }
}
