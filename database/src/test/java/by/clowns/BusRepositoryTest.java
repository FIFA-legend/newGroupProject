package by.clowns;

import by.clowns.configuration.DaoConfiguration;

import by.clowns.entity.Bus;
import by.clowns.repository.BusRepository;
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
public class BusRepositoryTest {

    @Autowired
    private BusRepository busRepository;

    @Before
    public void init() {
        Bus car1 = new Bus("Mercedes", 13.5, "8888", 85);
        Bus car2 = new Bus("Mercedes", 15.5, "7777", 20);
        Bus car3 = new Bus("Mercedes", 1.5, "6666",1);
        busRepository.save(car1);
        busRepository.save(car2);
        busRepository.save(car3);
    }

    @Test
    public void findByIdTest() {
        Bus car = busRepository.findById(2L);
        Assert.assertEquals(20, car.getCapacity());
    }


    @Test
    public void findAllTest() {
        List<Bus> cars = busRepository.findAll();
        Assert.assertEquals(85, cars.get(0).getCapacity());
    }

    @Test
    public void deleteByIdTest() {
        busRepository.deleteById(3L);
        List<Bus> cars = busRepository.findAll();
        Assert.assertEquals(2, cars.size());
    }
}
