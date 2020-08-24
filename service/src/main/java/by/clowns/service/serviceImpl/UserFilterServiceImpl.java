package by.clowns.service.serviceImpl;

import by.clowns.dto.UserDTO;
import by.clowns.entity.User;
import by.clowns.repository.UserFilter;
import by.clowns.service.UserFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@ComponentScan("by.clowns.repository")
public class UserFilterServiceImpl implements UserFilterService {

    private final UserFilter userFilter;

    @Autowired
    public UserFilterServiceImpl(UserFilter userFilter) {
        this.userFilter = userFilter;
    }

    @Override
    public Set<User> filter(UserDTO userDTO) {
        return new HashSet<>(userFilter.filter(userDTO));
    }
}
