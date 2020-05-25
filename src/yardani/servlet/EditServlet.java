package yardani.servlet;

import com.google.gson.Gson;
import yardani.config.Config;
import yardani.controller.NetworkController;
import yardani.domain.ErrorMessage;
import yardani.security.Crypto;
import yardani.security.HasTokenAccess;

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
public class EditServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String id = req.getParameter("id");
        String token = req.getParameter("token");
        String firstname = req.getParameter("firstname");
        String lastname = req.getParameter("lastname");
        String country = req.getParameter("country");
        String city = req.getParameter("city");
        String street = req.getParameter("street");
        String houseNum = req.getParameter("housenum");
        String email = req.getParameter("email");
        Gson gson = new Gson();

        if(id == null || token == null || (firstname == null && lastname == null && country == null && city == null && street == null && houseNum == null && email == null)) {
            resp.getWriter().write(gson.toJson(new ErrorMessage("Id or other value not specified.", 1)));
            return;
        }
        if(new HasTokenAccess().hasAccess(token)) {
            Crypto crypto = new Crypto();

            if(firstname != null)
                editUser(id, "firstname", new String(crypto.encrypt(firstname, Config.ENCRYPT_KEY)));

            if(lastname != null)
                editUser(id, "lastname", new String(crypto.encrypt(lastname, Config.ENCRYPT_KEY)));

            if(country != null)
                editUser(id, "country", new String(crypto.encrypt(country, Config.ENCRYPT_KEY)));

            if(city != null)
                editUser(id, "city", new String(crypto.encrypt(city, Config.ENCRYPT_KEY)));

            if(street != null)
                editUser(id, "street", new String(crypto.encrypt(street, Config.ENCRYPT_KEY)));

            if(houseNum != null)
                editUser(id, "housenum", new String(crypto.encrypt(houseNum, Config.ENCRYPT_KEY)));

            if(email != null)
                editUser(id, "email", new String(crypto.encrypt(houseNum, Config.ENCRYPT_KEY)));

            resp.sendRedirect("/api");
            return;
        } else {
            resp.getWriter().write(gson.toJson(new ErrorMessage("Token doesn't have access!", 7)));
        }
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
            System.out.println("Updated " + param + "of user with id " + id);
        } catch(SQLException e) {
            System.out.println("Can't update user!");
        } finally {
            networkController.disconnect(statement, rs);
        }
    }
}
