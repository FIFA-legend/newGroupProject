package by.clowns.controllers;

import by.clowns.entity.Truck;
import by.clowns.service.TruckService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TruckRegisterController {

    private TruckService truckService;

    @Autowired
    public TruckRegisterController(TruckService truckService) {
        this.truckService = truckService;
    }

    @ModelAttribute("truck")
    public Truck emptyTruck() {
        return new Truck();
    }

    @GetMapping("/truck/register")
    public String registerTruck() {
        return "truckRegistration";
    }

    @PostMapping("/truck/register")
    public String saveTruck(@Valid Truck truck, Errors errors) {
        if (errors.hasErrors()) return "truckRegistration";
        truckService.create(truck);
        return "redirect:/cars";
    }

}
