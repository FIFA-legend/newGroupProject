package by.clowns.repository;

import by.clowns.entity.Car;
import org.springframework.data.repository.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
public interface CarRepository extends Repository<Car, Long> {

    Car save(Car car);

    Car findById(Long id);

    List<Car> findAll();

    void deleteById(Long id);
}
