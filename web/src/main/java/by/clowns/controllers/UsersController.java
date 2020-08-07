package by.clowns.controllers;

import by.clowns.dto.UserDTO;
import by.clowns.service.UserFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;

@Controller
public class UsersController {

    private final UserFilterService userFilterService;

    @Autowired
    public UsersController(UserFilterService userFilterService) {
        this.userFilterService = userFilterService;
    }

    @ModelAttribute("modelUser")
    public UserDTO modelUser() {
        return new UserDTO();
    }

    @GetMapping("/users")
    public String users(Model model, UserDTO userDTO) {
        model.addAttribute("users", userFilterService.filter(userDTO));
        return "users";
    }

}
