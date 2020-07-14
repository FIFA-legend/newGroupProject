package by.clowns.controllers;

import by.clowns.entity.Car;
import by.clowns.entity.Region;
import by.clowns.service.CarFilterService;
import by.clowns.service.CarService;
import by.clowns.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
@ComponentScan("by.clowns.dao")
public class CarsController {

    private CarService carService;

    private RegionService regionService;

    private CarFilterService carFilterService;

    @ModelAttribute("cars")
    Set<Car> cars(){
        return carService.read();
    }

    @ModelAttribute("modelCar")
    Car modelCar(){
        return new Car();
    }

    @ModelAttribute("regions")
    Set<Region> regions(){
        return regionService.read();
    }

    @Autowired
    public CarsController(CarService carService, RegionService regionService, CarFilterService carFilterService) {
        this.carService = carService;
        this.regionService = regionService;
        this.carFilterService = carFilterService;
    }

    @GetMapping("/cars")
    public String cars(Model model, Car car) {
        Set<Car> cars = carFilterService.carFilterQuery(car/*new Car(
                (String) model.asMap().get("brand"),
                (int) model.asMap().getOrDefault("price", -1),
                ""
                )*/);
        model.addAttribute("cars", cars);
        return "cars";
    }

}
