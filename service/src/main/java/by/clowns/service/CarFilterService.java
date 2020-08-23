package by.clowns.service;

import by.clowns.dto.CarDTO;
import by.clowns.entity.Car;
import by.clowns.repository.CarFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Service
@ComponentScan("by.clowns.repository")
public class CarFilterService {

    private final CarFilter carFilter;

    @Autowired
    public CarFilterService(CarFilter carFilter) {
        this.carFilter = carFilter;
    }

    public Set<Car> filter(CarDTO car) {
        return new HashSet<>(carFilter.filter(car));
    }

    public Set<Car> getFreeAndFilteredCars(CarDTO car) {
        Set<Car> cars = filter(car);
        Date date = new Date(System.currentTimeMillis());
        Set<Car> carsToReturn = new HashSet<>();
        for (Car c : cars) {
            if (c.getRentTime() != null && c.getRentTime().before(date)) carsToReturn.add(c);
        }
        return carsToReturn;
    }
}
