package by.clowns.dao;

import by.clowns.entity.Request;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.Set;

public class RequestDao implements Dao<Request> {

    final static SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    private static RequestDao INSTANCE = null;

    private RequestDao(){}

    public static RequestDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new RequestDao();
        }
        return INSTANCE;
    }

    @Override
    public void create(Request entity) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
    }

    @Override
    public Set<Request> reed() {
        return null;
    }

    @Override
    public void update(Request entity, long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Request request = session.get(Request.class, id);
        session.replicate(request, ReplicationMode.OVERWRITE);

        request.setUser(entity.getUser());
        request.setCar(entity.getCar());

        transaction.commit();
        session.close();

    }

    @Override
    public void delete(long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Request request = session.get(Request.class, id);
        session.delete(request);

        transaction.commit();
        session.close();
    }

    @Override
    public Request get(long id) {
        Session session = SESSION_FACTORY.openSession();

        Request request = session.get(Request.class, id);

        session.close();
        return request;
    }

    @Override
    public void close() {
        SESSION_FACTORY.close();
    }
}
