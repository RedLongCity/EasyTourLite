package test.dao;

import com.redlongcitywork.easytourlite.dao.CountryDao;
import com.redlongcitywork.easytourlite.model.Country;
import org.hibernate.HibernateException;
import javax.validation.ConstraintViolationException;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertFalse;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import test.config.TestJPAConfig;

/**
 *
 * @author redlongcity 11/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CountryDaoTest extends TestJPAConfig {

    @Autowired
    private CountryDao dao;

    private Country country;

    @Before
    public void populate() {
        country = new Country();
        country.setId("-1");
        country.setName("CountryName");
    }

    @Test
    public void crudTest() {
        dao.save(country);
        assertTrue(dao.findAll().contains(country));
        country.setName("AnotherCountryName");
        dao.mergeCountry(country);
        assertTrue(dao.findAll().contains(country));
        dao.deleteCountry(country);
        assertFalse(dao.findAll().contains(country));
    }

    @Test(expected = HibernateException.class)
    public void exceptionTest() {
        country.setId(null);
        dao.save(country);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        country.setId("-1");
        country.setName(null);
        dao.save(country);
        dao.findAll();
    }

}
