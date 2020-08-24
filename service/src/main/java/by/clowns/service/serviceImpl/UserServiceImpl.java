package by.clowns.service.serviceImpl;

import by.clowns.repository.UserRepository;
import by.clowns.entity.User;
import by.clowns.entity.Role;
import by.clowns.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class UserServiceImpl implements UserDetailsService, UserService {

    private UserRepository userRepository;

    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Autowired
    public void setUserRepository(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Override
    public void create(User entity) {
        entity.setPassword(bCryptPasswordEncoder.encode(entity.getPassword()));
        userRepository.save(entity);
    }

    @Override
    public Set<User> read() {
        List<User> list = userRepository.findAll();
        return new HashSet<>(list);
    }

    @Override
    public void update(User entity) {
        userRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User get(long id) {
        return userRepository.findById(id);
    }

    @Override
    public User get(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
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

