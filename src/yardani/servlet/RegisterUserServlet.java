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
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

@WebServlet("/register")
public class RegisterUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        int id = Integer.parseInt(idParam);
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        if(username != null && password != null) {
            if(registerUser(id, username, password)) {
                System.out.println("Success!");
                resp.sendRedirect("/api");
                return;
            }
        } else {
            Gson gson = new Gson();
            ErrorMessage errorMessage = new ErrorMessage("Id or other value not specified.", 1);
            String jsonMessage = gson.toJson(errorMessage);
            resp.getWriter().write(jsonMessage);
        }
    }

    private boolean registerUser(int id, String username, String password) {
        boolean isReg = false;
        NetworkController networkController = new NetworkController();
        Statement statement = null;
        ResultSet rs = null;
        networkController.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        Crypto crypto = new Crypto();
        String token = new String(crypto.encrypt((username+password), Config.ENCRYPT_KEY));
        String query = "INSERT users(id, username, password, hasaccess, token) VALUES ("+ id +",'" + new String(crypto.encrypt(username, Config.ENCRYPT_KEY)) + "','" + new String(crypto.encrypt(password, Config.ENCRYPT_KEY)) + "'," + 0 + ",'" + token + "')";
        try {
            statement = networkController.getConnection().createStatement();
            statement.executeUpdate(query);
            isReg = true;
        } catch (SQLException e) {
            System.out.println("Can't register user\n" + e);
        } finally {
            networkController.disconnect(statement, rs);
        }

        return isReg;
    }
}
