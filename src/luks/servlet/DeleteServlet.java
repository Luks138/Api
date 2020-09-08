package luks.servlet;

import com.google.gson.Gson;
import luks.config.Config;
import luks.controller.NetworkController;
import luks.domain.ErrorMessage;
import luks.security.HasTokenAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;

@WebServlet("/delete")
public class DeleteServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Gson gson = new Gson();
        String id = req.getParameter("id");
        String token = req.getParameter("token");
        if(id != null && token != null) {
            if(new HasTokenAccess().hasAccess(token)) {
                if(deleteUser(id)) {
                    resp.sendRedirect("/api");
                    return;
                } else {
                    resp.getWriter().write(gson.toJson(new ErrorMessage("User wasn't deleted.", 6)));
                }
            } else {
                resp.getWriter().write(gson.toJson(new ErrorMessage("Token doesn't have access!", 7)));
            }
        } else {
            resp.getWriter().write(gson.toJson(new ErrorMessage("Id or token not specified.", 1)));
        }
    }

    private boolean deleteUser(String id) {
        NetworkController network = new NetworkController();
        PreparedStatement statement = null;
        boolean isDeleted = false;
        network.connect(Config.DB_URL, Config.DB_USER, Config.DB_PASSWORD);
        String query = "DELETE FROM api_table WHERE id = ?";
        try {
            statement = network.getConnection().prepareStatement(query);
            statement.setString(1, id);
            statement.executeUpdate();
            System.out.println("User deleted!");
            isDeleted = true;
        } catch (SQLException e) {
            System.out.println("Can't delete user...\n" + e);
        } finally {
            network.disconnect(statement, null);
        }

        return isDeleted;
    }
}
