package test.service;

import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import javax.validation.ConstraintViolationException;
import org.hibernate.HibernateException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import test.database.TestJPAConfig;

/**
 *
 * @author redlongcity 12/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class From_CitiesServiceTest extends TestJPAConfig {

    @Autowired
    private From_CitiesService service;

    private From_Cities city;

    @Before
    public void populate() {
        city = new From_Cities();
        city.setId("-1");
        city.setName("From_CitiesName");
    }

    @Test
    public void crudTest() {
        service.saveFrom_Cities(city);
        assertTrue(service.findAll().contains(city));
        city.setName("AnotherFrom_CitiesName");
        service.updateFrom_Cities(city);
        assertTrue(service.findAll().contains(city));
        service.deleteFrom_Cities(city);
        assertFalse(service.findAll().contains(city));
    }

    @Test(expected = HibernateException.class)
    public void exceptionTest() {
        city.setId(null);
        service.saveFrom_Cities(city);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        city.setId("-1");
        city.setName(null);
        service.saveFrom_Cities(city);
        service.findAll();
    }

}
