package by.clowns;

import by.clowns.dao.Dao;
import by.clowns.dao.UserDao;
import by.clowns.entity.*;

import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Dao<User> dao = UserDao.getInstance();

//        Set<User> users = dao.reed();
        User user = new User("Nik", "Krutoi", null, Role.CLIENT);

        dao.update(user, 1);

//        System.out.println(users.iterator().hasNext());

    }
}
