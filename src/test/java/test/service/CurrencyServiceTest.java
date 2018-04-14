package test.service;

import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.service.CurrencyService;
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
public class CurrencyServiceTest extends TestJPAConfig {

    @Autowired
    private CurrencyService service;

    private Currency currency;

    @Before
    public void populate() {
        currency = new Currency();
        currency.setId("-1");
        currency.setName("CurrencyName");
    }

    @Test
    public void crudTest() {
        service.saveCurrency(currency);
        assertTrue(service.findAll().contains(currency));
        currency.setName("AnotherCurrencyName");
        service.updateCurrency(currency);
        assertTrue(service.findAll().contains(currency));
        service.deleteCurrency(currency);
        assertFalse(service.findAll().contains(currency));
    }

    @Test(expected = PersistenceException.class)
    public void exceptionTest() {
        currency.setId(null);
        service.saveCurrency(currency);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        currency.setId("-1");
        currency.setName(null);
        service.saveCurrency(currency);
        service.findAll();
    }

}
