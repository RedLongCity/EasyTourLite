package test.dao;

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
import com.redlongcitywork.easytourlite.service.SearchingRequestService;
import com.redlongcitywork.easytourlite.service.TypeService;
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import org.springframework.beans.factory.annotation.Autowired;
import test.database.TestJPAConfig;

/**
 *
 * @author redlongcity 24/03/2018
 */
public class Instances extends TestJPAConfig {

    public static final String STR = "STR";
    public static final int INT = 1;

    @Autowired
    private From_CitiesService cityService;

    @Autowired
    private CountryService countryService;

    @Autowired
    private TypeService typeService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private Hotel_RatingService ratingService;

    @Autowired
    private Meal_TypeService mealTypeService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private SearchingRequestService searchingRequestService;

    private From_Cities city;

    private Country country;

    private Type type;

    private Region region;

    private Hotel_Rating rating;

    private Meal_Type mealType;

    private Currency currency;

    private SearchingRequest searchingRequest;

    public Country getCountry() {
        if (country == null) {
            country = new Country();
            country.setId(STR);
            country.setName(STR);
            countryService.saveCountry(country);
        }
        return country;
    }

    public From_Cities getCity() {
        if (city == null) {
            city = new From_Cities();
            city.setId(STR);
            city.setName(STR);
            cityService.saveFrom_Cities(city);
        }
        return city;
    }

    public Type getType() {
        if (type == null) {
            type = new Type();
            type.setId(STR);
            type.setName(STR);
            typeService.saveType(type);
        }
        return type;
    }

    public Region getRegion() {
        if (region == null) {
            region = new Region();
            region.setId(STR);
            region.setName(STR);
            regionService.saveRegion(region);
        }
        return region;
    }

    public Hotel_Rating getRating() {
        if (rating == null) {
            rating = new Hotel_Rating();
            rating.setId(STR);
            rating.setName(STR);
            ratingService.saveHotel_Rating(rating);
        }
        return rating;
    }

    public Meal_Type getMealType() {
        if (mealType == null) {
            mealType = new Meal_Type();
            mealType.setName(STR);
            mealType.setId(STR);
            mealType.setName_Full(STR);
            mealTypeService.saveMeal_Type(mealType);
        }
        return mealType;
    }

    public Currency getCurrency() {
        if (currency == null) {
            currency = new Currency();
            currency.setId(STR);
            currency.setName(STR);
            currencyService.saveCurrency(currency);
        }
        return currency;
    }

    public Price getPrice() {
        Price result = new Price();
        result.setCost(INT);
        result.setCurrency(getCurrency());
        return result;
    }

    public Hotel_Image getHotelImage() {
        Hotel_Image result = new Hotel_Image();
        result.setThumb(STR);
        result.setFull(STR);
        return result;
    }

    public Facility getFacility() {
        Facility result = new Facility();
        result.setId(STR);
        result.setName(STR);
        result.setCategoryId(STR);
        result.setCategory(STR);
        result.setMain(true);
        result.setPaid(true);
        return result;
    }

    public SearchingRequest getSearchingRequest() {
        if (searchingRequest == null) {
            searchingRequest = new SearchingRequest();
            searchingRequest.setType(getType());
            searchingRequest.setKind(INT);
            searchingRequest.setCountry(getCountry());
            searchingRequest.setCity(getCity());
            searchingRequest.getRegions().add(getRegion());
            searchingRequest.setHotel(STR);
            searchingRequest.getRatingSet().add(getRating());
            searchingRequest.setAdultAmount(INT);
            searchingRequest.setChildAmount(INT);
            searchingRequest.setChildAge(STR);
            searchingRequest.setNightFrom(INT);
            searchingRequest.setNightTill(INT);
            searchingRequest.setDateFrom(getDate());
            searchingRequest.setDateTill(getDate());
            searchingRequest.getMealTypes().add(getMealType());
            searchingRequest.setPriceFrom(INT);
            searchingRequest.setPriceTill(INT);
            searchingRequest.setCurrency(getCurrency());
            searchingRequest.setRequestTime(getTimeStamp());
            searchingRequestService.saveSearchingRequest(searchingRequest);
        }
        return searchingRequest;
    }

    public TourAdvanced getTourAdvanced() {
        TourAdvanced result = new TourAdvanced();
        result.setKey(STR);
        result.setCountry(getCountry());
        result.setType(getType());
        result.setRegion(getRegion());
        result.setHotelId(INT);
        result.setHotelName(STR);
        result.setRating(getRating());
        result.setMealType(getMealType());
        result.setAdultAmount(INT);
        result.setChildAmount(INT);
        result.setAccomodation(STR);
        result.setRoomType(STR);
        result.setDuration(INT);
        result.setDateFrom(getDate());
        result.setCombined(true);
        result.setCurrency(getCurrency());
        result.getPrices().add(getPrice());
        result.setCity(getCity());
        result.setTransportType(STR);
        result.getImages().add(getHotelImage());
        result.setRate(STR);
        result.setReviewCount(STR);
        result.getFacilities().add(getFacility());
        return result;
    }

    public Date getDate() {
        return new Date(System.currentTimeMillis());
    }

    public Date getDate(String str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return (Date) formatter.parse(str);
    }

    public Timestamp getTimeStamp() {
        return new Timestamp(getDate().getTime());
    }
}
