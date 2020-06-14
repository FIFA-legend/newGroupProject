package by.clowns.repository;

import by.clowns.entity.Request;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RequestRepository extends Repository<Request, Long> {

    Request save(Request request);

    Request findById(Long id);

    List<Request> findAll();

    void deleteById(Long id);

}