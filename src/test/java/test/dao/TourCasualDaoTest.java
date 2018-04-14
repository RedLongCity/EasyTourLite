package test.dao;

import com.redlongcitywork.easytourlite.dao.TourCasualDao;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.TourCasual;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import java.sql.Date;
import javax.persistence.PersistenceException;
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
import test.config.TestJPAConfig;

/**
 *
 * @author redlongcity 16/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TourCasualDaoTest extends TestJPAConfig {

    @Autowired
    private TourCasualDao dao;

    @Autowired
    private Meal_TypeService mealTypeService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private From_CitiesService cityService;

    private TourCasual tour;

    private Meal_Type mealType;

    private Currency currency;

    private From_Cities city;

    private Price price;

    @Before
    public void populate() {
        tour = new TourCasual();
        city = new From_Cities();
        city.setId("id");
        city.setName("Name");
        mealType = new Meal_Type();
        mealType.setId("id");
        mealType.setName("Name");
        mealType.setName_Full("NameFull");
        currency = new Currency();
        currency.setId("id");
        currency.setName("name");
        price = new Price();
        price.setCost(1);
        price.setCurrency(currency);

        cityService.saveFrom_Cities(city);
        mealTypeService.saveMeal_Type(mealType);
        currencyService.saveCurrency(currency);

        tour.setKey("tourKey");
        tour.setMealType(mealType);
        tour.setRoomType("roomType");
        tour.setDuration(1);
        tour.setDateFrom(new Date(System.currentTimeMillis()));
        tour.setCombined(true);
        tour.setCurrency(currency);
        tour.getPrices().add(price);
        tour.setCity(city);
        tour.setTransportType("transportType");
    }

    @Test
    public void crudTest() {
        dao.save(tour);
        assertTrue(dao.findAll().contains(tour));
        tour.setRoomType("newRoomType");
        dao.mergeTourCasual(tour);
        assertTrue(dao.findAll().contains(tour));
        dao.deleteTourCasual(tour);
        assertFalse(dao.findAll().contains(tour));
    }

    @Test(expected = PersistenceException.class)
    public void exceptionTest() {
        tour.setKey(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_3() {
        tour.setKey("key");
        tour.setMealType(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_4() {
        tour.setMealType(mealType);
        tour.setRoomType(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_5() {
        tour.setRoomType("roomType");
        tour.setDuration(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_6() {
        tour.setDuration(1);
        tour.setDateFrom(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_7() {
        tour.setDateFrom(new Date(System.currentTimeMillis()));
        tour.setCombined(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_8() {
        tour.setCombined(true);
        tour.setCurrency(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_9() {
        tour.setCurrency(currency);
        tour.setPrices(null);
        dao.save(tour);
        dao.findAll();
    }
}
