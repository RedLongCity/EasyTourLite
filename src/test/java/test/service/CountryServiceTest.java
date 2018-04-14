package test.service;

import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.service.CountryService;
import javax.persistence.PersistenceException;
import javax.validation.ConstraintViolationException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import test.config.TestJPAConfig;

/**
 *
 * @author redlongcity 12/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CountryServiceTest extends TestJPAConfig {

    @Autowired
    private CountryService service;

    private Country country;

    @Before
    public void populate() {
        country = new Country();
        country.setId("-1");
        country.setName("CountryName");
    }

    @Test
    public void crudTest() {
        service.saveCountry(country);
        assertTrue(service.findAll().contains(country));
        country.setName("AnotherCountryName");
        service.updateCountry(country);
        assertTrue(service.findAll().contains(country));
        service.deleteCountry(country);
        assertFalse(service.findAll().contains(country));
    }

    @Test(expected = PersistenceException.class)
    public void exceptionTest() {
        country.setId(null);
        service.saveCountry(country);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        country.setId("-1");
        country.setName(null);
        service.saveCountry(country);
        service.findAll();
    }

}
