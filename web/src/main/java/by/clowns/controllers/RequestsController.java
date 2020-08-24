package by.clowns.controllers;

import by.clowns.entity.RentRequest;
import by.clowns.service.RequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class RequestsController {

    private final RequestService requestService;

    @Autowired
    public RequestsController(RequestService requestService) {
        this.requestService = requestService;
    }

    private final Comparator<RentRequest> requestComparator = (o1, o2) -> {
        if (!o1.getUser().getUsername().equals(o2.getUser().getUsername())) {
            return o1.getUser().getUsername().compareTo(o2.getUser().getUsername());
        } else if (!o1.getCar().getBrand().equals(o2.getCar().getBrand())) {
            return o1.getCar().getBrand().compareTo(o2.getCar().getBrand());
        } else {
            return o1.getCar().getModel().compareTo(o2.getCar().getModel());
        }
    };

    @GetMapping("/requests/{page}")
    public String showRequests(@PathVariable int page, Model model) {
        Set<RentRequest> requests = new TreeSet<>(requestComparator);
        requests.addAll(requestService.read());
        Set<RentRequest> requestsOnPage = new TreeSet<>(requestComparator);
        int coefficient = page - 1;
        int i = 0;
        for (RentRequest request : requests) {
            if (i / 10 == coefficient) {
                requestsOnPage.add(request);
            }
            i++;
        }
        int allPages = (requests.size() - 1) / 10 + 1;
        model.addAttribute("page", page);
        model.addAttribute("allPages", allPages);
        model.addAttribute("requests", requestsOnPage);
        return "requests";
    }
}
