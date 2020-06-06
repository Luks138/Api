package yardani.servlet;

import com.google.gson.Gson;
import yardani.config.Config;
import yardani.controller.NetworkController;
import yardani.domain.ErrorMessage;
import yardani.domain.Token;
import yardani.security.Crypto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebServlet("/token")
public class GetTokenServlet extends HttpServlet {

    private int access;
    private String token;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username != null && password != null) {
            Gson gson = new Gson();
            if(getToken(username, password)) {
                Token message = new Token(access, token);
                resp.getWriter().write(gson.toJson(message));
            } else {
                resp.getWriter().write(gson.toJson(new ErrorMessage("Incorrect username or password.", 8)));
            }
        } else {
            Gson gson = new Gson();
            resp.getWriter().write(gson.toJson(new ErrorMessage("Username or password not specified.", 1)));
        }
    }

    private boolean getToken(String username, String password) {
        boolean isGotten = false;
        NetworkController networkController = new NetworkController();
        PreparedStatement statement = null;
        ResultSet rs = null;
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        Crypto crypto = new Crypto();
        String query = "SELECT * FROM users WHERE username = ? AND password = ?";
        try {
            statement = networkController.getConnection().prepareStatement(query);
            statement.setString(1, new String(crypto.encrypt(username, Config.ENCRYPT_KEY)));
            statement.setString(2, new String(crypto.encrypt(password, Config.ENCRYPT_KEY)));
            rs = statement.executeQuery();
            while(rs.next()){
                access = rs.getInt("hasaccess");
                token = rs.getString("token");
                isGotten = true;
            }
        } catch (SQLException e) {
            System.out.println("Can't get token\n" + e);
        } finally {
            networkController.disconnect(statement, rs);
        }

        return isGotten;
    }
}
