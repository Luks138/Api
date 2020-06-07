package yardani.servlet;

import com.google.gson.Gson;
import yardani.domain.ErrorMessage;
import yardani.security.EditAccess;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/disable")
public class DisableAccessServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String token = req.getParameter("token");
        if(token != null) {
            new EditAccess().disable(token);
            resp.sendRedirect("/api");
            return;
        } else {
            Gson gson = new Gson();
            resp.getWriter().write(gson.toJson(new ErrorMessage("Token not specified.", 1)));
        }
    }
}
