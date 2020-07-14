package by.clowns.controllers;

import by.clowns.dto.CarDTO;
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

@Controller
@ComponentScan("by.clowns.dao")
public class CarsController {

    private final CarService carService;

    private final RegionService regionService;

    private CarFilterService carFilterService;

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

    @Autowired
    public CarsController(CarService carService, RegionService regionService, CarFilterService carFilterService) {
        this.carService = carService;
        this.regionService = regionService;
        this.carFilterService = carFilterService;
    }

    @GetMapping("/cars")
    public String cars(Model model, CarDTO car) {
        Set<Car> cars = carFilterService.filter(car);
        model.addAttribute("cars", cars);
        return "cars";
    }

}
