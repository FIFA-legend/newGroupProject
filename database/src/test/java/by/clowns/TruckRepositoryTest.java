package by.clowns;

import by.clowns.configuration.DaoConfiguration;
import by.clowns.entity.Truck;
import by.clowns.repository.TruckRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.io.Resource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.jdbc.JdbcTestUtils;

import javax.sql.DataSource;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {DaoConfiguration.class})
public class TruckRepositoryTest {

    @Autowired
    private TruckRepository truckRepository;

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
    public void findByIdTest() {
        Truck car = truckRepository.findById(11L);
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
        truckRepository.deleteById(12L);
        List<Truck> cars = truckRepository.findAll();
        Assert.assertEquals(2, cars.size());
    }
}
