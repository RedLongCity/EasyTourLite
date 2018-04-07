package test.dao;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationConfig;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import test.config.TestJPAConfig;

/**
 *
 * @author redlongcity 05/04/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class SearchingRequestJson extends TestJPAConfig {

    private SearchingRequest request;

    @Before
    public void populate() {
        request = new SearchingRequest();
//        request.setType(new Type("1"));
//        request.setCountry(new Country("338", "Египет"));
//        request.setCity(new From_Cities("2014", "Киев"));
        request.getRegions().add(new Region("5996", "Шарм Эль Шейх"));
//        request.getRatingSet().add(new Hotel_Rating("4", "4"));
//        request.getMealTypes().add(new Meal_Type("388", "BB", "Завтрак"));
//        request.setAdultAmount(2);
//        request.setNightFrom(6);
//        request.setNightTill(7);
//        request.setDateFrom(parseForDate("2017-01-25"));
//        request.setDateTill(parseForDate("2017-01-27"));
    }

    @Test
    public void test() {
        try {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(request);
            System.out.println(jsonInString);
        } catch (JsonProcessingException ex) {
            Logger.getLogger(SearchingRequestJson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private Date parseForDate(String str) {
        return Date.valueOf(LocalDate.parse(str));
    }

}
