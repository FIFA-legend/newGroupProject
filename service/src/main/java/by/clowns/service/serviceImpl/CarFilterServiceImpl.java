package by.clowns.service.serviceImpl;

import by.clowns.dto.CarDTO;
import by.clowns.entity.Car;
import by.clowns.repository.CarFilter;
import by.clowns.service.CarFilterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Component
@ComponentScan("by.clowns.repository")
public class CarFilterServiceImpl implements CarFilterService {

    private final CarFilter carFilter;

    @Autowired
    public CarFilterServiceImpl(CarFilter carFilter) {
        this.carFilter = carFilter;
    }

    @Override
    public Set<Car> filter(CarDTO car) {
        return new HashSet<>(carFilter.filter(car));
    }

    @Override
    public Set<Car> getVacantCars(CarDTO car) {
        Set<Car> cars = filter(car);
        Date date = new Date(System.currentTimeMillis());
        Set<Car> carsToReturn = new HashSet<>();
        for (Car c : cars) {
            if (c.getRentTime() != null && c.getRentTime().before(date)) carsToReturn.add(c);
        }
        return carsToReturn;
    }
}
