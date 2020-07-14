package by.clowns.dao;

import by.clowns.dto.CarDTO;
import by.clowns.entity.Car;
import by.clowns.entity.Region;
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

    public List<Car> carFilterQuery(CarDTO carDTO){
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<Car> carCriteria = cb.createQuery(Car.class);
        Root<Car> carRoot = carCriteria.from(Car.class);
        Predicate[] predicates = new Predicate[4];
        if(carDTO.getNumber() != null && !carDTO.getNumber().isEmpty()) {
            predicates[0] = cb.equal(carRoot.get("number"), carDTO.getNumber());
        } else {
            predicates[0] = cb.like(carRoot.get("number"), "%");
        }
        if(carDTO.getMaxPrice() > 0 && carDTO.getMinPrice() > 0) {
            predicates[1] = cb.between(carRoot.get("price"), carDTO.getMinPrice(), carDTO.getMaxPrice());
        } else {
            predicates[1] = cb.between(carRoot.get("price"), 2, 50);
        }
        if(carDTO.getBrand() != null && !carDTO.getBrand().isEmpty()) {
            predicates[2] = cb.equal(carRoot.get("brand"), carDTO.getBrand());
        } else {
            predicates[2] = cb.like(carRoot.get("brand"), "%");
        }
        if (carDTO.getRegions() != null && !carDTO.getRegions().isEmpty()) {
            Join<Region, Car> region = carRoot.join("regions");
            predicates[3] = cb.equal(region.get("name"), carDTO.getRegions().get(0).getName());
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
