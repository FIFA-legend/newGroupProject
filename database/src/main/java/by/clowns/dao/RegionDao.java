package by.clowns.dao;

import by.clowns.entity.Region;

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
    public void save(Region entity) {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        session.save(entity);

        transaction.commit();
        session.close();
    }

    @Override
    public Set<Region> findAll() {
        Session session = SESSION_FACTORY.openSession();
        Transaction transaction = session.beginTransaction();

        List<Region> regions = session.createQuery("SELECT a FROM Region a", Region.class).getResultList();

        transaction.commit();
        session.close();

        return new HashSet<>(regions);
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
    public Region findById(long id) {
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
