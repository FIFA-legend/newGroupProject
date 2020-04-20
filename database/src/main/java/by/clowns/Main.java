package by.clowns;

import by.clowns.dao.*;
import by.clowns.entity.*;


import java.sql.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Dao<Car> carDao = CarDao.getInstance();
        Dao<Region> regionDao = RegionDao.getInstance();
        Dao<Request> requestDao = RequestDao.getInstance();
        Dao<User> userDao = UserDao.getInstance();

        Passport passport = new Passport();
        Date date = new Date(2001, 6, 41);
        passport.setBirth(date);
        passport.setPassportId("MP666");
        User user = new User("Dmitri", "Lugovskoi", passport, Role.ADMIN);
        userDao.create(user);
        userDao.close();
        regionDao.close();
        requestDao.close();
        carDao.close();
    }
}
