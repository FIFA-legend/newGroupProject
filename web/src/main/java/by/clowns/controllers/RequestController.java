package by.clowns.controllers;

import by.clowns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class RequestController {

    private UserService userService;

    @Autowired
    public RequestController(UserService userService) {
        this.userService = userService;
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
        if (model.containsAttribute("car")) return "request";
        else return "redirect:/cars";
    }

}
