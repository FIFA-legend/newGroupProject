package by.clowns.controllers;

import by.clowns.entity.Bus;
import by.clowns.entity.Region;
import by.clowns.service.BusService;
import by.clowns.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;
import java.util.Set;

@Controller
public class BusRegisterController {

    private final BusService busService;

    private final RegionService regionService;

    @Autowired
    public BusRegisterController(BusService busService, RegionService regionService) {
        this.busService = busService;
        this.regionService = regionService;
    }

    @ModelAttribute("bus")
    public Bus emptyBus() {
        return new Bus();
    }

    @ModelAttribute("regions")
    public Set<Region> regions() {
        return regionService.read();
    }

    @GetMapping("/bus/register")
    public String busRegister() {
        return "busRegistration";
    }

    @PostMapping("/bus/register")
    public String busSave(@Valid Bus bus, Errors errors) {
        if (errors.hasErrors()) return "busRegistration";
        busService.create(bus);
        return "redirect:/cars/1";
    }
}
