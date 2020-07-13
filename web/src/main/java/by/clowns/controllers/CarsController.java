package by.clowns.controllers;

import by.clowns.entity.Car;
import by.clowns.entity.Region;
import by.clowns.service.CarFilterService;
import by.clowns.service.CarService;
import by.clowns.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Set;
import java.util.stream.Collectors;

@Controller
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

    @ModelAttribute("modelCar")
    Set<Region> regions(){
        return regionService.read();
    }

    @Autowired
    public CarsController(CarService carService, RegionService regionService, CarFilterService carFilterService) {
        this.carService = carService;
        this.regionService = regionService;
    }

    @GetMapping("/cars")
    public String cars(Model model, @ModelAttribute Car car) {
        Set<Car> cars = carFilterService.carFilterQuery(car);
        model.addAttribute("cars", cars);
        return "cars";
    }

}
