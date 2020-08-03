package by.clowns.service;

import by.clowns.dto.UserDTO;
import by.clowns.entity.User;
import by.clowns.repository.UserFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
@ComponentScan("by.clowns.repository")
public class UserFilterService {

    private final UserFilter userFilter;

    @Autowired
    public UserFilterService(UserFilter userFilter) {
        this.userFilter = userFilter;
    }

    public Set<User> filter(UserDTO userDTO) {
        return new HashSet<>(userFilter.filter(userDTO));
    }
}
