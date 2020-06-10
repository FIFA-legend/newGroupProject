package by.clowns;

import by.clowns.configuration.DaoConfiguration;
import by.clowns.dao.UserRepository;
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
@ContextConfiguration(classes = { DaoConfiguration.class, UserRepository.class })
public class UserTests {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void init() {
        Passport passport = new Passport("123qwerty", new Date(2001, 8, 5));
        User user1 = new User("Nikita", "Kolodko", passport, Role.ADMIN);
        User user2 = new User("Name", "Surname", passport, Role.CLIENT);
        User user3 = new User("Lionel", "Messi", passport, Role.CLIENT);
        userRepository.save(user1);
        userRepository.save(user2);
        userRepository.save(user3);
    }

    @Test
    public void findOneTest() {
        User foundUser = userRepository.findById(1L);
        Assert.assertEquals(foundUser.getName(), "Nikita");
    }

    @Test
    public void findAllTest() {
        List<User> users = userRepository.findAll();
        Assert.assertEquals(users.get(1).getSurname(), "Surname");
    }

    @Test
    public void deleteByIdTest() {
        userRepository.deleteById(3L);
        List<User> usersLeft = userRepository.findAll();
        Assert.assertEquals(usersLeft.size(), 2);
    }

}
