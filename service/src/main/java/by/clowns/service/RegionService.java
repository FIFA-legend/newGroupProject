package by.clowns.service;

import by.clowns.dao.Dao;
import by.clowns.dao.RegionDao;
import by.clowns.entity.Region;

import java.util.Set;

public class RegionService implements Service<Region> {

    private RegionService(){}

    private static RegionService INSTANCE = null;

    public static RegionService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RegionService();
        }
        return INSTANCE;
    }

    @Override
    public void create(Region entity) {
        Dao<Region> dao = RegionDao.getInstance();
        dao.create(entity);
        dao.close();
    }

    @Override
    public Set<Region> read() {
        Dao<Region> dao = RegionDao.getInstance();
        Set<Region> set = dao.read();
        dao.close();
        return set;
    }

    @Override
    public void update(Region entity, long id) {
        Dao<Region> dao = RegionDao.getInstance();
        dao.update(entity, id);
        dao.close();
    }

    @Override
    public void delete(long id) {
        Dao<Region> dao = RegionDao.getInstance();
        dao.delete(id);
        dao.close();
    }

    @Override
    public Region get(long id) {
        Dao<Region> dao = RegionDao.getInstance();
        Region entity = dao.get(id);
        dao.close();
        return entity;
    }
}
