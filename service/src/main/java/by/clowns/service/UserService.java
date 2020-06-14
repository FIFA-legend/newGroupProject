package by.clowns.service;

import by.clowns.entity.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.Set;

public interface UserService extends UserDetailsService {

    void create(User entity);

    Set<User> read();

    void update(User entity, long id);

    void delete(long id);

    User get(long id);

}
