package by.clowns.repository;

import by.clowns.entity.Taxi;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TaxiRepository extends Repository<Taxi, Long> {

    Taxi save(Taxi taxi);

    Taxi findById(Long id);

    List<Taxi> findAll();

    void deleteById(Long id);
}
