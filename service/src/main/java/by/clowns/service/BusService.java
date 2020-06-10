package by.clowns.service;

import by.clowns.dao.BusDao;
import by.clowns.dao.Dao;
import by.clowns.entity.Bus;

import java.util.Set;

public class BusService implements Service<Bus> {

    private BusService(){}

    private static BusService INSTANCE = null;

    public static BusService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BusService();
        }
        return INSTANCE;
    }

    @Override
    public void create(Bus entity) {
        Dao<Bus> dao = BusDao.getInstance();
        dao.save(entity);
        dao.close();
    }

    @Override
    public Set<Bus> read() {
        Dao<Bus> dao = BusDao.getInstance();
        Set<Bus> set = dao.findAll();
        dao.close();
        return set;
    }

    @Override
    public void update(Bus entity, long id) {
        Dao<Bus> dao = BusDao.getInstance();
        dao.update(entity, id);
        dao.close();
    }

    @Override
    public void delete(long id) {
        Dao<Bus> dao = BusDao.getInstance();
        dao.delete(id);
        dao.close();
    }

    @Override
    public Bus get(long id) {
        Dao<Bus> dao = BusDao.getInstance();
        Bus entity = dao.findById(id);
        dao.close();
        return entity;
    }
}
