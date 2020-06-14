package by.clowns;

import by.clowns.configuration.DaoConfiguration;
import by.clowns.repository.UserRepository;
import by.clowns.entity.Passport;
import by.clowns.entity.Role;
import by.clowns.entity.User;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.sql.Date;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = { DaoConfiguration.class })
public class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        Passport passport = new Passport("Nikita", "Kolodko", "123qwerty", new Date(2001, 8, 5));
        User user1 = new User("Nikita123", "12345", passport, Role.ADMIN);
        User user2 = new User("FIFA_legend", "67890", passport, Role.CLIENT);
        User user3 = new User("Username", "Password", passport, Role.CLIENT);
        User user4 = new User("Tankist", "qwerty", passport, Role.CLIENT);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
        userRepository.save(user4);
    }

    @Test
    public void findOneTest() {
        User foundUser = userRepository.findById(1L);
        Assert.assertEquals(foundUser.getUsername(), "Nikita123");
    }

    @Test
    public void findAllTest() {
        List<User> users = userRepository.findAll();
        Assert.assertEquals(users.get(1).getPassword(), "67890");
    }

    @Test
    public void deleteByIdTest() {
        userRepository.deleteById(3L);
        List<User> usersLeft = userRepository.findAll();
        Assert.assertEquals(usersLeft.size(), 3);
    }

    @Test
    public void updateTest() {
        User foundUser = userRepository.findById(4L);
        foundUser.setUsername("Andrea");
        userRepository.save(foundUser);
        User secondTimeFound = userRepository.findById(4L);
        Assert.assertEquals(secondTimeFound.getUsername(), "Andrea");
    }

}
