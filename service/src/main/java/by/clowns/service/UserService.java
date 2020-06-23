package by.clowns.service;

import by.clowns.repository.UserRepository;
import by.clowns.entity.User;
import by.clowns.entity.Role;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class UserService implements ServiceInterface<User>, UserDetailsService {

    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void create(User entity) {
        userRepository.save(entity);
    }

    @Override
    public Set<User> read() {
        List<User> list = userRepository.findAll();
        return new HashSet<>(list);
    }

    @Override
    public void update(User entity, long id) {
        User foundUser = userRepository.findById(id);
        foundUser.setUsername(entity.getUsername());
        foundUser.setPassword(entity.getPassword());
        foundUser.setPassport(entity.getPassport());
        foundUser.setRole(entity.getRole());
        foundUser.setRequests(entity.getRequests());
        userRepository.save(foundUser);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User get(long id) {
        return userRepository.findById(id);
    }

    public Role[] getAllRoles() {
        return Role.values();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        return new org.springframework.security.core.userdetails.User(user.getUsername(), user.getPassword(), convertRoles(user));
    }

    private Collection<GrantedAuthority> convertRoles(User user) {
        Set<GrantedAuthority> set = new HashSet<>();
        set.add(new SimpleGrantedAuthority(user.getRole().toString()));
        return set;
    }

}

