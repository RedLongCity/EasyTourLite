package test.dao;

import com.redlongcitywork.easytourlite.dao.HotelDao;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.TourCasual;
import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.service.RegionService;
import com.redlongcitywork.easytourlite.service.TourCasualService;
import com.redlongcitywork.easytourlite.service.TypeService;
import java.sql.Date;
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
 * @author redlongcity 19/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class HotelDaoTest extends TestJPAConfig {

    @Autowired
    private HotelDao dao;

    @Autowired
    private CountryService countryService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private Hotel_RatingService ratingService;

    @Autowired
    private TourCasualService tourService;

    @Autowired
    private From_CitiesService cityService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private Meal_TypeService mealTypeService;

    @Autowired
    private TypeService typeService;

    private Hotel hotel;

    private Country country;

    private Region region;

    private Hotel_Rating rating;

    private TourCasual tour;

    private From_Cities city;

    private Currency currency;

    private Meal_Type mealType;

    private Price price;

    private Type type;

    @Before
    public void populate() {
        hotel = new Hotel();
        country = new Country();
        country.setId("id");
        country.setName("name");
        region = new Region();
        region.setId(1);
        region.setName("name");
        rating = new Hotel_Rating();
        rating.setId("id");
        rating.setName("name");
        city = new From_Cities();
        city.setId("id");
        city.setName("name");
        currency = new Currency();
        currency.setId("id");
        currency.setName("name");
        mealType = new Meal_Type();
        mealType.setId("id");
        mealType.setName("name");
        mealType.setName_Full("nameFull");
        price = new Price();
        price.setCost(1);
        price.setCurrency(currency);
        type = new Type();
        type.setId("id");
        type.setName("name");
        tour = new TourCasual();
        tour.setCity(city);
        tour.setCombined(true);
        tour.setCurrency(currency);
        tour.setDateFrom(new Date(System.currentTimeMillis()));
        tour.setDuration(1);
        tour.setKey("key");
        tour.setMealType(mealType);
        tour.getPrices().add(price);
        tour.setRoomType("roomType");
        tour.setTransportType("transportType");
        tour.setType(type);

        countryService.saveCountry(country);
        cityService.saveFrom_Cities(city);
        currencyService.saveCurrency(currency);
        mealTypeService.saveMeal_Type(mealType);
        typeService.saveType(type);
        regionService.saveRegion(region);
        ratingService.saveHotel_Rating(rating);

        hotel.setCountry(country);
        hotel.setRegion(region);
        hotel.setHotelName("hotelName");
        hotel.setHotelReviewRate("1");
        hotel.setHotelReviewCount("1");
        hotel.setRating(rating);
        hotel.setAdultAmount(1);
        hotel.setChildAmount(1);
        hotel.setAccomodation("acomodation");
        hotel.getTours().add(tour);
    }

    @Test
    public void crudTest() {
        dao.save(hotel);
        assertTrue(dao.findAll().contains(hotel));
        hotel.setAdultAmount(2);
        dao.mergeHotel(hotel);
        assertTrue(dao.findAll().contains(hotel));
        dao.deleteHotel(hotel);
        assertFalse(dao.findAll().contains(hotel));
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest() {
        hotel.setCountry(null);
        dao.save(hotel);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        hotel.setCountry(country);
        hotel.setRegion(null);
        dao.save(hotel);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_3() {
        hotel.setRegion(region);
        hotel.setHotelName(null);
        dao.save(hotel);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_4() {
        hotel.setHotelName("name");
        hotel.setRating(null);
        dao.save(hotel);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_5() {
        hotel.setRating(rating);
        hotel.setAdultAmount(null);
        dao.save(hotel);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_6() {
        hotel.setAdultAmount(1);
        hotel.setChildAmount(null);
        dao.save(hotel);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_7() {
        hotel.setChildAmount(1);
        hotel.setAccomodation(null);
        dao.save(hotel);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_8() {
        hotel.setAccomodation("acomodation");
        hotel.setTours(null);
        dao.save(hotel);
        dao.findAll();
    }

}
