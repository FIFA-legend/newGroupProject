package by.clowns.repository;

import by.clowns.entity.User;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface UserRepository extends Repository<User, Long> {

    User save(User user);

    User findById(Long id);

    List<User> findAll();

    void deleteById(Long id);
}

