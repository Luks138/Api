package luks.security;

import luks.config.Config;
import luks.controller.NetworkController;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class HasTokenAccess {

    public boolean hasAccess(String token) {
        boolean hasAcc = false;
        NetworkController networkController = new NetworkController();
        PreparedStatement statement = null;
        ResultSet rs = null;
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "SELECT hasaccess FROM users WHERE token = ?";
        try {
            statement = networkController.getConnection().prepareStatement(query);
            statement.setString(1, token);
            rs = statement.executeQuery();
            while(rs.next()){
                int access = rs.getInt("hasaccess");
                if(access == 1)
                    hasAcc = true;
            }
        } catch (SQLException e) {
            System.out.println("Can't get info about token\n" + e);
        } finally {
            networkController.disconnect(statement, rs);
        }
        return hasAcc;
    }
}
