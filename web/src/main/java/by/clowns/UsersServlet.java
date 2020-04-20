package by.clowns;

import by.clowns.entity.User;
import by.clowns.service.Service;
import by.clowns.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/users", name = "UsersServlet")
public class UsersServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Service<User> userService = UserService.getInstance();
        req.setAttribute("users", userService.read());
        req.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(req, resp);
    }
}
