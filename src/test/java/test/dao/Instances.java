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
import java.sql.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 *
 * @author redlongcity 24/03/2018
 */
public class Instances {

    public static final String STR = "STR";
    public static final int INT = 1;

    public static Country getCountry() {
        Country result = new Country();
        result.setId(STR);
        result.setName(STR);
        return result;
    }

    public static From_Cities getCity() {
        From_Cities result = new From_Cities();
        result.setId(STR);
        result.setName(STR);
        return result;
    }

    public static Type getType() {
        Type result = new Type();
        result.setId(STR);
        result.setName(STR);
        return result;
    }

    public static Region getRegion() {
        Region result = new Region();
        result.setId(STR);
        result.setName(STR);
        return result;
    }

    public static Hotel_Rating getRating() {
        Hotel_Rating result = new Hotel_Rating();
        result.setId(STR);
        result.setName(STR);
        return result;
    }

    public static Meal_Type getMealType() {
        Meal_Type result = new Meal_Type();
        result.setName(STR);
        result.setId(STR);
        result.setName_Full(STR);
        return result;
    }

    public static Currency getCurrency() {
        Currency result = new Currency();
        result.setId(STR);
        result.setName(STR);
        return result;
    }

    public static Price getPrice() {
        Price result = new Price();
        result.setId(INT);
        result.setCost(INT);
        result.setCurrency(getCurrency());
        return result;
    }

    public static Hotel_Image getHotelImage() {
        Hotel_Image result = new Hotel_Image();
        result.setId(INT);
        result.setThumb(STR);
        result.setFull(STR);
        return result;
    }

    public static Facility getFacility() {
        Facility result = new Facility();
        result.setId(STR);
        result.setName(STR);
        result.setCategoryId(STR);
        result.setMain(true);
        result.setPaid(true);
        return result;
    }

    public static SearchingRequest getSearchingRequest() {
        SearchingRequest result = new SearchingRequest();
        result.setId(INT);
        result.setType(getType());
        result.setKind(INT);
        result.setCountry(getCountry());
        result.setCity(getCity());
        result.getRegions().add(getRegion());
        result.setHotel(STR);
        result.getRatingSet().add(getRating());
        result.setAdultAmount(INT);
        result.setChildAmount(INT);
        result.setChildAge(STR);
        result.setNightFrom(INT);
        result.setNightTill(INT);
        result.setDateFrom(getDate());
        result.setDateTill(getDate());
        result.getMealTypes().add(getMealType());
        result.setPriceFrom(INT);
        result.setPriceTill(INT);
        result.setCurrency(getCurrency());
        result.setRequestTime(getTimeStamp());
        return result;
    }

    public static TourAdvanced getTourAdvanced() {
        TourAdvanced result = new TourAdvanced();
        result.setKey(STR);
        result.setCountry(getCountry());
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

    public static Date getDate() {
        return new Date(System.currentTimeMillis());
    }

    public static Date getDate(String str) throws ParseException {
        SimpleDateFormat formatter = new SimpleDateFormat("dd.MM.yyyy");
        return (Date) formatter.parse(str);
    }

    public static Timestamp getTimeStamp() {
        return new Timestamp(getDate().getTime());
    }
}
