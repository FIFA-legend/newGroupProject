package by.clowns;

import by.clowns.configuration.DaoConfiguration;
import by.clowns.entity.Truck;
import by.clowns.repository.CarRepository;
import by.clowns.repository.TruckRepository;
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
public class TruckRepositoryTest {

    @Autowired
    TruckRepository truckRepository;

    @Before
    public void init() {
        Truck car1 = new Truck(13.5, "8888", 15);
        Truck car2 = new Truck(15.5, "7777", 11);
        Truck car3 = new Truck(1.5, "6666", 89);
        truckRepository.save(car1);
        truckRepository.save(car2);
        truckRepository.save(car3);
    }

    @Test
    public void findByIdTest() {
        Truck car = truckRepository.findById(2L);
        System.out.println(car.getCarrying());
        Assert.assertEquals(11, car.getCarrying());
    }


    @Test
    public void findAllTest() {
        List<Truck> cars = truckRepository.findAll();
        Assert.assertEquals(15, cars.get(0).getCarrying());
    }

    @Test
    public void deleteByIdTest() {
        truckRepository.deleteById(3L);
        List<Truck> cars = truckRepository.findAll();
        Assert.assertEquals(2, cars.size());
    }
}
