package yardani.servlet;

import com.google.gson.Gson;
import yardani.config.Config;
import yardani.controller.NetworkController;
import yardani.domain.ErrorMessage;
import yardani.domain.Message;
import yardani.security.Crypto;
import yardani.security.HasTokenAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/api")
public class ApiServlet extends HttpServlet {

    private String id;
    private String firstName;
    private String lastName;
    private String country;
    private String city;
    private String street;
    private String houseNum;
    private String email;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        Crypto crypto = new Crypto();
        String id = req.getParameter("id");
        String token = req.getParameter("token");
        if(token != null) {
            if(new HasTokenAccess().hasAccess(token)) {
                if(id != null) {
                    if(getInfo(id)) {
                        Message address = new Message(new String(crypto.decrypt(street, Config.ENCRYPT_KEY)), new String(crypto.decrypt(houseNum, Config.ENCRYPT_KEY)), new String(crypto.decrypt(city, Config.ENCRYPT_KEY)));
                        Message message = new Message(this.id, new String(crypto.decrypt(firstName, Config.ENCRYPT_KEY)), new String(crypto.decrypt(lastName, Config.ENCRYPT_KEY)), new String(crypto.decrypt(country, Config.ENCRYPT_KEY)), new String(crypto.decrypt(email, Config.ENCRYPT_KEY)), address);
                        resp.getWriter().write(gson.toJson(message));
                    } else {
                        resp.getWriter().write(gson.toJson(new ErrorMessage("User doesn't exist.", 4)));
                    }
                }
            } else {
                resp.getWriter().write(gson.toJson(new ErrorMessage("Token doesn't have access!", 7)));
            }
        }
    }

    private boolean getInfo(String id) {
        NetworkController network = new NetworkController();
        PreparedStatement statement = null;
        ResultSet rs = null;
        boolean isGotten = false;
        network.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "SELECT * FROM api_table WHERE id = ?";
        try {
            statement = network.getConnection().prepareStatement(query);
            statement.setString(1, id);
            rs = statement.executeQuery();
            while(rs.next()){
                this.id = rs.getString("id");
                if(this.id != null) {
                    firstName = rs.getString("firstname");
                    lastName = rs.getString("lastname");
                    country = rs.getString("country");
                    city = rs.getString("city");
                    street = rs.getString("street");
                    houseNum = rs.getString("housenum");
                    email = rs.getString("email");

                    isGotten = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Can't get info\n" + e);
        } finally {
            network.disconnect(statement, rs);
        }

        return isGotten;
    }
}
