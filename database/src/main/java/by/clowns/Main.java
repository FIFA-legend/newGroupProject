package by.clowns;

import by.clowns.dao.*;
import by.clowns.entity.*;


import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Dao<Car> carDao = CarDao.getInstance();
        Dao<Region> regionDao = RegionDao.getInstance();
        Dao<Request> requestDao = RequestDao.getInstance();
        Dao<User> userDao = UserDao.getInstance();

        Car car = carDao.get(1);

        carDao.close();
        regionDao.close();
        requestDao.close();
        System.out.println();
    }
}
