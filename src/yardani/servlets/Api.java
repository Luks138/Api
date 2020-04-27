package yardani.servlets;

import com.google.gson.Gson;
import yardani.Config;
import yardani.controllers.NetworkController;
import yardani.templates.MessageEntity;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/api")
public class Api extends HttpServlet {

    private String id;
    private String firstName;
    private String lastName;
    private String country;
    private String city;

    public String getId() {
        return id;
    }

    @Override
    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String id = req.getParameter("id");
        if(id != null) {
            if(getInfo(id)) {
                MessageEntity message = new MessageEntity(this.id, firstName, lastName, country, city);
                String jsonMessage = gson.toJson(message);
                resp.getWriter().write(jsonMessage);
            } else {
                resp.getWriter().write("User doesn't exist!");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Init");
    }

    private boolean getInfo(String id) {
        NetworkController networkController = new NetworkController();
        Statement statement = null;
        ResultSet rs = null;
        boolean isGotten = false;
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "SELECT * FROM api_table WHERE id = " + id;
        try {
            statement = networkController.getConnection().createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                this.id = rs.getString("id");
                if(this.id != null) {
                    firstName = rs.getString("firstname");
                    lastName = rs.getString("lastname");
                    country = rs.getString("country");
                    city = rs.getString("city");
                    isGotten = true;
                }
            }
        } catch (SQLException e) {
            System.out.println("Can't get info\n" + e);
        } finally {
            networkController.disconnect(statement, rs);
        }
        return isGotten;
    }
}
