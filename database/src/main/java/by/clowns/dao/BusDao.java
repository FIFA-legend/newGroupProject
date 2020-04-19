package by.clowns.dao;

import by.clowns.entity.Bus;
import by.clowns.entity.Car;
import by.clowns.entity.Truck;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class BusDao implements Dao<Bus> {

    final static SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    private static BusDao INSTANCE = null;

    private BusDao(){}

    public static BusDao getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new BusDao();
        }
        return INSTANCE;
    }

    @Override
    public void create(Bus entity) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
    }

    @Override
    public Set<Bus> reed() {
        Set<Bus> cars = new HashSet<>();
        long id = 1;

        while (true) {
            Bus car;
            car = this.get(id);
            id++;
            if (car != null) {
                cars.add(car);
            } else {
                return cars;
            }
        }
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
    public Bus get(long id) {
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
