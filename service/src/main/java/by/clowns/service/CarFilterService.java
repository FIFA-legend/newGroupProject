package by.clowns.service;

import by.clowns.dto.CarDTO;
import by.clowns.entity.Car;
import org.springframework.stereotype.Service;

import java.util.Set;

@Service
public interface CarFilterService {

    Set<Car> filter(CarDTO car);

    Set<Car> getVacantCars(CarDTO car);

}
