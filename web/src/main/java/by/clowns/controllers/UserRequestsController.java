package by.clowns.controllers;

import by.clowns.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserRequestsController {

    private final UserServiceImpl userService;

    @Autowired
    public UserRequestsController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @GetMapping("/user/{id}/requests")
    public String getUserRequests(@PathVariable Long id, Model model) {
        model.addAttribute("user", userService.get(id));
        return "userRequests";
    }

}
