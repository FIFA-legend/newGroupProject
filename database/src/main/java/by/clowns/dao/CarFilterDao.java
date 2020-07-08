package by.clowns.dao;

import by.clowns.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.List;

@Repository
@Transactional
@Scope("singleton")
public class CarFilterDao {

    private EntityManagerFactory entityManagerFactory;

    @Autowired
    public CarFilterDao(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    public List<Car> carFilterQuery(Car car){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Car> carCriteria = cb.createQuery(Car.class);
        Root<Car> carRoot = carCriteria.from(Car.class);
        carCriteria.select(carRoot);
        if(!car.getNumber().isEmpty()) {
            carCriteria.where(cb.equal(carRoot.get("number"), car.getNumber()));
        }
        if(car.getPrice() > 0) {
            carCriteria.where(cb.greaterThanOrEqualTo(carRoot.get("price"), car.getPrice()));
        }
        return em.createQuery(carCriteria)
                .getResultList();
    }
}
