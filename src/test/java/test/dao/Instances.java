package test.dao;

import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.Type;
import java.sql.Date;

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
    }
}
