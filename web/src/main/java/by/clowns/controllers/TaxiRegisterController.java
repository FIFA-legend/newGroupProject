package by.clowns.controllers;

import by.clowns.entity.Comfort;
import by.clowns.entity.Taxi;
import by.clowns.service.TaxiService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class TaxiRegisterController {

    private TaxiService taxiService;

    @Autowired
    public TaxiRegisterController(TaxiService taxiService) {
        this.taxiService = taxiService;
    }

    @ModelAttribute("taxi")
    public Taxi emptyTaxi() {
        return new Taxi();
    }

    @ModelAttribute("comforts")
    public Comfort[] comforts() {
        return taxiService.getAllComforts();
    }

    @GetMapping("/taxi/register")
    public String registerTaxi() {
        return "taxiRegistration";
    }

    @PostMapping("/taxi/register")
    public String saveTaxi(@Valid Taxi taxi, Errors errors) {
        if (errors.hasErrors()) return "taxiRegistration";
        taxiService.create(taxi);
        return "redirect:/cars";
    }

}
