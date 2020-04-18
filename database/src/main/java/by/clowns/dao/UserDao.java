package by.clowns.dao;

import by.clowns.entity.User;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class UserDao implements Dao<User> {

    final static SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    private static UserDao INSTANCE = null;

    private UserDao(){}

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
    }

    @Override
    public Set<User> reed() {
        Set<User> users = new HashSet<>();
        long id = 1;

        while (true) {
            User user;
            user = this.get(id);
            id++;
            if (user != null) {
                users.add(user);
            } else {
                return users;
            }
        }
    }

    @Override
    public void update(User entity, long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, id);
        session.replicate(user, ReplicationMode.OVERWRITE);

        user.setName(entity.getName());
        user.setSurname(entity.getSurname());
        user.setPassport(entity.getPassport());
        user.setRole(entity.getRole());

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, id);
        session.delete(user);

        transaction.commit();
        session.close();
    }

    @Override
    public User get(long id) {
        Session session = SESSION_FACTORY.openSession();

        User user = session.get(User.class, id);

        session.close();
        return user;
    }

    @Override
    public void close() {
        SESSION_FACTORY.close();
    }
}
