package by.clowns.dao;

import by.clowns.entity.Car;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class CarDao implements Dao<Car> {

    static SessionFactory SESSION_FACTORY;

    private static CarDao INSTANCE = null;

    private CarDao(){}

    public static CarDao getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new CarDao();
        }
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        return INSTANCE;
    }

    @Override
    public void create(Car entity) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
    }

    @Override
    public Set<Car> read() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        List<Car> cars = session.createQuery("SELECT a FROM Car a", Car.class).getResultList();

        transaction.commit();
        session.close();

        return new HashSet<>(cars);
    }

    @Override
    public void update(Car entity, long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Car car = session.get(Car.class, id);
        session.replicate(car, ReplicationMode.OVERWRITE);

        car.setPrice(entity.getPrice());
        car.setNumber(entity.getNumber());
        car.setRegions(entity.getRegions());
        car.setRequest(entity.getRequest());

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Car car = session.get(Car.class, id);
        session.delete(car);

        transaction.commit();
        session.close();
    }

    @Override
    public Car get(long id) {
        Session session = SESSION_FACTORY.openSession();

        Car car = session.get(Car.class, id);

        session.close();
        return car;
    }

    @Override
    public void close() {
        SESSION_FACTORY.close();
    }
}
