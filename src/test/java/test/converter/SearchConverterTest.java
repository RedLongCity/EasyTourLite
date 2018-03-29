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
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.service.RegionService;
import com.redlongcitywork.easytourlite.service.TourAdvancedService;
import com.redlongcitywork.easytourlite.service.TypeService;
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

/**
 *
 * @author redlongcity 05/03/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class SearchConverterTest extends TestJPAConfig {

    @Autowired
    private SearchConvertor convertor;

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

    @Autowired
    private Hotel_RatingService ratingService;

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
        price.setCost(500);
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
        rating = new Hotel_Rating();
        rating.setId("id");
        rating.setName("name");

        countryService.saveCountry(country);
        typeService.saveType(type);
        regionService.saveRegion(region);
        mealTypeService.saveMeal_Type(mealType);
        currencyService.saveCurrency(currency);
        cityService.saveFrom_Cities(city);
        ratingService.saveHotel_Rating(rating);

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
        currency2.setId("2");
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
        request.getRegions().add(region);
        request.getRatingSet().add(rating);
        request.setAdultAmount(1);
        request.setChildAmount(2);
        request.setNightFrom(0);
        request.setNightTill(3);
        request.setDateFrom(Date.valueOf(LocalDate.parse("2000-01-01")));
        request.setDateTill(Date.valueOf(LocalDate.parse("2000-01-04")));
        request.getMealTypes().add(mealType);
        request.setCurrency(currency);
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
        String regions = null;
        if (request.getRegions() != null) {
            regions = new String("");
            boolean flag = true;
            for (Region entity : request.getRegions()) {
                if (flag) {
                    regions = regions.concat(entity.getId());
                    flag = false;
                } else {
                    regions = regions.concat(
                            ":" + entity.getId()
                    );
                }
            }
        }

        String ratings = null;
        if (request.getRatingSet() != null) {
            ratings = new String("");
            boolean flag = true;
            for (Hotel_Rating entity : request.getRatingSet()) {
                if (flag) {
                    ratings = ratings.concat(entity.getId());
                    flag = false;
                } else {
                    ratings = ratings.concat(
                            ":" + entity.getId()
                    );
                }
            }
        }

        String types = null;
        if (request.getMealTypes() != null) {
            types = new String("");
            boolean flag = true;
            for (Meal_Type entity : request.getMealTypes()) {
                if (flag) {
                    types = types.concat(entity.getId());
                    flag = false;
                } else {
                    types = types.concat(
                            ":" + entity.getId()
                    );
                }
            }
        }

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

        if (regions != null) {
            url = url.concat("&region=" + regions);
        }

        if (ratings != null) {
            url = url.concat("&hotel_rating=" + ratings);
        }

        if (types != null) {
            url = url.concat("&meal_type=" + types);
        }
        
        String result = convertor.getURLByRequest(request);
        assertEquals(url,convertor.getURLByRequest(request));
    }

}
