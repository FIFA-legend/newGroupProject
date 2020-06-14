package by.clowns;

import by.clowns.configuration.DaoConfiguration;
import by.clowns.entity.*;
import by.clowns.repository.CarRepository;
import by.clowns.repository.TaxiRepository;
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
public class TaxiRepositoryTest {
    @Autowired
    TaxiRepository taxiRepository;

    @Before
    public void init() {
        Taxi car1 = new Taxi(13.5, "8888", Comfort.BUSINESS);
        Taxi car2 = new Taxi(15.5, "7777", Comfort.ECONOMY);
        Taxi car3 = new Taxi(1.5, "6666", Comfort.UBER_BLACK);
        taxiRepository.save(car1);
        taxiRepository.save(car2);
        taxiRepository.save(car3);
    }

    @Test
    public void findByIdTest() {
        Taxi car = taxiRepository.findById(2L);
        Assert.assertEquals(Comfort.ECONOMY, car.getComfort());
    }


    @Test
    public void findAllTest() {
        List<Taxi> cars = taxiRepository.findAll();
        Assert.assertEquals(Comfort.BUSINESS, cars.get(0).getComfort());
    }

    @Test
    public void deleteByIdTest() {
        taxiRepository.deleteById(3L);
        List<Taxi> cars = taxiRepository.findAll();
        Assert.assertEquals(2, cars.size());
    }
}
