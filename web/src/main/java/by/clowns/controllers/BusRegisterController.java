package by.clowns.controllers;

import by.clowns.entity.Bus;
import by.clowns.service.BusService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class BusRegisterController {

    private BusService busService;

    @Autowired
    public BusRegisterController(BusService busService) {
        this.busService = busService;
    }

    @ModelAttribute("bus")
    public Bus emptyBus() {
        return new Bus();
    }

    @GetMapping("/bus/register")
    public String busRegister() {
        return "busRegistration";
    }

    @PostMapping("/bus/register")
    public String busSave(@Valid Bus bus, Errors errors) {
        if (errors.hasErrors()) return "busRegistration";
        busService.create(bus);
        return "redirect:/cars";
    }
}
