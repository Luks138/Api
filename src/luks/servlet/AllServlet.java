package luks.servlet;

import com.google.gson.Gson;
import luks.config.Config;
import luks.controller.NetworkController;
import luks.domain.ErrorMessage;
import luks.domain.Message;
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
import java.util.ArrayList;

@WebServlet("/all")
public class AllServlet extends HttpServlet {

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
        ArrayList<String> idList;
        ArrayList<Message> data = new ArrayList<>();
        Gson gson = new Gson();
        Crypto crypto = new Crypto();
        String token = req.getParameter("token");
        if(token != null) {
            if(new HasTokenAccess().hasAccess(token)) {
                idList = getIds();
                if(idList.size() == 0) {
                    resp.getWriter().write(gson.toJson(new ErrorMessage("No users found.", 5)));
                } else {
                    for(String i : idList) {
                        String[] userInfo;
                        userInfo = getInfo(i);
                        Message address = new Message(new String(crypto.decrypt(userInfo[5], Config.ENCRYPT_KEY)), new String(crypto.decrypt(userInfo[6], Config.ENCRYPT_KEY)), new String(crypto.decrypt(userInfo[4], Config.ENCRYPT_KEY)));
                        Message message = new Message(userInfo[0], new String(crypto.decrypt(userInfo[1], Config.ENCRYPT_KEY)), new String(crypto.decrypt(userInfo[2], Config.ENCRYPT_KEY)), new String(crypto.decrypt(userInfo[3], Config.ENCRYPT_KEY)), new String(crypto.decrypt(userInfo[7], Config.ENCRYPT_KEY)), address);
                        data.add(message);
                    }
                    resp.getWriter().write(gson.toJson(data));
                }
            } else {
                resp.getWriter().write(gson.toJson(new ErrorMessage("Token doesn't have access!", 7)));
            }
        } else {
            resp.getWriter().write(gson.toJson(new ErrorMessage("Token not specified.", 1)));
        }
    }

    private ArrayList<String> getIds() {
        ArrayList<String> idList = new ArrayList<>();
        NetworkController network = new NetworkController();
        PreparedStatement statement = null;
        ResultSet rs = null;
        network.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "SELECT id FROM api_table";
        try {
            statement = network.getConnection().prepareStatement(query);
            rs = statement.executeQuery();
            while(rs.next()) {
                idList.add(rs.getString("id"));
            }
        } catch(SQLException e) {
            System.out.println("Can't get IDs from db\n" + e);
        } finally {
            network.disconnect(statement, rs);
        }

        return idList;
    }

    private String[] getInfo(String id) {
        NetworkController network = new NetworkController();
        PreparedStatement statement = null;
        ResultSet rs = null;
        String[] userInfo = new String[8];
        network.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "SELECT * FROM api_table WHERE id = ?";
        try {
            statement = network.getConnection().prepareStatement(query);
            statement.setString(1, id);
            rs = statement.executeQuery();
            while(rs.next()){
                this.id = rs.getString("id");
                firstName = rs.getString("firstname");
                lastName = rs.getString("lastname");
                country = rs.getString("country");
                city = rs.getString("city");
                street = rs.getString("street");
                houseNum = rs.getString("housenum");
                email = rs.getString("email");

                userInfo[0] = this.id;
                userInfo[1] = firstName;
                userInfo[2] = lastName;
                userInfo[3] = country;
                userInfo[4] = city;
                userInfo[5] = street;
                userInfo[6] = houseNum;
                userInfo[7] = email;
            }
        } catch (SQLException e) {
            System.out.println("Can't get info\n" + e);
        } finally {
            network.disconnect(statement, rs);
        }

        return userInfo;
    }
}
