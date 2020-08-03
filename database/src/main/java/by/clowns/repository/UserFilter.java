package by.clowns.repository;

import by.clowns.dto.UserDTO;
import by.clowns.entity.Passport;
import by.clowns.entity.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.List;

@Repository
public class UserFilter {

    private final EntityManagerFactory entityManagerFactory;

    @Autowired
    public UserFilter(EntityManagerFactory entityManagerFactory) {
        this.entityManagerFactory = entityManagerFactory;
    }

    @Transactional
    public List<User> filter(UserDTO example) {
        EntityManager em = entityManagerFactory.createEntityManager();
        em.getTransaction().begin();
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<User> userCriteria = cb.createQuery(User.class);
        Root<User> userRoot = userCriteria.from(User.class);
        Predicate[] predicates = new Predicate[4];

        predicates[0] = usernameFilter(cb, example, userRoot);
        predicates[1] = realNameFilter(cb, example, userRoot);
        predicates[2] = realSurnameFilter(cb, example, userRoot);
        predicates[3] = birthFilter(cb, example, userRoot);

        if (predicates[3] != null) {
            userCriteria.select(userRoot).where(predicates);
        } else {
            userCriteria.select(userRoot).where(predicates[0], predicates[1], predicates[2]);
        }
        return em.createQuery(userCriteria)
                .getResultList();
    }

    private Predicate usernameFilter(CriteriaBuilder cb, UserDTO user, Root<User> root) {
        if (user.getUsername() != null && !user.getUsername().isEmpty()) {
            return cb.like(root.get("username"), user.getUsername() + "%");
        } else {
            return cb.like(root.get("username"), "%");
        }
    }

    private Predicate realNameFilter(CriteriaBuilder cb, UserDTO userDTO, Root<User> root) {
        Join<User, Passport> passport = root.join("passport");
        if (userDTO.getRealName() != null && !userDTO.getRealName().isEmpty()) {
            return cb.like(passport.get("name"), userDTO.getRealName() + "%");
        } else {
            return cb.like(passport.get("name"), "%");
        }
    }

    private Predicate realSurnameFilter(CriteriaBuilder cb, UserDTO userDTO, Root<User> root) {
        Join<User, Passport> passport = root.join("passport");
        if (userDTO.getRealSurname() != null && !userDTO.getRealSurname().isEmpty()) {
            return cb.like(passport.get("surname"), userDTO.getRealSurname() + "%");
        } else {
            return cb.like(passport.get("surname"), "%");
        }
    }

    private Predicate birthFilter(CriteriaBuilder cb, UserDTO userDTO, Root<User> root) {
        Join<User, Passport> passport = root.join("passport");
        if (userDTO.getStart() != null && userDTO.getFinish() != null) {
            return cb.between(passport.get("birth"), userDTO.getStart(), userDTO.getFinish());
        } else if (userDTO.getStart() != null) {
            return cb.greaterThan(passport.get("birth"), userDTO.getStart());
        } else if (userDTO.getFinish() != null) {
            return cb.lessThan(passport.get("birth"), userDTO.getFinish());
        } else {
            return null;
        }
    }
}
