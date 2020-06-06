package yardani.servlet;

import com.google.gson.Gson;
import yardani.config.Config;
import yardani.controller.NetworkController;
import yardani.domain.ErrorMessage;
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

@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username != null && password != null && idParam != null) {
            int id = Integer.parseInt(idParam);
            registerUser(id, username, password);
            System.out.println("User registered successfully!");
            resp.sendRedirect("/token?username=" + username + "&password=" + password);
            return;
        } else {
            Gson gson = new Gson();
            resp.getWriter().write(gson.toJson(new ErrorMessage("Id or other value not specified.", 1)));
        }
    }

    private void registerUser(int id, String username, String password) {
        NetworkController networkController = new NetworkController();
        PreparedStatement statement = null;
        ResultSet rs = null;
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        Crypto crypto = new Crypto();
        String query = "INSERT users(id, username, password, hasaccess, token) VALUES (?, ?, ?, 0, ?)";
        try {
            statement = networkController.getConnection().prepareStatement(query);
            statement.setInt(1, id);
            statement.setString(2, new String(crypto.encrypt(username, Config.ENCRYPT_KEY)));
            statement.setString(3, new String(crypto.encrypt(password, Config.ENCRYPT_KEY)));
            statement.setString(4, new String(crypto.encrypt((username+password), Config.ENCRYPT_KEY)));
            statement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Can't register user\n" + e);
        } finally {
            networkController.disconnect(statement, rs);
        }
    }
}
