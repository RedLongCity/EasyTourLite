package test.service;

import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.service.SearchingRequestService;
import java.sql.Date;
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
import test.dao.Instances;
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
    private Instances instances;

    private SearchingRequest request;

    private Country country;

    private From_Cities city;

    private Meal_Type type;

    private Hotel_Rating rating;

    private Currency currency;

    @Before
    public void populate() {
        country = instances.getCountry();
        city = instances.getCity();
        type = instances.getMealType();
        rating = instances.getRating();
        currency = instances.getCurrency();
        instances.saveInstances();

        request = new SearchingRequest();
        request.setKind(1);
        request.setCountry(country);
        request.setCity(city);
        request.setHotel("HotelName");
        request.getRatingSet().add(rating);
        request.setAdultAmount(1);
        request.setChildAmount(1);
        request.setChildAge("1");
        request.setNightFrom(1);
        request.setNightTill(2);
        request.setDateFrom(instances.getDate());
        request.setDateTill(instances.getDate());
        request.getMealTypes().add(type);
        request.setPriceFrom(1);
        request.setPriceTill(2);
        request.setCurrency(currency);
        request.setOnlyStandart(1);
        request.setRequestTime(instances.getTimeStamp());

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
        set.add(rating);
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
