package test.service;

import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.Facility;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Image;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.service.RegionService;
import com.redlongcitywork.easytourlite.service.TourAdvancedService;
import com.redlongcitywork.easytourlite.service.TypeService;
import java.sql.Date;
import java.util.HashSet;
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
 * @author redlongcity 04/03/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TourAdvancedServiceTest extends TestJPAConfig {

    @Autowired
    private TourAdvancedService service;

    @Autowired
    private CountryService countryService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private Meal_TypeService mealTypeService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private From_CitiesService cityService;

    private TourAdvanced tour;

    private Country country;

    private Type type;

    private Region region;

    private Meal_Type mealType;

    private Currency currency;

    private Price price;

    private Hotel_Image image;

    private Facility facility;

    private From_Cities city;

    @Before
    public void populate() {
        tour = new TourAdvanced();
        country = new Country();
        country.setId("id");
        country.setName("name");
        type = new Type();
        type.setId("id");
        type.setName("name");
        region = new Region();
        region.setId("id");
        region.setName("name");
        mealType = new Meal_Type();
        mealType.setId("id");
        mealType.setName("name");
        mealType.setName_Full("name");
        currency = new Currency();
        currency.setId("id");
        currency.setName("name");
        price = new Price();
        price.setCost(1);
        price.setCurrency(currency);
        image = new Hotel_Image();
        image.setThumb("thumb");
        image.setFull("full");
        facility = new Facility();
        facility.setId("id");
        facility.setCategory("category");
        facility.setCategoryId("categoryId");
        facility.setName("name");
        facility.setMain(Boolean.TRUE);
        facility.setPaid(Boolean.TRUE);
        city = new From_Cities();
        city.setId("id");
        city.setName("name");

        countryService.saveCountry(country);
        typeService.saveType(type);
        regionService.saveRegion(region);
        mealTypeService.saveMeal_Type(mealType);
        currencyService.saveCurrency(currency);
        cityService.saveFrom_Cities(city);

        tour.setKey("key");
        tour.setCountry(country);
        tour.setType(type);
        tour.setRegion(region);
        tour.setHotelId(1);
        tour.setHotelName("name");
        tour.setMealType(mealType);
        tour.setAdultAmount(1);
        tour.setChildAmount(2);
        tour.setAccomodation("accomodation");
        tour.setRoomType("type");
        tour.setDuration(1);
        tour.setDateFrom(new Date(System.currentTimeMillis()));
        tour.setCombined(true);
        tour.setCurrency(currency);
        tour.getPrices().add(price);
        tour.setCity(city);
        tour.setTransportType("type");
        tour.getImages().add(image);
        tour.setRate("rate");
        tour.setReviewCount("count");
        tour.getFacilities().add(facility);
    }

    @Test
    public void crudTest() {
        service.saveTourAdvanced(tour);
        assertTrue(service.findAll().contains(tour));
        tour.setRate("anotherRate");
        service.updateTourAdvanced(tour);
        assertTrue(service.findAll().contains(tour));
        service.deleteTourAdvanced(tour);
        assertFalse(service.findAll().contains(tour));
    }

    @Test(expected = HibernateException.class)
    public void exceptionTest() {
        tour.setKey(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        tour.setKey("key");
        tour.setCountry(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_3() {
        tour.setCountry(country);
        tour.setType(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_4() {
        tour.setType(type);
        tour.setRegion(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_5() {
        tour.setRegion(region);
        tour.setHotelId(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_6() {
        tour.setHotelId(1);
        tour.setHotelName(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_7() {
        tour.setHotelName("name");
        tour.setMealType(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_8() {
        tour.setMealType(mealType);
        tour.setAdultAmount(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_9() {
        tour.setAdultAmount(1);
        tour.setChildAmount(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_10() {
        tour.setChildAmount(2);
        tour.setRoomType(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_11() {
        tour.setRoomType("type");
        tour.setDuration(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_12() {
        tour.setDuration(1);
        tour.setDateFrom(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_13() {
        tour.setDateFrom(new Date(System.currentTimeMillis()));
        tour.setCombined(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_14() {
        tour.setCombined(true);
        tour.setCurrency(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test
    public void nullSave() {
        tour.setCurrency(currency);
        service.saveTourAdvanced(tour);
        assertTrue(service.findAll().contains(tour));
        tour.setTransportType(null);
        service.updateTourAdvanced(tour);
        assertTrue(service.findAll().contains(tour));
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_16() {
        tour.setPrices(null);
        service.saveTourAdvanced(tour);
        service.findAll();
    }

    @Test
    public void nullSave_2() {
        tour.setPrices(new HashSet<>());
        tour.getPrices().add(price);
        service.saveTourAdvanced(tour);
        assertTrue(service.findAll().contains(tour));
        tour.setCity(null);
        service.updateTourAdvanced(tour);
        assertTrue(service.findAll().contains(tour));
    }
}
