package by.clowns.controllers;

import by.clowns.entity.Region;
import by.clowns.repository.RegionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class RegionRegisterController {

    private RegionRepository regionRepository;

    @Autowired
    public RegionRegisterController(RegionRepository regionRepository) {
        this.regionRepository = regionRepository;
    }

    @ModelAttribute("region")
    public Region emptyRegion() {
        return new Region();
    }

    @GetMapping("/region/register")
    public String registerRegion() {
        return "regionRegistration";
    }

    @PostMapping(value = "/region/register", produces = "text/html; charset=utf-8")
    public String saveRegion(@Valid Region region, Errors errors) {
        if (errors.hasErrors()) return "regionRegistration";
        regionRepository.save(region);
        return "regionRegistration";
    }
}
