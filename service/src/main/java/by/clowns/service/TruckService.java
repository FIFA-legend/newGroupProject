package by.clowns.service;

import by.clowns.dao.Dao;
import by.clowns.dao.TruckDao;
import by.clowns.entity.Truck;

import java.util.Set;

public class TruckService implements Service<Truck> {

    private TruckService(){}

    private static TruckService INSTANCE = null;

    public static TruckService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new TruckService();
        }
        return INSTANCE;
    }

    @Override
    public void create(Truck entity) {
        Dao<Truck> dao = TruckDao.getInstance();
        dao.save(entity);
        dao.close();
    }

    @Override
    public Set<Truck> read() {
        Dao<Truck> dao = TruckDao.getInstance();
        Set<Truck> set = dao.findAll();
        dao.close();
        return set;
    }

    @Override
    public void update(Truck entity, long id) {
        Dao<Truck> dao = TruckDao.getInstance();
        dao.update(entity, id);
        dao.close();
    }

    @Override
    public void delete(long id) {
        Dao<Truck> dao = TruckDao.getInstance();
        dao.delete(id);
        dao.close();
    }

    @Override
    public Truck get(long id) {
        Dao<Truck> dao = TruckDao.getInstance();
        Truck entity = dao.findById(id);
        dao.close();
        return entity;
    }
}
