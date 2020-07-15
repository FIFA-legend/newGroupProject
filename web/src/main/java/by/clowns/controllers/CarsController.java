package by.clowns.controllers;

import by.clowns.dto.CarDTO;
import by.clowns.entity.Car;
import by.clowns.entity.Region;
import by.clowns.entity.Role;
import by.clowns.service.CarFilterService;
import by.clowns.service.CarService;
import by.clowns.service.RegionService;
import by.clowns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;

@Controller
@ComponentScan("by.clowns.dao")
public class CarsController {

    private final CarService carService;

    private final RegionService regionService;

    private final CarFilterService carFilterService;

    private final UserService userService;

    @ModelAttribute("cars")
    Set<Car> cars(){
        return carService.read();
    }

    @ModelAttribute("modelCar")
    CarDTO modelCar(){
        return new CarDTO();
    }

    @ModelAttribute("regions")
    Set<Region> regions(){
        return regionService.read();
    }

    @ModelAttribute("admin")
    public Role adminRole() {
        return userService.getAllRoles()[1];
    }

    @Autowired
    public CarsController(CarService carService, RegionService regionService, CarFilterService carFilterService, UserService userService) {
        this.carService = carService;
        this.regionService = regionService;
        this.carFilterService = carFilterService;
        this.userService = userService;
    }

    @GetMapping("/cars")
    public String cars(Model model, CarDTO car) {
        Set<Car> cars = carFilterService.filter(car);
        model.addAttribute("cars", cars);
        Object sessionUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (sessionUser instanceof UserDetails) {
            username = ((UserDetails)sessionUser).getUsername();
        } else {
            username = sessionUser.toString();
        }
        model.addAttribute("loggedUser", userService.get(username));
        return "cars";
    }

}
