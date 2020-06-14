package by.clowns.repository;

import by.clowns.entity.Bus;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface BusRepository extends Repository<Bus, Long> {

    Bus save(Bus bus);

    Bus findById(Long id);

    List<Bus> findAll();

    void deleteById(Long id);
}
