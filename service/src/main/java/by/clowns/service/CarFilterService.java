package by.clowns.service;

import by.clowns.dao.CarFilterDao;
import by.clowns.entity.Car;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class CarFilterService {

    private CarFilterDao carFilterDao;

    @Autowired
    public CarFilterService(CarFilterDao carFilterDao) {
        this.carFilterDao = carFilterDao;
    }

    public Set<Car> carFilterQuery(Car car) {
        return new HashSet<>(carFilterDao.carFilterQuery(car));
    }
}
