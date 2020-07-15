package by.clowns.controllers;

import by.clowns.entity.Car;
import by.clowns.entity.RentRequest;
import by.clowns.entity.User;
import by.clowns.service.RequestService;
import by.clowns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Map;

@Controller
public class RequestController {

    private final UserService userService;

    private final RequestService requestService;

    private Map<String, Object> map;

    @Autowired
    public RequestController(UserService userService, RequestService requestService) {
        this.userService = userService;
        this.requestService = requestService;
    }

    @GetMapping("/request/save")
    public String getRequestPage(Model model) {
        Object sessionUser = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        String username;
        if (sessionUser instanceof UserDetails) {
            username = ((UserDetails)sessionUser).getUsername();
        } else {
            username = sessionUser.toString();
        }
        model.addAttribute("user", userService.get(username));
        if (model.containsAttribute("car")) {
            map = model.asMap();
            return "request";
        }
        else return "redirect:/cars";
    }

    @PostMapping("/request/save")
    public String saveRequest() {
        User user;
        Car car;
        try {
            if (map.get("user") instanceof User) {
                if (map.get("car") instanceof Car) {
                    user = (User) map.get("user");
                    car = (Car) map.get("car");
                    RentRequest request = new RentRequest(user, car);
                    requestService.create(request);
                    return "successRequest";
                }
            }
            return "failRequest";
        } catch (Exception e) {
            return "failRequest";
        }
    }

}
