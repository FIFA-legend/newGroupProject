package by.clowns.service;

import by.clowns.dao.Dao;
import by.clowns.dao.RequestDao;
import by.clowns.entity.Request;

import java.util.Set;

public class RequestService implements Service<Request> {

    private RequestService(){}

    private static RequestService INSTANCE = null;

    public static RequestService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RequestService();
        }
        return INSTANCE;
    }

    @Override
    public void create(Request entity) {
        Dao<Request> dao = RequestDao.getInstance();
        dao.create(entity);
        dao.close();
    }

    @Override
    public Set<Request> read() {
        Dao<Request> dao = RequestDao.getInstance();
        Set<Request> set = dao.read();
        dao.close();
        return set;
    }

    @Override
    public void update(Request entity, long id) {
        Dao<Request> dao = RequestDao.getInstance();
        dao.update(entity, id);
        dao.close();
    }

    @Override
    public void delete(long id) {
        Dao<Request> dao = RequestDao.getInstance();
        dao.delete(id);
        dao.close();
    }

    @Override
    public Request get(long id) {
        Dao<Request> dao = RequestDao.getInstance();
        Request entity = dao.get(id);
        dao.close();
        return entity;
    }
}
