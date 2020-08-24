package by.clowns.service;

import by.clowns.dto.UserDTO;
import by.clowns.entity.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserFilterService {

    Set<User> filter(UserDTO userDTO);

}
