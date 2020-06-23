package by.clowns.controllers;

import by.clowns.entity.Car;
import by.clowns.service.CarService;
import by.clowns.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class CarsController {

    private CarService carService;

    private RegionService regionService;

    @Autowired
    public CarsController(CarService carService, RegionService regionService) {
        this.carService = carService;
        this.regionService = regionService;
    }

    @GetMapping("/cars")
    public String cars(Model model) {
        Set<Car> cars = carService.read();
        cars = cars.stream()
                .filter(car -> car.getPrice() < (int) model.asMap().getOrDefault("price", Integer.MAX_VALUE))
                .collect(Collectors.toSet());
        model.addAttribute("cars", carService.read());
        model.addAttribute("regions", regionService.read());
        return "cars";
    }

}
