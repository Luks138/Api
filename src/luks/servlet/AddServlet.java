package luks.servlet;

import com.google.gson.Gson;
import luks.config.Config;
import luks.controller.NetworkController;
import luks.domain.ErrorMessage;
import luks.security.Crypto;
import luks.security.HasTokenAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/add")
public class AddServlet extends HttpServlet {

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
        Crypto crypto = new Crypto();
        if(id == null || token == null || firstname == null || lastname == null || country == null || city == null || street == null || houseNum == null || email == null) {
            resp.getWriter().write(gson.toJson(new ErrorMessage("Id or other value not specified.", 1)));
        } else {
            if(new HasTokenAccess().hasAccess(token)) {
                if(checkForId(id)) {
                    addUser(id, new String(crypto.encrypt(firstname, Config.ENCRYPT_KEY)), new String(crypto.encrypt(lastname, Config.ENCRYPT_KEY)), new String(crypto.encrypt(country, Config.ENCRYPT_KEY)), new String(crypto.encrypt(city, Config.ENCRYPT_KEY)), new String(crypto.encrypt(street, Config.ENCRYPT_KEY)), new String(crypto.encrypt(houseNum, Config.ENCRYPT_KEY)), new String(crypto.encrypt(email, Config.ENCRYPT_KEY)));
                    System.out.println("User added!");
                    resp.sendRedirect("/api?id=" + id);
                    return;
                } else {
                    resp.getWriter().write((gson.toJson(new ErrorMessage("Id is already in use.", 2))));
                }
            } else {
                resp.getWriter().write(gson.toJson(new ErrorMessage("Token doesn't have access!", 7)));
            }
        }
    }

    private boolean checkForId(String id) {
        NetworkController network = new NetworkController();
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean isChecked = false;
        String idBd = null;
        network.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "SELECT * FROM api_table WHERE id = ?";
        try {
            statement = network.getConnection().prepareStatement(query);
            statement.setString(1, id);
            rs = statement.executeQuery();
            while(rs.next()) {
                idBd = rs.getString("id");
            }
            if(idBd == null)
                isChecked = true;
        } catch (SQLException e) {
            System.out.println("Can't check user for id!\n" + e);
        } finally {
            network.disconnect(statement, rs);
        }

        return isChecked;
    }

    private void addUser(String id, String firstname, String lastname, String country, String city, String street, String houseNum, String email) {
        NetworkController networkController = new NetworkController();
        PreparedStatement statement = null;
        ResultSet rs = null;
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "INSERT api_table(id, firstname, lastname, country, city, street, housenum, email) VALUES (?, ?, ?, ?, ?, ?, ?, ?);";
        try {
            statement = networkController.getConnection().prepareStatement(query);

            statement.setString(1, id);
            statement.setString(2, firstname);
            statement.setString(3, lastname);
            statement.setString(4, country);
            statement.setString(5, city);
            statement.setString(6, street);
            statement.setString(7, houseNum);
            statement.setString(8, email);

            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Can't add user\n" + e);
        } finally {
            networkController.disconnect(statement, rs);
        }
    }
}
