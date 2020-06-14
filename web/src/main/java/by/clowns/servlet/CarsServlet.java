package by.clowns.servlet;

import by.clowns.entity.Car;
import by.clowns.entity.Truck;
import by.clowns.service.*;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/cars", name = "CarsServlet")
public class CarsServlet extends HttpServlet {

    @Autowired
    private BusService busService;

    @Autowired
    private TruckService truckService;

    @Autowired
    private TaxiService taxiService;

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("cars", taxiService.read());
        req.getRequestDispatcher("/WEB-INF/jsp/cars.jsp").forward(req, resp);
    }
}
