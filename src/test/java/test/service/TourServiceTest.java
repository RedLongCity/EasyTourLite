package test.service;

import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.service.TourService;
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
import test.database.TestJPAConfig;

/**
 *
 * @author redlongcity 14/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TourServiceTest extends TestJPAConfig {

    @Autowired
    private TourService service;

    @Autowired
    private CountryService countryService;

    @Autowired
    private Hotel_RatingService ratingService;

    @Autowired
    private Meal_TypeService mealService;

    @Autowired
    private From_CitiesService cityService;

    private Tour tour;

    private Country country;

    private From_Cities city;

    private Meal_Type type;

    private Hotel_Rating rating;

    @Before
    public void populate() {
        tour = new Tour();
        tour.setKey("-1");
        tour.setType(1);
        country = new Country();
        country.setId("-1");
        country.setName("CountryName");
        countryService.saveCountry(country);
        tour.setCountry(country);
        tour.setRegion("SomeRegionName");
        tour.setHotel_id(1);
        tour.setHotel("Hotel");
        rating = new Hotel_Rating();
        rating.setName("HotelName");
        rating.setId("-1");
        ratingService.saveHotel_Rating(rating);
        tour.setHotel_Rating(rating);
        type = new Meal_Type();
        type.setId("-1");
        type.setName("MealTypeName");
        type.setName_Full("mealTypeNameFull");
        mealService.saveMeal_Type(type);
        tour.setMeal_Type(type);
        tour.setRoom_Type("1");
        tour.setAdult_Amount(1);
        tour.setChild_Amount(0);
        tour.setAccomodation("1");
        tour.setDuration(1);
        tour.setDate_From(new Date(System.currentTimeMillis()));
        tour.setDate_From_Unix(1);
        tour.setCurrency_id(0);
        tour.setCurrency_Symbol("1");
        city = new From_Cities();
        city.setName("cityName");
        city.setId("cityId");
        cityService.saveFrom_Cities(city);
        tour.setFrom_Cities(city);
    }

    @Test
    public void crudTest() {
        service.saveTour(tour);
        assertTrue(service.findAll().contains(tour));
        tour.setType(2);
        service.updateTour(tour);
        assertTrue(service.findAll().contains(tour));
        service.deleteTour(tour);
        assertFalse(service.findAll().contains(tour));
    }

    @Test(expected = HibernateException.class)
    public void exceptionTest() {
        tour.setKey(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        tour.setKey("-1");
        tour.setType(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_3() {
        tour.setType(1);
        tour.setCountry(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_4() {
        tour.setCountry(country);
        tour.setRegion(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_5() {
        tour.setRegion("region");
        tour.setHotel_id(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_6() {
        tour.setHotel_id(1);
        tour.setHotel(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_7() {
        tour.setHotel("Hotel");
        tour.setHotel_Rating(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_8() {
        tour.setHotel_Rating(rating);
        tour.setMeal_Type(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_9() {
        tour.setMeal_Type(type);
        tour.setRoom_Type(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_10() {
        tour.setRoom_Type("room type");
        tour.setAdult_Amount(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_11() {
        tour.setAdult_Amount(1);
        tour.setChild_Amount(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_12() {
        tour.setChild_Amount(1);
        tour.setAccomodation(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_13() {
        tour.setAccomodation("1");
        tour.setDuration(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_14() {
        tour.setDuration(1);
        tour.setDate_From(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_15() {
        tour.setDate_From(new Date(System.currentTimeMillis()));
        tour.setDate_From_Unix(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_16() {
        tour.setDate_From_Unix(1);
        tour.setCurrency_id(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_17() {
        tour.setCurrency_id(0);
        tour.setCurrency_Symbol(null);
        service.saveTour(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_18() {
        tour.setCurrency_Symbol("$");
        tour.setFrom_Cities(null);
        service.saveTour(tour);
        service.findAll();
    }

}
