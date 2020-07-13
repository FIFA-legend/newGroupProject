package by.clowns.controllers;

import by.clowns.entity.Passport;
import by.clowns.entity.Role;
import by.clowns.entity.User;
import by.clowns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.validation.Valid;

@Controller
public class UserRegisterController {

    private UserService userService;

    @Autowired
    public UserRegisterController(UserService userService) {
        this.userService = userService;
    }

    @ModelAttribute("user")
    public User emptyUser() {
        return new User();
    }

    @ModelAttribute("passport")
    public Passport emptyPassport() {
        return new Passport();
    }

    @ModelAttribute("roles")
    public Role[] allRoles() {
        return userService.getAllRoles();
    }

    @GetMapping("/user/register")
    public String register() {
        return "registrationPage";
    }

    @PostMapping("/user/register")
    public String getRegister(@Valid User user, Errors userErrors, @Valid Passport passport, Errors passportErrors) {
        if (userErrors.hasErrors() || passportErrors.hasErrors()) return "registrationPage";
        user.setPassport(passport);
        userService.create(user);
        return "redirect:/login";
    }

}
