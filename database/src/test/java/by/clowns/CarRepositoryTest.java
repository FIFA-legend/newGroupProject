package by.clowns;

import by.clowns.configuration.DaoConfiguration;
import by.clowns.entity.Car;
import by.clowns.repository.CarRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
public class CarRepositoryTest {

    @Autowired
    private CarRepository carRepository;

    @Autowired
    private ApplicationContext context;

    private JdbcTemplate template;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        template = new JdbcTemplate(dataSource);
    }

    @Before
    public void beforeTests() {
        if (!DataBaseInput.flag) {
            String classpath = "classpath:testBase.sql";
            Resource resource = context.getResource(classpath);
            JdbcTestUtils.executeSqlScript(template, resource, true);
            DataBaseInput.flag = true;
        }
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void findByIdTest() {
        Car car = carRepository.findById(2L);
        Assert.assertEquals("7777", car.getNumber());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void findAllTest() {
        List<Car> cars = carRepository.findAll();
        Assert.assertEquals("8888", cars.get(0).getNumber());
    }

    @Test
    @DirtiesContext(methodMode = DirtiesContext.MethodMode.AFTER_METHOD)
    public void deleteByIdTest() {
        carRepository.deleteById(3L);
        List<Car> cars = carRepository.findAll();
        Assert.assertEquals(10, cars.size());
    }
    
}
