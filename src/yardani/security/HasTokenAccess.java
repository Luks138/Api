package yardani.security;

import yardani.config.Config;
import yardani.controller.NetworkController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HasTokenAccess {

    public boolean hasAccess(String token) {
        boolean hasAcc = false;
        NetworkController networkController = new NetworkController();
        Statement statement = null;
        ResultSet rs = null;
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "SELECT hasaccess FROM users WHERE token = '" + token + "'";
        try {
            statement = networkController.getConnection().createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                int access = rs.getInt("hasaccess");
                if(access == 1)
                    hasAcc = true;
            }
        } catch (SQLException e) {
            System.out.println("Can't get info\n" + e);
        } finally {
            networkController.disconnect(statement, rs);
        }
        return hasAcc;
    }
}
