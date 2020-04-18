package by.clowns;

import by.clowns.entity.*;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Main {
    public static void main(String[] args) {
        final Configuration configuration = new Configuration().configure();
        final SessionFactory sessionFactory = configuration.buildSessionFactory();
        final Session session = sessionFactory.openSession();
        final Transaction transaction = session.beginTransaction();

        User user = session.get(User.class, 1L);
        Car car = session.get(Car.class, 1L);
        System.out.println(user);
        transaction.commit();

        session.close();
        sessionFactory.close();
    }
}
