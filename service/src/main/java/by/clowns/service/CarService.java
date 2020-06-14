package by.clowns.service;

import by.clowns.dao.CarDao;
import by.clowns.dao.Dao;
import by.clowns.entity.Car;

import java.util.Set;

public class CarService implements ServiceInterface<Car> {

    private CarService(){}

    private static CarService INSTANCE = null;

    public static CarService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CarService();
        }
        return INSTANCE;
    }

    @Override
    public void create(Car entity) {
        Dao<Car> dao = CarDao.getInstance();
        dao.save(entity);
        dao.close();
    }

    @Override
    public Set<Car> read() {
        Dao<Car> dao = CarDao.getInstance();
        Set<Car> set = dao.findAll();
        dao.close();
        return set;
    }

    @Override
    public void update(Car entity, long id) {
        Dao<Car> dao = CarDao.getInstance();
        dao.update(entity, id);
        dao.close();
    }

    @Override
    public void delete(long id) {
        Dao<Car> dao = CarDao.getInstance();
        dao.delete(id);
        dao.close();
    }

    @Override
    public Car get(long id) {
        Dao<Car> dao = CarDao.getInstance();
        Car entity = dao.findById(id);
        dao.close();
        return entity;
    }
}
