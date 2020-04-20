package by.clowns;

import by.clowns.dao.*;
import by.clowns.entity.*;

public class Main {
    public static void main(String[] args) {

        Dao<Car> carDao = CarDao.getInstance();
        Dao<Region> regionDao = RegionDao.getInstance();
        Dao<Request> requestDao = RequestDao.getInstance();
        Dao<User> userDao = UserDao.getInstance();

        User user = userDao.get(1);
        Car car = carDao.get(1);
        Request request = new Request(user, car);
        requestDao.create(request);
    }
}
