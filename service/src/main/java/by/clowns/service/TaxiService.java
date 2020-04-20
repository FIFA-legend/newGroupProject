package by.clowns.service;

import by.clowns.dao.Dao;
import by.clowns.dao.TaxiDao;
import by.clowns.entity.Taxi;

import java.util.Set;

public class TaxiService implements Service<Taxi> {

    private TaxiService(){}

    private static TaxiService INSTANCE = null;

    public static TaxiService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new TaxiService();
        }
        return INSTANCE;
    }

    @Override
    public void create(Taxi entity) {
        Dao<Taxi> dao = TaxiDao.getInstance();
        dao.create(entity);
        dao.close();
    }

    @Override
    public Set<Taxi> read() {
        Dao<Taxi> dao = TaxiDao.getInstance();
        Set<Taxi> set = dao.read();
        dao.close();
        return set;
    }

    @Override
    public void update(Taxi entity, long id) {
        Dao<Taxi> dao = TaxiDao.getInstance();
        dao.update(entity, id);
        dao.close();
    }

    @Override
    public void delete(long id) {
        Dao<Taxi> dao = TaxiDao.getInstance();
        dao.delete(id);
        dao.close();
    }

    @Override
    public Taxi get(long id) {
        Dao<Taxi> dao = TaxiDao.getInstance();
        Taxi entity = dao.get(id);
        dao.close();
        return entity;
    }
}
