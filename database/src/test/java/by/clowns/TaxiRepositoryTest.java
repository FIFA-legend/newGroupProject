package by.clowns;

import by.clowns.configuration.DaoConfiguration;
import by.clowns.entity.*;
import by.clowns.repository.TaxiRepository;
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
public class TaxiRepositoryTest {

    @Autowired
    private TaxiRepository taxiRepository;

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
        Taxi car = taxiRepository.findById(8L);
        Assert.assertEquals(Comfort.ECONOMY, car.getComfort());
    }


    @Test
    public void findAllTest() {
        List<Taxi> cars = taxiRepository.findAll();
        Assert.assertEquals(Comfort.BUSINESS, cars.get(0).getComfort());
    }

    @Test
    public void deleteByIdTest() {
        taxiRepository.deleteById(9L);
        List<Taxi> cars = taxiRepository.findAll();
        Assert.assertEquals(2, cars.size());
    }
}
