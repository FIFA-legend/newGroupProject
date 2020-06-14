package by.clowns.service;

import by.clowns.repository.UserRepository;
import by.clowns.entity.User;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class UserService {

    @Autowired
    private UserRepository userRepository;

    private UserService(){}

    private static UserService INSTANCE = null;

    public static UserService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

    public void create(User entity) {
        userRepository.save(entity);
    }

    public Set<User> read() {
        List<User> list = userRepository.findAll();
        return new TreeSet<>(list);
    }

    public void update(User entity, long id) {
        userRepository.save(entity);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public User get(long id) {
        return userRepository.findById(id);
    }
}
