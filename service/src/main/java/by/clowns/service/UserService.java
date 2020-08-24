package by.clowns.service;

import by.clowns.entity.Role;
import by.clowns.entity.User;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface UserService {

    void create(User entity);

    Set<User> read();

    void update(User entity);

    void delete(long id);

    User get(long id);

    User get(String username);

    Role[] getAllRoles();

}
