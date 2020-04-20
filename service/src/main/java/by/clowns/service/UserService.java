package by.clowns.service;

import by.clowns.dao.Dao;
import by.clowns.dao.UserDao;
import by.clowns.entity.User;

import java.util.Set;

public class UserService implements Service<User> {

    private UserService(){}

    private static UserService INSTANCE = null;

    public static UserService getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserService();
        }
        return INSTANCE;
    }

    @Override
    public void create(User entity) {
        Dao<User> dao = UserDao.getInstance();
        dao.create(entity);
        dao.close();
    }

    @Override
    public Set<User> read() {
        Dao<User> dao = UserDao.getInstance();
        Set<User> set = dao.read();
        dao.close();
        return set;
    }

    @Override
    public void update(User entity, long id) {
        Dao<User> dao = UserDao.getInstance();
        dao.update(entity, id);
        dao.close();
    }

    @Override
    public void delete(long id) {
        Dao<User> dao = UserDao.getInstance();
        dao.delete(id);
        dao.close();
    }

    @Override
    public User get(long id) {
        Dao<User> dao = UserDao.getInstance();
        User entity = dao.get(id);
        dao.close();
        return entity;
    }
}
