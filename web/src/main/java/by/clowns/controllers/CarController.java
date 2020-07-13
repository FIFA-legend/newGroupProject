package by.clowns.controllers;

import by.clowns.entity.Car;
import by.clowns.entity.Truck;
import by.clowns.service.BusService;
import by.clowns.service.TaxiService;
import by.clowns.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class CarController {

    private TruckService truckService;

    private BusService busService;

    private TaxiService taxiService;

    @Autowired
    public CarController(TruckService truckService, BusService busService, TaxiService taxiService) {
        this.truckService = truckService;
        this.busService = busService;
        this.taxiService = taxiService;
    }

    @GetMapping("/car/{id}/details")
    public String getSingleCar(@PathVariable Long id, Model model) {
        model.addAttribute("truck", truckService.get(id));
        model.addAttribute("bus", busService.get(id));
        model.addAttribute("taxi", taxiService.get(id));
        return "singleCar";
    }

    @PostMapping("/car/{id}/details")
    public String singleCar(@PathVariable Long id, RedirectAttributes model) {
        if (truckService.get(id) != null) {
            model.addFlashAttribute("car", truckService.get(id));
        } else if (taxiService.get(id) != null) {
            model.addFlashAttribute("car", taxiService.get(id));
        } else if (busService.get(id) != null) {
            model.addFlashAttribute("car", busService.get(id));
        }
        return "redirect:/request/save";
    }

}
