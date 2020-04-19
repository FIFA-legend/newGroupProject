package by.clowns;

import by.clowns.dao.UserDao;
import by.clowns.entity.User;

import java.util.Set;

public class UserService {

    public Set<User> getAllUsers() {
        return UserDao.getInstance().read();
    }
}
