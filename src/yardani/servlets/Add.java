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

@WebServlet("/add")
public class Add extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        Gson gson = new Gson();
        if(id == null || firstname == null || lastname == null || country == null || city == null) {
            ErrorMessageEntity errorMessage = new ErrorMessageEntity("Id or other value not specified.", 1);
            String jsonMessage = gson.toJson(errorMessage);
            resp.getWriter().write(jsonMessage);
        } else {
            if(checkForId(id)) {
                if(addUser(id, firstname, lastname, country, city)) {
                    resp.sendRedirect("/api?id=" + id);
                    return;
                }
            } else {
                ErrorMessageEntity errorMessage = new ErrorMessageEntity("Id is already in use.", 2);
                String jsonMessage = gson.toJson(errorMessage);
                resp.getWriter().write(jsonMessage);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private boolean checkForId(String id) {
        NetworkController networkController = new NetworkController();
        Statement statement = null;
        ResultSet rs = null;
        boolean isChecked = false;
        String idBd = null;
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "SELECT * FROM api_table WHERE id = " + id;
        try {
            statement = networkController.getConnection().createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()) {
                idBd = rs.getString("id");
            }
            if(idBd == null)
                isChecked = true;
        } catch (SQLException e) {
            System.out.println("Can't check user for id!\n" + e);
        } finally {
            networkController.disconnect(statement, rs);
        }
        return isChecked;
    }

    private boolean addUser(String id, String firstname, String lastname, String country, String city) {
        NetworkController networkController = new NetworkController();
        Statement statement = null;
        ResultSet rs = null;
        boolean isAdded = false;
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "INSERT api_table(id, firstname, lastname, country, city) VALUES ('" + id + "','" + firstname + "','" + lastname + "','" + country + "','" + city + "');";
        try {
            statement = networkController.getConnection().createStatement();
            statement.executeUpdate(query);
            isAdded = true;
        } catch (SQLException e) {
            System.out.println("Can't add user\n" + e);
        } finally {
            networkController.disconnect(statement, rs);
        }
        return isAdded;
    }
}
