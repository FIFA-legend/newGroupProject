package by.clowns;

import by.clowns.dao.Dao;
import by.clowns.dao.UserDao;
import by.clowns.entity.*;

import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Dao<User> dao = UserDao.getInstance();

        Set<User> users = dao.read();

        System.out.println(users.iterator().hasNext());

    }
}
