package yardani.controller;

import javax.swing.*;
import java.sql.Connection;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.DriverManager;
import java.sql.SQLException;

public class NetworkController {

    private static Connection connection = null;
    private static Statement statement = null;
    private static ResultSet resultSet = null;

    public Connection getConnection() {
        return connection;
    }

    public void connect(String url, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("Connected...");
        } catch(Exception e) {
            System.out.println("Connection failed...\n" + e);
            JOptionPane.showMessageDialog(null, "Failed to connect to data base");
            disconnect(statement, resultSet);
        }
    }

    public void disconnect(Statement statement, ResultSet resultSet) {
        try {
            if(statement != null)
                statement.close();
            if(connection != null)
                connection.close();
            if(resultSet != null)
                resultSet.close();
            System.out.println("Connection closed...");
        } catch (SQLException e) {
            System.out.println("Can't close connection...\n" + e);
        }
    }
}
