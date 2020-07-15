package by.clowns.repository;

import by.clowns.entity.RentRequest;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface RequestRepository extends Repository<RentRequest, Long> {

    RentRequest save(RentRequest request);

    RentRequest findById(Long id);

    List<RentRequest> findAll();

    void deleteById(Long id);

}