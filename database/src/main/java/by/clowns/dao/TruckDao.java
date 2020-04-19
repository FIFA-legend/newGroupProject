package by.clowns.dao;

import by.clowns.entity.Car;
import by.clowns.entity.Truck;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class TruckDao implements Dao<Truck> {

    final static SessionFactory SESSION_FACTORY = new Configuration().configure().buildSessionFactory();

    private static TruckDao INSTANCE = null;

    private TruckDao(){}

    public static TruckDao getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new TruckDao();
        }
        return INSTANCE;
    }

    @Override
    public void create(Truck entity) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
    }

    @Override
    public Set<Truck> read() {
        Set<Truck> cars = new HashSet<>();
        long id = 1;

        while (true) {
            Truck car;
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
    public void update(Truck entity, long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Truck car = session.get(Truck.class, id);
        session.replicate(car, ReplicationMode.OVERWRITE);

        car.setPrice(entity.getPrice());
        car.setNumber(entity.getNumber());
        car.setRegions(entity.getRegions());
        car.setRequest(entity.getRequest());
        car.setCarrying(entity.getCarrying());

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Truck car = session.get(Truck.class, id);
        session.delete(car);

        transaction.commit();
        session.close();
    }

    @Override
    public Truck get(long id) {
        Session session = SESSION_FACTORY.openSession();

        Truck car = session.get(Truck.class, id);

        session.close();
        return car;
    }

    @Override
    public void close() {

    }
}
