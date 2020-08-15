package by.clowns.controllers;

import by.clowns.dto.UserDTO;
import by.clowns.entity.Car;
import by.clowns.entity.User;
import by.clowns.service.UserFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.Comparator;
import java.util.Set;
import java.util.TreeSet;

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

    private Comparator<User> userComparator = Comparator.comparing(User::getUsername);

    @GetMapping("/users/{page}")
    public String users(Model model, UserDTO userDTO, @PathVariable int page) {
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
        model.addAttribute("start", userDTO.getStart());
        model.addAttribute("finish", userDTO.getFinish());
        model.addAttribute("users", usersOnPage);
        return "users";
    }

}
