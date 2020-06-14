package by.clowns.repository;

import by.clowns.entity.Car;
import by.clowns.entity.Truck;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface TruckRepository extends Repository<Truck, Long> {

    Truck save(Truck truck);

    Truck findById(Long id);

    List<Truck> findAll();

    void deleteById(Long id);
}
