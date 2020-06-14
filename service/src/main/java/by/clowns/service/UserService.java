package by.clowns.service;

import by.clowns.dao.UserRepository;
import by.clowns.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    public void create(User entity) {
        userRepository.save(entity);
    }

    public Set<User> read() {
        List<User> list = userRepository.findAll();
        return new HashSet<>(list);
    }

    public void update(User entity, long id) {
        User foundUser = userRepository.findById(id);
        foundUser.setName(entity.getName());
        foundUser.setSurname(entity.getSurname());
        foundUser.setPassport(entity.getPassport());
        foundUser.setRole(entity.getRole());
        foundUser.setRequests(entity.getRequests());
        userRepository.save(foundUser);
    }

    public void delete(long id) {
        userRepository.deleteById(id);
    }

    public User get(long id) {
        return userRepository.findById(id);
    }
}
