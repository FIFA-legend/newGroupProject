package by.clowns.servlet;

import by.clowns.entity.User;
import by.clowns.service.ServiceInterface;
import by.clowns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Import;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/*@WebServlet(urlPatterns = "/users", name = "UsersServlet")
public class UsersServlet extends HttpServlet {

    private UserService service;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        UserService userService = new UserService();
        req.setAttribute("users", userService.read());
        req.getRequestDispatcher("/WEB-INF/jsp/users.jsp").forward(req, resp);
    }
}*/
