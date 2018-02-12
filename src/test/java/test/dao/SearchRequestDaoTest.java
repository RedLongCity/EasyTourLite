package test.dao;

import com.redlongcitywork.easytourlite.dao.CountryDao;
import com.redlongcitywork.easytourlite.dao.CountryDaoImpl;
import com.redlongcitywork.easytourlite.dao.CurrencyDao;
import com.redlongcitywork.easytourlite.dao.CurrencyDaoImpl;
import com.redlongcitywork.easytourlite.dao.From_CitiesDao;
import com.redlongcitywork.easytourlite.dao.From_CitiesDaoImpl;
import com.redlongcitywork.easytourlite.dao.Hotel_RatingDao;
import com.redlongcitywork.easytourlite.dao.Hotel_RatingDaoImpl;
import com.redlongcitywork.easytourlite.dao.Meal_TypeDao;
import com.redlongcitywork.easytourlite.dao.Meal_TypeDaoImpl;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.CountryServiceImpl;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.CurrencyServiceImpl;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.From_CitiesServiceImpl;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingServiceImpl;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.service.Meal_TypeServiceImpl;
import com.redlongcitywork.easytourlite.service.SearchingRequestService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;
import org.hibernate.HibernateException;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author redlongcity 09/02/2018
 */
//@RunWith(classes = {TestJPAConfig.class})
//@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration
//@Configuration
//@ContextConfiguration(classes = {TestJPAConfig.class})
//@ContextConfiguration(classes = {AppConfig.class}, loader = AnnotationConfigContextLoader.class)
public class SearchRequestDaoTest {

    @Configuration
    static class ContextConfiguration {
        
        @Bean
        CountryDao countryDao(){
            return new CountryDaoImpl();
        }
        
        @Bean
        From_CitiesDao cityDao(){
            return new From_CitiesDaoImpl();
        }
        
        @Bean
        Meal_TypeDao typeDao(){
            return new Meal_TypeDaoImpl();
        }
        
        @Bean
        CurrencyDao currencyDao(){
            return new CurrencyDaoImpl();
        }
        
        @Bean
        Hotel_RatingDao ratingDao(){
            return new Hotel_RatingDaoImpl();
        }

        @Bean
        CountryService countryService() {
            return new CountryServiceImpl();
        }

        @Bean
        From_CitiesService cityService() {
            return new From_CitiesServiceImpl();
        }

        @Bean
        Meal_TypeService typeService() {
            return new Meal_TypeServiceImpl();
        }

        @Bean
        CurrencyService currencyService() {
            return new CurrencyServiceImpl();
        }

        @Bean
        Hotel_RatingService ratingService() {
            return new Hotel_RatingServiceImpl();
        }

    }

