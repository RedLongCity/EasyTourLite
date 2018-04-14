package test.dao;

import com.redlongcitywork.easytourlite.dao.CurrencyDao;
import com.redlongcitywork.easytourlite.model.Currency;
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
 * @author redlongcity 13/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class CurencyDaoTest extends TestJPAConfig {

    @Autowired
    private CurrencyDao dao;

    private Currency currency;

    @Before
    public void populate() {
        currency = new Currency();
        currency.setId("-1");
        currency.setName("CurrencyName");
    }

    @Test
    public void crudTest() {
        dao.save(currency);
        assertTrue(dao.findAll().contains(currency));
        currency.setName("AnotherCurrencyName");
        dao.mergeCurrency(currency);
        assertTrue(dao.findAll().contains(currency));
        dao.deleteCurrency(currency);
        assertFalse(dao.findAll().contains(currency));
    }

    @Test(expected = PersistenceException.class)
    public void exceptionTest() {
        currency.setId(null);
        dao.save(currency);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        currency.setId("-1");
        currency.setName(null);
        dao.save(currency);
        dao.findAll();
    }

}
