package by.clowns;

import by.clowns.dao.CarDao;
import by.clowns.dao.Dao;
import by.clowns.dao.RegionDao;
import by.clowns.dao.UserDao;
import by.clowns.entity.*;


import java.util.HashSet;
import java.util.Set;

public class Main {
    public static void main(String[] args) {

        Dao<Car> carDao = CarDao.getInstance();
        Dao<Region> regionDao = RegionDao.getInstance();

        Car car = carDao.get(1);

        carDao.close();
        regionDao.close();
        System.out.println(car);
    }
}
