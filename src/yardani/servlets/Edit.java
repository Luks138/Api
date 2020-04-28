package yardani.servlets;

import yardani.Config;
import yardani.controllers.NetworkController;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/edit")
public class Edit extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        if(id == null) {
            resp.sendRedirect("/api");
            return;
        }
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        if(firstname != null) {
            editUser(id, "firstname", firstname);
        }
        else if(lastname != null) {
            editUser(id, "lastname", lastname);
        }
        else if(country != null) {
            editUser(id, "country", country);
        }
        else if(city != null) {
            editUser(id, "city", city);
        }
        resp.sendRedirect("/api");
        return;
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void editUser(String id, String param, String value) {
        NetworkController networkController = new NetworkController();
        Statement statement = null;
        ResultSet rs = null;
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "UPDATE api_table SET " + param + " = '" + value + "' WHERE id = " + id;
        try {
            statement = networkController.getConnection().createStatement();
            statement.executeUpdate(query);
            System.out.println("User updated!");
        } catch(SQLException e) {
            System.out.println("Can't update user!");
        } finally {
            networkController.disconnect(statement, rs);
        }
    }
}
