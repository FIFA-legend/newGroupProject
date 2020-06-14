package by.clowns;

import by.clowns.configuration.DaoConfiguration;
import by.clowns.entity.Car;
import by.clowns.repository.CarRepository;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Before
    public void init() {
        Car car1 = new Car(13.5, "8888");
        Car car2 = new Car(15.5, "7777");
        Car car3 = new Car(1.5, "6666");
        carRepository.save(car1);
        carRepository.save(car2);
        carRepository.save(car3);
    }

    @Test
    public void findByIdTest() {
        Car car = carRepository.findById(2L);
        Assert.assertEquals("7777", car.getNumber());
    }


    @Test
    public void findAllTest() {
        List<Car> cars = carRepository.findAll();
        Assert.assertEquals("8888", cars.get(0).getNumber());
    }

    @Test
    public void deleteByIdTest() {
        carRepository.deleteById(3L);
        List<Car> cars = carRepository.findAll();
        Assert.assertEquals(2, cars.size());
    }
    
}
