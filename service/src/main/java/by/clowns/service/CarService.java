package by.clowns.service;

import by.clowns.entity.Car;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CarService {

    void create(Car entity);

    Set<Car> read();

    void update(Car entity);

    void delete(long id);

    Car get(long id);

    void updateDate(Car entity, long id);
}
