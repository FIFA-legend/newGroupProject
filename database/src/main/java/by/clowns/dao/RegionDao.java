package by.clowns.dao;

import by.clowns.entity.Region;
import by.clowns.entity.User;
import org.hibernate.ReplicationMode;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import java.util.HashSet;
import java.util.Set;

public class RegionDao implements Dao<Region> {

    static SessionFactory SESSION_FACTORY;

    private static RegionDao INSTANCE = null;

    private RegionDao(){}

    public static RegionDao getInstance() {
        if(INSTANCE == null) {
            INSTANCE = new RegionDao();
        }
        SESSION_FACTORY = new Configuration().configure().buildSessionFactory();
        return INSTANCE;
    }

    @Override
    public void create(Region entity) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
    }

    @Override
    public Set<Region> reed() {
        Set<Region> regions = new HashSet<>();
        long id = 1;

        while (true) {
            Region region;
            region = this.get(id);
            id++;
            if (region != null) {
                regions.add(region);
            } else {
                return regions;
            }
        }
    }

    @Override
    public void update(Region entity, long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Region region = session.get(Region.class, id);
        session.replicate(region, ReplicationMode.OVERWRITE);

        region.setName(entity.getName());
        region.setCars(entity.getCars());

        transaction.commit();
        session.close();
    }

    @Override
    public void delete(long id) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        Region region = session.get(Region.class, id);
        session.delete(region);

        transaction.commit();
        session.close();
    }

    @Override
    public Region get(long id) {
        Session session = SESSION_FACTORY.openSession();

        Region region = session.get(Region.class, id);

        session.close();
        return region;
    }

    @Override
    public void close() {
        SESSION_FACTORY.close();
    }
}
