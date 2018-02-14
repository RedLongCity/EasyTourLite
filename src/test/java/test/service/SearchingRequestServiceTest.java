package test.service;

import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.service.SearchingRequestService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
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
 * @author redlongcity 14/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SearchingRequestServiceTest extends TestJPAConfig {

    @Autowired
    private SearchingRequestService service;

    @Autowired
    private CountryService countryService;

    @Autowired
    private From_CitiesService cityService;

    @Autowired
    private Meal_TypeService mealService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private Hotel_RatingService ratingService;

    private SearchingRequest request;

    private Country country;

    private From_Cities city;

    private Meal_Type type;

    private Hotel_Rating rating_1;

    private Hotel_Rating rating_2;

    private Currency currency;

    @Before
    public void populate() {
        country = new Country();
        country.setId("-1");
        country.setName("CountryName");
        city = new From_Cities();
        city.setId("-1");
        city.setName("CityName");
        type = new Meal_Type();
        type.setId("-1");
        type.setName("MealType");
        type.setName_Full("Meal_TypeNameFull");
        currency = new Currency();
        currency.setId("-1");
        currency.setName("CurrencyName");
        rating_1 = new Hotel_Rating();
        rating_2 = new Hotel_Rating();
        rating_1.setId("-1");
        rating_1.setName("RatingOne");
        rating_2.setId("-2");
        rating_2.setName("RatingTwo");

        countryService.saveCountry(country);
        cityService.saveFrom_Cities(city);
        mealService.saveMeal_Type(type);
        currencyService.saveCurrency(currency);
        ratingService.saveHotel_Rating(rating_1);
        ratingService.saveHotel_Rating(rating_2);

        request = new SearchingRequest();
        request.setType(1);
        request.setKind(1);
        request.setCountry(country);
        request.setCity(city);
        request.setHotel("HotelName");
        request.getRatingSet().add(rating_1);
        request.getRatingSet().add(rating_2);
        request.setAdultAmount(1);
        request.setChildAmount(1);
        request.setChildAge("1");
        request.setNightFrom(1);
        request.setNightTill(2);
        request.setDateFrom(new Date(System.currentTimeMillis()));
        request.setDateTill(new Date(System.currentTimeMillis()));
        request.setMealType(type);
        request.setPriceFrom(1);
        request.setPriceTill(2);
        request.setCurrency(currency);
        request.setOnlyStandart(1);
    }

    @Test
    public void crudTest() {
        service.saveSearchingRequest(request);
        assertTrue(service.findAll().contains(request));
        request.setAdultAmount(3);
        service.updateSearchingRequest(request);
        assertTrue(service.findAll().contains(request));
        service.deleteSearchingRequest(request);
        assertFalse(service.findAll().contains(request));
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_1() {
        request.setCountry(null);
        service.saveSearchingRequest(request);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        request.setCountry(country);
        request.setCity(null);
        service.saveSearchingRequest(request);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_3() {
        request.setCity(city);
        request.setRatingSet(null);
        service.saveSearchingRequest(request);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_4() {
        Set<Hotel_Rating> set = new HashSet<>();
        set.add(rating_1);
        set.add(rating_2);
        request.setRatingSet(set);
        request.setAdultAmount(null);
        service.saveSearchingRequest(request);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_5() {
        request.setAdultAmount(1);
        request.setNightFrom(null);
        service.saveSearchingRequest(request);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_6() {
        request.setNightFrom(1);
        request.setNightTill(null);
        service.saveSearchingRequest(request);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_7() {
        request.setNightTill(2);
        request.setDateFrom(null);
        service.saveSearchingRequest(request);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_8() {
        request.setDateFrom(new Date(System.currentTimeMillis()));
        request.setDateTill(null);
        service.saveSearchingRequest(request);
        service.findAll();
    }

}
