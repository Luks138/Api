package luks.servlet;

import com.google.gson.Gson;
import luks.domain.ErrorMessage;
import luks.security.EditAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/enable")
public class EnableAccessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        if(token != null) {
            new EditAccess().enable(token);
            resp.sendRedirect("/api");
            return;
        } else {
            Gson gson = new Gson();
            resp.getWriter().write(gson.toJson(new ErrorMessage("Token not specified.", 1)));
        }
    }
}
