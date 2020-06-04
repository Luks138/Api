package yardani.security;

import yardani.config.Config;
import yardani.controller.NetworkController;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class EditAccess {

    public void enable(String token) {
        NetworkController networkController = new NetworkController();
        Statement statement = null;
        ResultSet rs = null;
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "UPDATE users SET hasaccess = " + 1 + " WHERE token = '" + token + "'";
        try {
            statement = networkController.getConnection().createStatement();
            statement.executeUpdate(query);
            System.out.println("Access enabled!");
        } catch(SQLException e) {
            System.out.println("Can't enable access!");
        } finally {
            networkController.disconnect(statement, rs);
        }
    }

    public void disable(String token) {
        NetworkController networkController = new NetworkController();
        Statement statement = null;
        ResultSet rs = null;
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "UPDATE users SET hasaccess = " + 0 + " WHERE token = '" + token + "'";
        try {
            statement = networkController.getConnection().createStatement();
            statement.executeUpdate(query);
            System.out.println("Access disabled!");
        } catch(SQLException e) {
            System.out.println("Can't disable access!");
        } finally {
            networkController.disconnect(statement, rs);
        }
    }
}
