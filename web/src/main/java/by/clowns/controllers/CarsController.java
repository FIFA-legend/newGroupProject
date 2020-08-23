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

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

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

    private final Comparator<Car> carComparator = (o1, o2) -> {
        if (!o1.getBrand().equals(o2.getBrand())) {
            return o1.getBrand().compareTo(o2.getBrand());
        } else if (!o1.getModel().equals(o2.getModel())){
            return o1.getModel().compareTo(o2.getModel());
        } else {
            return o1.getNumber().compareTo(o2.getNumber());
        }
    };

    @Autowired
    public CarsController(CarService carService, RegionService regionService, CarFilterService carFilterService, UserService userService) {
        this.carService = carService;
        this.regionService = regionService;
        this.carFilterService = carFilterService;
        this.userService = userService;
    }

    @GetMapping("/cars/{page}")
    public String cars(Model model, CarDTO car, @PathVariable int page) {
        Set<Car> cars = new TreeSet<>(carComparator);
        cars.addAll(carFilterService.getFreeAndFilteredCars(car));
        Set<Car> carsOnPage = new TreeSet<>(carComparator);
        int coefficient = page - 1;
        int i = 0;
        for (Car c : cars) {
            if (i / 10 == coefficient) {
                carsOnPage.add(c);
            }
            i++;
        }
        model.addAttribute("cars", carsOnPage);
        Object sessionUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (sessionUser instanceof UserDetails) {
            username = ((UserDetails)sessionUser).getUsername();
        } else {
            username = sessionUser.toString();
        }
        int allPages = (cars.size() - 1) / 10 + 1;
        model.addAttribute("minPrice", car.getMinPrice());
        model.addAttribute("maxPrice", car.getMaxPrice());
        model.addAttribute("brand", car.getBrand());
        model.addAttribute("number", car.getNumber());
        model.addAttribute("page", page);
        model.addAttribute("allPages", allPages);
        model.addAttribute("loggedUser", userService.get(username));
        return "cars";
    }

}
