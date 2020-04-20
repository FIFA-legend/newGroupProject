package by.clowns.dao;

import by.clowns.entity.Car;
import by.clowns.entity.Taxi;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TaxiDao implements Dao<Taxi> {

    static SessionFactory SESSION_FACTORY;

    private static TaxiDao INSTANCE = null;

    private TaxiDao(){}

    public static TaxiDao getInstance() {
        if (INSTANCE == null) {
            INSTANCE = new TaxiDao();
        }
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        return INSTANCE;
    }

    @Override
    public void create(Taxi entity) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
    }

    @Override
    public Set<Taxi> read() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        List<Taxi> cars = session.createQuery("SELECT a FROM Taxi a", Taxi.class).getResultList();

        transaction.commit();
        session.close();

        return new HashSet<>(cars);
    }

    @Override
    public void update(Taxi entity, long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Taxi car = session.get(Taxi.class, id);
        session.replicate(car, ReplicationMode.OVERWRITE);

        car.setPrice(entity.getPrice());
        car.setNumber(entity.getNumber());
        car.setRegions(entity.getRegions());
        car.setRequest(entity.getRequest());
        car.setComfort(entity.getComfort());

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Taxi car = session.get(Taxi.class, id);
        session.delete(car);

        transaction.commit();
        session.close();
    }

    @Override
    public Taxi get(long id) {
        Session session = SESSION_FACTORY.openSession();

        Taxi car = session.get(Taxi.class, id);

        session.close();
        return car;
    }

    @Override
    public void close() {
        SESSION_FACTORY.close();
    }
}