    @Autowired
    private SearchingRequestService requestService;

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
        request.setRegion("RegionName");
        request.setHotel("HotelName");
        request.getRatingSet().add(rating_1);
        request.getRatingSet().add(rating_2);
        request.setAdultAmount(1);
        request.setChildAmount(1);
        request.setChildAge("1");
        request.setNightFrom(1);
        request.setNightTill(2);
        request.setDateFrom(Date.valueOf(
                LocalDate.parse("01.01.2018")));
        request.setDateTill(Date.valueOf(
                LocalDate.parse("02.01.2018")));
        request.setMealType(type);
        request.setPriceFrom(1);
        request.setPriceTill(2);
        request.setCurrency(currency);
        request.setOnlyStandart(1);
    }

    //@Test
    public void saveTest() {
        requestService.saveSearchingRequest(request);

        SearchingRequest entity = requestService.findById(request.getId());
        assertEquals(entity, request);
        assertTrue(entity.getType() == 1);
        assertTrue(entity.getKind() == 1);
        assertEquals(entity.getCountry(), country);
        assertEquals(entity.getCity(), city);
        assertEquals(entity.getRegion(), "RegionName");
        assertEquals(entity.getHotel(), "HotelName");

        Set<Hotel_Rating> set = new HashSet<>();
        set.add(rating_1);
        set.add(rating_2);

        assertEquals(entity.getRatingSet(), set);
        assertTrue(entity.getAdultAmount() == 1);
        assertTrue(entity.getChildAmount() == 1);
        assertEquals(entity.getChildAge(), "1");
        assertTrue(entity.getNightFrom() == 1);
        assertTrue(entity.getNightTill() == 2);
        assertEquals(entity.getDateFrom(), Date.valueOf(
                LocalDate.parse("01.01.2018")));
        assertEquals(entity.getDateTill(), Date.valueOf(
                LocalDate.parse("02.01.2018")));
        assertEquals(entity.getMealType(), type);
        assertTrue(entity.getPriceFrom() == 1);
        assertTrue(entity.getPriceTill() == 2);
        assertEquals(entity.getCurrency(), currency);
        assertTrue(entity.getOnlyStandart() == 1);
    }

    //@Test(expected = HibernateException.class)
    public void exceptionTest_1() {
        request.setId(0);
        requestService.saveSearchingRequest(request);
    }

    //@Test(expected = HibernateException.class)
    public void exceptionTest_2() {
        request.setId(null);
        request.setCountry(null);
        requestService.saveSearchingRequest(request);
    }

    //@Test(expected = HibernateException.class)
    public void exceptionTest_3() {
        request.setCountry(country);
        request.setCity(null);
        requestService.saveSearchingRequest(request);
    }

    //@Test(expected = HibernateException.class)
    public void exceptionTest_4() {
        request.setCity(city);
        request.setRatingSet(null);
        requestService.saveSearchingRequest(request);
    }

    //@Test(expected = HibernateException.class)
    public void exceptionTest_5() {
        Set<Hotel_Rating> set = new HashSet<>();
        set.add(rating_1);
        set.add(rating_2);
        request.setRatingSet(set);
        request.setAdultAmount(null);
        requestService.saveSearchingRequest(request);
    }

    //@Test(expected = HibernateException.class)
    public void exceptionTest_6() {
        request.setAdultAmount(1);
        request.setNightFrom(null);
        requestService.saveSearchingRequest(request);
    }

    //@Test(expected = HibernateException.class)
    public void exceptionTest_7() {
        request.setNightFrom(1);
        request.setNightTill(null);
        requestService.saveSearchingRequest(request);
    }

    //@Test(expected = HibernateException.class)
    public void exceptionTest_8() {
        request.setNightTill(2);
        request.setDateFrom(null);
        requestService.saveSearchingRequest(request);
    }

    //@Test(expected = HibernateException.class)
    public void exceptionTest_9() {
        request.setDateFrom(Date.valueOf(
                LocalDate.parse("01.01.2018")));
        request.setDateTill(null);
        requestService.saveSearchingRequest(request);
    }

    //@Test(expected = HibernateException.class)
    public void exceptionTest_10() {
        request.setDateTill(Date.valueOf(
                LocalDate.parse("02.01.2018")));
        request.setDateTill(null);
        requestService.saveSearchingRequest(request);
    }

    //@Test
    public void updateTest() {
        SearchingRequest entity = new SearchingRequest();
        entity.setId(request.getId());
        entity.setType(null);
        entity.setKind(null);
        country.setId("-2");
        country.setName("AnotherCountryName");
        countryService.saveCountry(country);
        entity.setCountry(country);
        city.setId("-2");
        city.setName("AnotherCityName");
        entity.setCity(city);
        entity.setRegion(null);
        entity.setHotel(null);
        rating_1.setId("-3");
        rating_1.setName("AnotherRatingName_1");
        rating_2.setId("-4");
        rating_2.setName("AnotherRatingName_2");
        ratingService.saveHotel_Rating(rating_1);
        ratingService.saveHotel_Rating(rating_2);
        Set<Hotel_Rating> set = new HashSet<>();
        set.add(rating_1);
        set.add(rating_2);
        entity.setRatingSet(set);
        entity.setAdultAmount(4);
        entity.setChildAmount(null);
        entity.setChildAge(null);
        entity.setDateFrom(Date.valueOf(
                LocalDate.parse("03.01.2018")));
        entity.setDateTill(Date.valueOf(
                LocalDate.parse("04.01.2018")));
        entity.setMealType(null);
        entity.setPriceFrom(null);
        entity.setPriceTill(null);
        entity.setCurrency(null);
        entity.setOnlyStandart(null);
        requestService.updateSearchingRequest(entity);

        SearchingRequest expected = requestService.findById(entity.getId());
        assertEquals(expected.getType(), entity.getType());
        assertEquals(expected.getKind(), entity.getKind());
        assertEquals(expected.getCountry(), entity.getCountry());
        assertEquals(expected.getCity(), entity.getCity());
        assertEquals(expected.getRegion(), entity.getRegion());
        assertEquals(expected.getHotel(), entity.getHotel());
        assertEquals(expected.getRatingSet(), entity.getRatingSet());
        assertEquals(expected.getAdultAmount(), entity.getAdultAmount());
        assertEquals(expected.getChildAmount(), entity.getChildAmount());
        assertEquals(expected.getChildAge(), entity.getChildAge());
        assertEquals(expected.getNightFrom(), entity.getNightFrom());
        assertEquals(expected.getNightTill(), entity.getNightTill());
        assertEquals(expected.getDateFrom(), entity.getDateFrom());
        assertEquals(expected.getDateTill(), entity.getDateTill());
        assertEquals(expected.getMealType(), entity.getMealType());
        assertEquals(expected.getPriceFrom(), entity.getPriceFrom());
        assertEquals(expected.getPriceTill(), entity.getPriceTill());
        assertEquals(expected.getCurrency(), entity.getCurrency());
        assertEquals(expected.getOnlyStandart(), entity.getOnlyStandart());
    }

    //@Test
    public void deleteTest() {
        Integer id = request.getId();
        requestService.deleteSearchingRequest(request);
        assertEquals(requestService.findById(id), null);
        countryService.deleteCountry(country);
        countryService.deleteCountry(countryService.findById("-1"));
        assertEquals(countryService.findById("-1"), null);
        assertEquals(countryService.findById("-2"), null);
        cityService.deleteFrom_Cities(city);
        cityService.deleteFrom_Cities(cityService.findById("-1"));
        assertEquals(cityService.findById("-1"), null);
        assertEquals(cityService.findById("-2"), null);
        mealService.deleteMeal_Type(type);
        assertEquals(mealService.findById("-1"), null);
        ratingService.deleteHotel_Rating(rating_1);
        ratingService.deleteHotel_Rating(rating_2);
        ratingService.deleteHotel_Rating(ratingService.findById("-1"));
        ratingService.deleteHotel_Rating(ratingService.findById("-2"));
        assertEquals(ratingService.findById("-1"), null);
        assertEquals(ratingService.findById("-2"), null);
        assertEquals(ratingService.findById("-3"), null);
        assertEquals(ratingService.findById("-4"), null);
        currencyService.deleteCurrency(currency);
        assertEquals(currencyService.findById("-1"), null);
    }
}
