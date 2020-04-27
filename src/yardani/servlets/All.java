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
import java.util.ArrayList;

@WebServlet("/all")
public class All extends HttpServlet {

    private String id;
    private String firstName;
    private String lastName;
    private String country;
    private String city;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<String> idList = new ArrayList<>();
        Gson gson = new Gson();
        ArrayList<MessageEntity> data = new ArrayList<>();
        idList = getIds();
        for(String i : idList) {
            String[] userInfo = new String[5];
            userInfo = getInfo(i);
            MessageEntity message = new MessageEntity(userInfo[0], userInfo[1], userInfo[2], userInfo[3], userInfo[4]);
            data.add(message);
        }
        String jsonMessage = gson.toJson(data);
        resp.getWriter().write(jsonMessage);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    @Override
    public void init() throws ServletException {
        System.out.println("Init");
    }

    private ArrayList<String> getIds() {
        ArrayList<String> idList = new ArrayList<>();
        NetworkController networkController = new NetworkController();
        Statement statement = null;
        ResultSet rs = null;
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "SELECT id FROM api_table";
        try {
            statement = networkController.getConnection().createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()) {
                idList.add(rs.getString("id"));
            }
        } catch(SQLException e) {
            System.out.println("Can't get IDs from db\n" + e);
        } finally {
            networkController.disconnect(statement, rs);
        }
        return idList;
    }

    private String[] getInfo(String id) {
        NetworkController networkController = new NetworkController();
        Statement statement = null;
        ResultSet rs = null;
        String[] userInfo = new String[5];
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "SELECT * FROM api_table WHERE id = " + id;
        try {
            statement = networkController.getConnection().createStatement();
            rs = statement.executeQuery(query);
            while(rs.next()){
                this.id = rs.getString("id");
                firstName = rs.getString("firstname");
                lastName = rs.getString("lastname");
                city = rs.getString("city");
                country = rs.getString("country");

                userInfo[0] = this.id;
                userInfo[1] = firstName;
                userInfo[2] = lastName;
                userInfo[3] = city;
                userInfo[4] = country;
            }
        } catch (SQLException e) {
            System.out.println("Can't get info\n" + e);
        } finally {
            networkController.disconnect(statement, rs);
        }
        return userInfo;
    }
}
