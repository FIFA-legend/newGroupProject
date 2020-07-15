package by.clowns.repository;

import by.clowns.dto.CarDTO;
import by.clowns.entity.Car;
import by.clowns.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import java.util.List;

@Repository
public class CarFilter {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public CarFilter(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    public List<Car> filter(CarDTO example){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Car> carCriteria = cb.createQuery(Car.class);
        Root<Car> carRoot = carCriteria.from(Car.class);
        Predicate[] predicates = new Predicate[4];

        predicates[0] = numberFilter(cb, example, carRoot);
        predicates[1] = priceFilter(cb, example, carRoot);
        predicates[2] = brandFilter(cb, example, carRoot);
        predicates[3] = regionFilter(cb, example, carRoot);

        if (predicates[3] != null) {
            carCriteria.select(carRoot).where(predicates);
        } else {
            carCriteria.select(carRoot).where(predicates[0], predicates[1], predicates[2]);
        }
        return em.createQuery(carCriteria)
                .getResultList();
    }

    private Predicate numberFilter(CriteriaBuilder cb, CarDTO car, Root<Car> root) {
        if (car.getNumber() != null && !car.getNumber().isEmpty()) {
            return cb.equal(root.get("number"), car.getNumber());
        } else {
            return cb.like(root.get("number"), "%");
        }
    }

    private Predicate priceFilter(CriteriaBuilder cb, CarDTO car, Root<Car> root) {
        if (car.getMaxPrice() > 0 && car.getMinPrice() > 0) {
            return cb.between(root.get("price"), car.getMinPrice(), car.getMaxPrice());
        } else if (car.getMaxPrice() > 0 && car.getMinPrice() <= 0) {
            return cb.lessThanOrEqualTo(root.get("price"), car.getMaxPrice());
        } else if (car.getMaxPrice() <= 0 && car.getMinPrice() > 0) {
            return cb.greaterThanOrEqualTo(root.get("price"), car.getMinPrice());
        } else {
            return cb.between(root.get("price"), 2, 50);
        }
    }

    private Predicate brandFilter(CriteriaBuilder cb, CarDTO car, Root<Car> root) {
        if (car.getBrand() != null && !car.getBrand().isEmpty()) {
            return cb.equal(root.get("brand"), car.getBrand());
        } else {
            return cb.like(root.get("brand"), "%");
        }
    }

    private Predicate regionFilter(CriteriaBuilder cb, CarDTO car, Root<Car> root) {
        if (car.getRegions() != null && !car.getRegions().isEmpty()) {
            Join<Region, Car> region = root.join("regions");
            return cb.equal(region.get("name"), car.getRegions().get(0).getName());
        } else {
            return null;
        }
    }
}
