package yardani.servlets;

import com.google.gson.Gson;
import yardani.Config;
import yardani.controllers.NetworkController;
import yardani.templates.ErrorMessageEntity;

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
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String houseNum = req.getParameter("housenum");
        String email = req.getParameter("email");

        if(id == null || (firstname == null && lastname == null && country == null && city == null && street == null && houseNum == null && email == null)) {
            Gson gson = new Gson();
            ErrorMessageEntity errorMessage = new ErrorMessageEntity("Id or other value not specified.", 1);
            String jsonMessage = gson.toJson(errorMessage);
            resp.getWriter().write(jsonMessage);
            return;
        }

        if(firstname != null) {
            editUser(id, "firstname", firstname);
        }
        if(lastname != null) {
            editUser(id, "lastname", lastname);
        }
        if(country != null) {
            editUser(id, "country", country);
        }
        if(city != null) {
            editUser(id, "city", city);
        }
        if(street != null) {
            editUser(id, "street", street);
        }
        if(houseNum != null) {
            editUser(id, "housenum", houseNum);
        }
        if(email != null) {
            editUser(id, "email", email);
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
