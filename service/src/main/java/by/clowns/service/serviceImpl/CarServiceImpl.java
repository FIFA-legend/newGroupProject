package by.clowns.service.serviceImpl;

import by.clowns.entity.Car;
import by.clowns.repository.CarRepository;
import by.clowns.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Component
public class CarServiceImpl implements CarService {

    private final CarRepository carRepository;

    @Autowired
    public CarServiceImpl(CarRepository carRepository) {
        this.carRepository = carRepository;
    }

    @Override
    public void create(Car entity) {
        carRepository.save(entity);
    }

    @Override
    public Set<Car> read() {
        List<Car> carList = carRepository.findAll();
        return new HashSet<>(carList);
    }

    @Override
    public void update(Car entity) {
        carRepository.save(entity);
    }

    @Override
    public void delete(long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car get(long id) {
        return carRepository.findById(id);
    }

    @Override
    public void updateDate(Car entity, long id) {
        Car carToUpdate = carRepository.findById(id);
        carToUpdate.setRentTime(entity.getRentTime());
        carRepository.save(carToUpdate);
    }
}
