package test.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Facility;
import com.redlongcitywork.easytourlite.model.Hotel;
import com.redlongcitywork.easytourlite.model.Hotel_Image;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.TourCasual;
import com.redlongcitywork.easytourlite.parsers.FacilityArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.HotelArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.HotelImageArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.HotelNodeParser;
import com.redlongcitywork.easytourlite.parsers.TourCasualArrayNodeParser;
import com.redlongcitywork.easytourlite.storage.CountryStorage;
import com.redlongcitywork.easytourlite.storage.HotelRatingStorage;
import com.redlongcitywork.easytourlite.storage.RegionStorage;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyObject;
import org.mockito.Mock;
import static org.mockito.Mockito.stub;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author redlongcity 25/02/2018
 */
@RunWith(MockitoJUnitRunner.class)
public class HotelParserTest extends TestJsonConfig {

    @Mock
    private RegionStorage regionStorage;

    @Mock
    private HotelRatingStorage ratingStorage;

    @Mock
    private CountryStorage countryStorage;

    @Mock
    private TourCasualArrayNodeParser tourParser;

    @Mock
    private FacilityArrayNodeParser facilityParser;

    @Mock
    private HotelImageArrayNodeParser imageParser;

    @InjectMocks
    private HotelNodeParser nodeParser = new HotelNodeParser(
            facilityParser,
            imageParser,
            tourParser,
            regionStorage,
            ratingStorage,
            countryStorage
    );

    private HotelArrayNodeParser arrayNodeParser
            = new HotelArrayNodeParser(nodeParser);

    private JsonNode node;

    @Before
    public void populate() {
        node = getJsonFromFile("json/hotel.json").path("hotels");
    }

    @PostConstruct
    @Test
    public void parsingTest() {
        Country country = new Country();
        country.setName("Турция");
        Region region = new Region();
        region.setId("5186");
        region.setName("Аланья");
        Hotel_Rating rating = new Hotel_Rating();
        rating.setName("4");

        List<Country> countryList = new ArrayList<>();
        List<Region> regionList = new ArrayList<>();
        List<Hotel_Rating> ratingList = new ArrayList<>();

        countryList.add(country);
        regionList.add(region);
        ratingList.add(rating);

        stub(ratingStorage.getContent()).toReturn(ratingList);
        stub(regionStorage.getContent()).toReturn(regionList);
        stub(countryStorage.getContent()).toReturn(countryList);
        stub(tourParser.parseNode(anyObject())).toReturn(new ArrayList<TourCasual>());

        Facility facility = new Facility();
        facility.setId("1");
        facility.setName("Wi-fi");
        facility.setCategory("Интернет");
        facility.setCategoryId("1");
        facility.setMain(false);
        facility.setPaid(false);
        List<Facility> facilityList = new ArrayList<>();
        facilityList.add(facility);
        stub(facilityParser.parseNode(anyObject())).toReturn(facilityList);

        Hotel_Image image = new Hotel_Image();
        image.setFull("http://ittour.com.ua/images/hotel_image/3/0/4/7/8/4/file_name/304784.jpg");
        image.setThumb("http://ittour.com.ua/images/hotel_image_thumb/3/0/4/7/8/4/file_name/304784.jpg");
        List<Hotel_Image> imageList = new ArrayList<>();
        imageList.add(image);
        stub(imageParser.parseNode(anyObject())).toReturn(imageList);

        Hotel hotel = new Hotel();
        hotel.setCountry(country);
        hotel.setRegion(region);
        hotel.setRating(rating);
        hotel.setAdultAmount(2);
        hotel.setChildAmount(0);
        hotel.setAccomodation("DBL");
        hotel.setHotelName("Xeno Club Mare (ex.Porto Azzurro Club Mare)");
        hotel.setHotelReviewRate("2.3");
        hotel.setHotelReviewCount("12");
        hotel.getFacilities().add(facility);
        hotel.setLat("36.47030");
        hotel.setLng("32.11530");
        hotel.setWifiFree(true);
        hotel.getImages().add(image);
        List<Hotel> hotelList = new ArrayList<>();
        hotelList.add(hotel);
        List<Hotel> entityList = arrayNodeParser.parseNode(node);
        assertEquals(entityList, hotelList);
    }

}
