package test.dao;

import com.redlongcitywork.easytourlite.dao.From_CitiesDao;
import com.redlongcitywork.easytourlite.model.From_Cities;
import org.hibernate.HibernateException;
import javax.validation.ConstraintViolationException;
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
 * @author redlongcity
 * 12/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class From_CitiesDaoTest extends TestJPAConfig {
    
    @Autowired
    private From_CitiesDao dao;
    
    private From_Cities city;
    
    @Before
    public void populate(){
        city = new From_Cities();
        city.setId("-1");
        city.setName("From_CitiesName");
    }
    
    @Test
    public void crudTest() {
        dao.save(city);
        assertTrue(dao.findAll().contains(city));
        city.setName("AnotherFrom_CitiesName");
        dao.mergeFrom_Cities(city);
        assertTrue(dao.findAll().contains(city));
        dao.deleteFrom_Cities(city);
        assertFalse(dao.findAll().contains(city));
    }

    @Test(expected = HibernateException.class)
    public void exceptionTest() {
        city.setId(null);
        dao.save(city);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        city.setId("-1");
        city.setName(null);
        dao.save(city);
        dao.findAll();
    }
    
}
