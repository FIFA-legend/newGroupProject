package by.clowns.dao;

import by.clowns.entity.Taxi;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

//@Repository
public class TaxiDaoImpl implements TaxiDao {

    private SessionFactory sessionFactory;

    //@Autowired
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void save(Taxi entity) {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
    }

    @Override
    public Set<Taxi> findAll() {
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        List<Taxi> cars = session.createQuery("SELECT a FROM Taxi a", Taxi.class).getResultList();

        transaction.commit();
        session.close();

        return new HashSet<>(cars);
    }

    @Override
    public void update(Taxi entity, long id) {
        Session session = sessionFactory.openSession();
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
        Session session = sessionFactory.openSession();
        Transaction transaction = session.beginTransaction();

        Taxi car = session.get(Taxi.class, id);
        session.delete(car);

        transaction.commit();
        session.close();
    }

    @Override
    public Taxi findById(long id) {
        Session session = sessionFactory.openSession();

        Taxi car = session.get(Taxi.class, id);

        session.close();
        return car;
    }

    @Override
    public void close() {
        sessionFactory.close();
    }
}
