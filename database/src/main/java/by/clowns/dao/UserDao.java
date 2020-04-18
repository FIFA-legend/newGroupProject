package by.clowns.dao;

import by.clowns.entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class UserDao implements Dao<User> {

    final static SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    private static UserDao INSTANCE = null;

    private UserDao(){};

    public static UserDao getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new UserDao();
        }
        return INSTANCE;
    }

    @Override
    public void create(User entity) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();
        session.save(entity);
        transaction.commit();
        session.close();
        SESSION_FACTORY.close();
    }

    @Override
    public Set<User> reed() {

        Session session = SESSION_FACTORY.openSession();

        Set<User> users = new HashSet<>();

        Transaction transaction = session.beginTransaction();

        for (long i = 0; session.get(User.class, i) != null; i++){
            users.add(session.get(User.class, i));
        }

        transaction.commit();
        session.close();
        SESSION_FACTORY.close();
        return users;
    }

    @Override
    public void update(User entity, long id) {

        Session session = SESSION_FACTORY.openSession();

        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, id);

        session.persist(user);

        user = entity;

        transaction.commit();
        session.close();
        SESSION_FACTORY.close();
    }

    @Override
    public void delete(long id) {

        Session session = SESSION_FACTORY.openSession();

        User user = session.get(User.class, id);
        session.delete(user);

        session.close();
        SESSION_FACTORY.close();
    }

    @Override
    public User get(long id) {

        Session session = SESSION_FACTORY.openSession();

        User user = session.get(User.class, id);

        session.close();
        SESSION_FACTORY.close();
        return user;
    }
}
