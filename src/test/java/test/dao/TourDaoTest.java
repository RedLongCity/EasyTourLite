package test.dao;

import com.redlongcitywork.easytourlite.dao.TourDao;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import java.sql.Date;
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
 * @author redlongcity 13/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TourDaoTest extends TestJPAConfig {

    @Autowired
    private TourDao dao;

    @Autowired
    private Instances instances;

    @Autowired
    private CountryService countryService;

    @Autowired
    private From_CitiesService cityService;

    @Autowired
    private Meal_TypeService mealTypeService;

    @Autowired
    private Hotel_RatingService ratingService;

    private Tour tour;

    private Country country;

    private From_Cities city;

    private Meal_Type type;

    private Hotel_Rating rating;

    @Before
    public void populate() {
        country = instances.getCountry();
        countryService.saveCountry(country);
        city = instances.getCity();
        cityService.saveFrom_Cities(city);
        type = instances.getMealType();
        mealTypeService.saveMeal_Type(type);
        rating = instances.getRating();
        ratingService.saveHotel_Rating(rating);

        tour = new Tour();
        tour.setKey("-1");
        tour.setType(1);
        tour.setCountry(country);
        tour.setRegion("SomeRegionName");
        tour.setHotel_id(1);
        tour.setHotel("Hotel");
        tour.setHotel_Rating(rating);
        tour.setMeal_Type(type);
        tour.setRoom_Type("1");
        tour.setAdult_Amount(1);
        tour.setChild_Amount(0);
        tour.setAccomodation("1");
        tour.setDuration(1);
        tour.setDate_From(instances.getDate());
        tour.setDate_From_Unix(1);
        tour.setCurrency_id(0);
        tour.setCurrency_Symbol("1");
        tour.setFrom_Cities(city);
    }

    @Test
    public void crudTest() {
        dao.save(tour);
        assertTrue(dao.findAll().contains(tour));
        tour.setType(2);
        dao.mergeTour(tour);
        assertTrue(dao.findAll().contains(tour));
        dao.deleteTour(tour);
        assertFalse(dao.findAll().contains(tour));
    }

    @Test(expected = HibernateException.class)
    public void exceptionTest() {
        tour.setKey(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        tour.setKey("-1");
        tour.setType(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_3() {
        tour.setType(1);
        tour.setCountry(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_4() {
        tour.setCountry(country);
        tour.setRegion(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_5() {
        tour.setRegion("region");
        tour.setHotel_id(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_6() {
        tour.setHotel_id(1);
        tour.setHotel(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_7() {
        tour.setHotel("Hotel");
        tour.setHotel_Rating(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_8() {
        tour.setHotel_Rating(rating);
        tour.setMeal_Type(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_9() {
        tour.setMeal_Type(type);
        tour.setRoom_Type(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_10() {
        tour.setRoom_Type("room type");
        tour.setAdult_Amount(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_11() {
        tour.setAdult_Amount(1);
        tour.setChild_Amount(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_12() {
        tour.setChild_Amount(1);
        tour.setAccomodation(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_13() {
        tour.setAccomodation("1");
        tour.setDuration(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_14() {
        tour.setDuration(1);
        tour.setDate_From(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_15() {
        tour.setDate_From(new Date(System.currentTimeMillis()));
        tour.setDate_From_Unix(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_16() {
        tour.setDate_From_Unix(1);
        tour.setCurrency_id(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_17() {
        tour.setCurrency_id(0);
        tour.setCurrency_Symbol(null);
        dao.save(tour);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_18() {
        tour.setCurrency_Symbol("$");
        tour.setFrom_Cities(null);
        dao.save(tour);
        dao.findAll();
    }

}
