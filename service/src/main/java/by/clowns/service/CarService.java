package by.clowns.service;

import by.clowns.entity.Car;
import by.clowns.repository.CarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
public class CarService implements ServiceInterface<Car> {

    private final CarRepository carRepository;

    @Autowired
    public CarService(CarRepository carRepository) {
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
    public void update(Car entity, long id) {
        Car carToUpdate = carRepository.findById(id);
        carToUpdate.setNumber(entity.getNumber());
        carToUpdate.setPrice(entity.getPrice());
        carToUpdate.setRegions(entity.getRegions());
        carToUpdate.setRequest(entity.getRequest());
        carRepository.save(carToUpdate);
    }

    @Override
    public void delete(long id) {
        carRepository.deleteById(id);
    }

    @Override
    public Car get(long id) {
        return carRepository.findById(id);
    }

}
