package by.clowns.service;

import by.clowns.dao.Dao;
import by.clowns.dao.TaxiDaoImpl;
import by.clowns.entity.Taxi;

import java.util.Set;

public class TaxiService  {

    /*private TaxiService(){}

    private static TaxiService INSTANCE = null;

    public static TaxiService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new TaxiService();
        }
        return INSTANCE;
    }

    @Override
    public void create(Taxi entity) {
        Dao<Taxi> dao = TaxiDaoImpl.getInstance();
        dao.save(entity);
        dao.close();
    }

    @Override
    public Set<Taxi> read() {
        Dao<Taxi> dao = TaxiDaoImpl.getInstance();
        Set<Taxi> set = dao.findAll();
        dao.close();
        return set;
    }

    @Override
    public void update(Taxi entity, long id) {
        Dao<Taxi> dao = TaxiDaoImpl.getInstance();
        dao.update(entity, id);
        dao.close();
    }

    @Override
    public void delete(long id) {
        Dao<Taxi> dao = TaxiDaoImpl.getInstance();
        dao.delete(id);
        dao.close();
    }

    @Override
    public Taxi get(long id) {
        Dao<Taxi> dao = TaxiDaoImpl.getInstance();
        Taxi entity = dao.findById(id);
        dao.close();
        return entity;
    }*/
}
