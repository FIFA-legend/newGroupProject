package by.clowns.repository;

import by.clowns.dto.CarDTO;
import by.clowns.entity.Car;
import by.clowns.entity.Region;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
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
        if(example.getNumber() != null && !example.getNumber().isEmpty()) {
            predicates[0] = cb.equal(carRoot.get("number"), example.getNumber());
        } else {
            predicates[0] = cb.like(carRoot.get("number"), "%");
        }
        if(example.getMaxPrice() > 0 && example.getMinPrice() > 0) {
            predicates[1] = cb.between(carRoot.get("price"), example.getMinPrice(), example.getMaxPrice());
        } else {
            predicates[1] = cb.between(carRoot.get("price"), 2, 50);
        }
        if(example.getBrand() != null && !example.getBrand().isEmpty()) {
            predicates[2] = cb.equal(carRoot.get("brand"), example.getBrand());
        } else {
            predicates[2] = cb.like(carRoot.get("brand"), "%");
        }
        if (example.getRegions() != null && !example.getRegions().isEmpty()) {
            Join<Region, Car> region = carRoot.join("regions");
            predicates[3] = cb.equal(region.get("name"), example.getRegions().get(0).getName());
        }
        if (predicates[3] != null) {
            carCriteria.select(carRoot).where(predicates);
        } else {
            carCriteria.select(carRoot).where(predicates[0], predicates[1], predicates[2]);
        }
        return em.createQuery(carCriteria)
                .getResultList();
    }

}
