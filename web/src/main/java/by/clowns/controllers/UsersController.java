package by.clowns.controllers;

import by.clowns.dto.UserDTO;
import by.clowns.entity.Role;
import by.clowns.entity.User;
import by.clowns.service.UserFilterService;
import by.clowns.service.serviceImpl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import java.sql.Date;
import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

@Controller
public class UsersController {

    private final UserFilterService userFilterService;

    private final UserServiceImpl userService;

    @Autowired
    public UsersController(UserFilterService userFilterService, UserServiceImpl userService) {
        this.userFilterService = userFilterService;
        this.userService = userService;
    }

    @ModelAttribute("modelUser")
    public UserDTO modelUser() {
        return new UserDTO(new Date(20, 1,1), new Date(System.currentTimeMillis()));
    }

    @ModelAttribute("admin")
    public Role admin() {
        return userService.getAllRoles()[1];
    }

    private final Comparator<User> userComparator = Comparator.comparing(User::getUsername);

    @GetMapping("/users/{page}")
    public String users(Model model, UserDTO userDTO, @PathVariable int page,
                        @RequestParam(required = false) String admin) {
        User newAdmin = userService.get(admin);
        if (newAdmin != null) {
            if (newAdmin.getRole() == Role.CLIENT) {
                newAdmin.setRole(Role.ADMIN);
            } else {
                newAdmin.setRole(Role.CLIENT);
            }
            userService.update(newAdmin);
        }

        Set<User> users = new TreeSet<>(userComparator);
        users.addAll(userFilterService.filter(userDTO));
        Set<User> usersOnPage = new TreeSet<>(userComparator);
        int coefficient = page - 1;
        int i = 0;
        for (User user : users) {
            if (i / 10 == coefficient) {
                usersOnPage.add(user);
            }
            i++;
        }
        int allPages = (users.size() - 1) / 10 + 1;

        model.addAttribute("page", page);
        model.addAttribute("allPages", allPages);
        model.addAttribute("username", userDTO.getUsername());
        model.addAttribute("realName", userDTO.getRealName());
        model.addAttribute("realSurname", userDTO.getRealSurname());
        if (userDTO.getStart() == null) {
            model.addAttribute("start", new Date(20, 1,1));
        } else {
            model.addAttribute("start", userDTO.getStart());
        }
        if (userDTO.getFinish() == null) {
            model.addAttribute("finish", new Date(System.currentTimeMillis()));
        } else {
            model.addAttribute("finish", userDTO.getFinish());
        }
        model.addAttribute("users", usersOnPage);
        return "users";
    }

}
