package test.converter;

import com.redlongcitywork.easytourlite.convertor.SearchConvertor;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.Facility;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Image;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.SearchingRequestService;
import com.redlongcitywork.easytourlite.service.TourAdvancedService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import test.config.TestJPAConfig;
import test.dao.Instances;

/**
 *
 * @author redlongcity 05/03/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SearchConverterTest extends TestJPAConfig {

    @Autowired
    private TourAdvancedService service;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private SearchingRequestService requestService;

    @Autowired
    private Instances instances;

    @Autowired
    private SearchConvertor convertor;

    private TourAdvanced tour;

    private Country country;

    private Type type;

    private Region region;

    private Meal_Type mealType;

    private Currency currency;

    private Currency currency2;

    private Price price;

    private Price price2;

    private Hotel_Image image;

    private Facility facility;

    private From_Cities city;

    private Hotel_Rating rating;

    private TourAdvanced tourTwo;

    private SearchingRequest request;

    @Before
    public void populate() {
        tour = new TourAdvanced();
        country = instances.getCountry();
        type = instances.getType();
        region = instances.getRegion();
        mealType = instances.getMealType();
        currency = instances.getCurrency();
        price = instances.getPrice();
        price.setCost(500);
        image = instances.getHotelImage();
        facility = instances.getFacility();
        city = instances.getCity();
        rating = instances.getRating();
        instances.saveInstances();

//        stub(mealTypeStorage.findById(anyString())).toReturn(mealType);
//        stub(regionStorage.findById(anyString())).toReturn(region);
//        stub(ratingStorage.findById(anyString())).toReturn(rating);
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
        tour.setDateFrom(Date.valueOf(LocalDate.parse("2000-01-03")));
        tour.setCombined(true);
        tour.setCurrency(currency);
        tour.getPrices().add(price);
        tour.setCity(city);
        tour.setTransportType("flight");
        tour.getImages().add(image);
        tour.setRate("rate");
        tour.setReviewCount("count");
        tour.getFacilities().add(facility);
        tour.setRating(rating);

        currency2 = new Currency();
        currency2.setId("id2");
        currency2.setName("Currency");
        price2 = new Price();
        price2.setCurrency(currency2);
        price2.setCost(900);
        currencyService.saveCurrency(currency2);

        tourTwo = new TourAdvanced();
        tourTwo.setKey("keyTwo");
        tourTwo.setCountry(country);
        tourTwo.setType(type);
        tourTwo.setRegion(region);
        tourTwo.setHotelId(1);
        tourTwo.setHotelName("name");
        tourTwo.setMealType(mealType);
        tourTwo.setAdultAmount(1);
        tourTwo.setChildAmount(2);
        tourTwo.setAccomodation("accomodation");
        tourTwo.setRoomType("type");
        tourTwo.setDuration(3);
        tourTwo.setDateFrom(Date.valueOf(LocalDate.parse("2000-01-01")));
        tourTwo.setCombined(true);
        tourTwo.setCurrency(currency);
        tourTwo.getPrices().add(price2);
        tourTwo.setCity(city);
        tourTwo.setTransportType("bus");
        tourTwo.getImages().add(image);
        tourTwo.setRate("rate");
        tourTwo.setReviewCount("count");
        tourTwo.getFacilities().add(facility);
        tourTwo.setRating(rating);

        request = new SearchingRequest();
        request.setType(type);
        request.setKind(1);
        request.setCountry(country);
        request.setCity(city);
        request.setRegions(region.getId());
        request.setRatings(rating.getId());
        request.setAdultAmount(1);
        request.setChildAmount(2);
        request.setNightFrom(0);
        request.setNightTill(3);
        request.setDateFrom(instances.getDate("2000-01-01"));
        request.setDateTill(instances.getDate("2000-01-04"));
        request.setMealTypes(mealType.getId());
        request.setCurrency(currency);
        request.setRequestTime(instances.getTimeStamp());
    }

    @Test
    public void convertTest() {
        service.saveTourAdvanced(tour);
        service.saveTourAdvanced(tourTwo);
        assertTrue(service.findByPrices(100, 1000, currency).contains(tour));
        assertFalse(service.findByPrices(100, 1000, currency).contains(tourTwo));
        assertTrue(service.findByPrices(100, 1000, currency2).contains(tourTwo));
        assertFalse(service.findByPrices(100, 1000, currency2).contains(tour));

        price2.setCurrency(currency);
        service.updateTourAdvanced(tourTwo);
        List<TourAdvanced> list = service.findByPrices(100, 1000, currency);
        assertTrue(list.contains(tour) && list.contains(tourTwo));
        list = service.findByCriterions(convertor.getCriterionsByRequest(request));
        assertTrue(list.contains(tour) && !list.contains(tourTwo));
        tourTwo.setTransportType("flight");
        service.updateTourAdvanced(tourTwo);
        list = service.findByCriterions(convertor.getCriterionsByRequest(request));
        assertTrue(list.contains(tour) && list.contains(tourTwo));
        tour.setDuration(2);
        service.updateTourAdvanced(tour);
        tourTwo.setDuration(2);
        service.updateTourAdvanced(tourTwo);
        list = service.findByCriterions(convertor.getCriterionsByRequest(request));
        assertFalse(list.contains(tour) && list.contains(tourTwo));
    }

    @Test
    public void urlConvertTest() {
        String url = "?type=" + request.getType().getId()
                + "&kind=" + request.getKind()
                + "&country=" + request.getCountry().getId()
                + "&from_city=" + request.getCity().getId()
                + "&adult_amount=" + request.getAdultAmount()
                + "&child_amount=" + request.getChildAmount()
                + "&night_from=" + request.getNightFrom()
                + "&night_till=" + request.getNightTill()
                + "&date_from=" + "01.01.00"
                + "&date_till=" + "04.01.00"
                + "&currency=" + request.getCurrency().getId();

        if (request.getRegions() != null) {
            url = url.concat("&region=" + request.getRegions());
        }

        if (request.getRatings() != null) {
            url = url.concat("&hotel_rating=" + request.getRatings());
        }

        if (request.getMealTypes() != null) {
            url = url.concat("&meal_type=" + request.getMealTypes());
        }

        String result = convertor.getURLByRequest(request);
        assertEquals(url, convertor.getURLByRequest(request));
    }

    @Test
    public void selectTest() {
        requestService.saveOrUpdateSearchingRequest(request);
        SearchingRequest request2 = new SearchingRequest();
        request2.setType(type);
        request2.setKind(1);
        request2.setCountry(country);
        request2.setCity(city);
        request2.setRegions(region.getId());
        request2.setRatings(rating.getId() + ":id2");
        request2.setAdultAmount(1);
        request2.setChildAmount(2);
        request2.setNightFrom(0);
        request2.setNightTill(3);
        request2.setDateFrom(instances.getDate("2000-01-01"));
        request2.setDateTill(instances.getDate("2000-01-04"));
        request2.setMealTypes(mealType.getId());
        request2.setCurrency(currency);
        request2.setRequestTime(instances.getTimeStamp());
        requestService.saveOrUpdateSearchingRequest(request2);

        assertTrue(requestService.findByCriterions(
                convertor.getRequestCriterions(request)).equals(request));
        assertTrue(requestService.findByCriterions(
                convertor.getRequestCriterions(request2)).equals(request2));
        assertFalse(requestService.findByCriterions(
                convertor.getRequestCriterions(request)).equals(request2));
        assertFalse(requestService.findByCriterions(
                convertor.getRequestCriterions(request2)).equals(request));
    }

}
