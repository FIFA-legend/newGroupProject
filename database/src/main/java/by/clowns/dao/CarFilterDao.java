package by.clowns.dao;

import by.clowns.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
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
        Predicate[] predicates = new Predicate[3];
        if(car.getNumber() != null && !car.getNumber().isEmpty()) {
            predicates[0] = cb.equal(carRoot.get("number"), car.getNumber());
        } else {
            predicates[0] = cb.like(carRoot.get("number"), "%");
        }
        if(car.getPrice() > 0) {
            predicates[1] = cb.lessThanOrEqualTo(carRoot.get("price"), car.getPrice());
        } else {
            predicates[1] = cb.between(carRoot.get("price"), 2, 50);
        }
        if(car.getBrand() != null && !car.getBrand().isEmpty()) {
            predicates[2] = cb.equal(carRoot.get("brand"), car.getBrand());
        } else {
            predicates[2] = cb.like(carRoot.get("brand"), "%");
        }
        carCriteria.select(carRoot).where(predicates);
        return em.createQuery(carCriteria)
                .getResultList();
    }

}
