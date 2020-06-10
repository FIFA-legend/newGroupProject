package by.clowns.dao;

import by.clowns.entity.Bus;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class BusDao implements Dao<Bus> {

    static SessionFactory SESSION_FACTORY;

    private static BusDao INSTANCE = null;

    private BusDao(){}

    public static BusDao getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BusDao();
        }
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        return INSTANCE;
    }

    @Override
    public void save(Bus entity) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
    }

    @Override
    public Set<Bus> findAll() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        List<Bus> cars = session.createQuery("SELECT a FROM Bus a", Bus.class).getResultList();

        transaction.commit();
        session.close();

        return new HashSet<>(cars);
    }

    @Override
    public void update(Bus entity, long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Bus car = session.get(Bus.class, id);
        session.replicate(car, ReplicationMode.OVERWRITE);

        car.setPrice(entity.getPrice());
        car.setNumber(entity.getNumber());
        car.setRegions(entity.getRegions());
        car.setRequest(entity.getRequest());
        car.setCapacity(entity.getCapacity());

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Bus car = session.get(Bus.class, id);
        session.delete(car);

        transaction.commit();
        session.close();
    }

    @Override
    public Bus findById(long id) {
        Session session = SESSION_FACTORY.openSession();

        Bus car = session.get(Bus.class, id);

        session.close();
        return car;
    }

    @Override
    public void close() {
        SESSION_FACTORY.close();
    }
}
