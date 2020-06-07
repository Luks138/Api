package yardani.controller;

import java.sql.*;

public class NetworkController {

    private Connection connection = null;

    public Connection getConnection() {
        return connection;
    }

    public void connect(String url, String username, String password) {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").getDeclaredConstructor().newInstance();
            connection = DriverManager.getConnection(url, username, password);
        } catch(Exception e) {
            System.out.println("Connection failed...\n" + e);
        }
    }

    public void disconnect(PreparedStatement statement, ResultSet resultSet) {
        try {
            if(statement != null)
                statement.close();
            if(connection != null)
                connection.close();
            if(resultSet != null)
                resultSet.close();
        } catch (SQLException e) {
            System.out.println("Can't close connection...\n" + e);
        }
    }
}
