package test.parser;

import test.config.TestJsonConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.HotelFilter;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.parsers.HotelFilterArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.HotelFilterNodeParser;
import com.redlongcitywork.easytourlite.storage.HotelRatingStorage;
import com.redlongcitywork.easytourlite.storage.RegionStorage;
import com.redlongcitywork.easytourlite.storage.TypeStorage;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.stub;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author redlongcity 24/02/2018
 */
@RunWith(MockitoJUnitRunner.class)
public class HotelFilterParserTest extends TestJsonConfig {

    @Mock
    private RegionStorage regionStorage;

    @Mock
    private HotelRatingStorage ratingStorage;

    @Mock
    private TypeStorage typeStorage;

    @InjectMocks
    private HotelFilterNodeParser nodeParser
            = new HotelFilterNodeParser(ratingStorage, regionStorage, typeStorage);

    private HotelFilterArrayNodeParser parser
            = new HotelFilterArrayNodeParser(nodeParser);

    private JsonNode hotelFilterNode;

    @Before
    public void populate() {
        hotelFilterNode = getJsonFromFile("json/hotel_filter.json").path("hotels");
    }

    @Test
    public void parsingTest() {
        List<Region> regionList = new ArrayList<>();
        List<Hotel_Rating> ratingList = new ArrayList<>();
        List<Type> typeList = new ArrayList<>();
        Hotel_Rating rating = new Hotel_Rating();
        rating.setId("4");
        rating.setName("4");
        Region region = new Region();
        region.setId("5498");
        regionList.add(region);
        ratingList.add(rating);
        typeList.add(new Type("1"));
        typeList.add(new Type("2"));

        stub(regionStorage.getContent()).toReturn(regionList);
        stub(ratingStorage.getContent()).toReturn(ratingList);
        stub(typeStorage.getContent()).toReturn(typeList);

        List<HotelFilter> filterList = new ArrayList<>();
        HotelFilter filter = new HotelFilter();
        filter.setId("1005594");
        filter.setName("Ac Hotel Istanbul Macka By Marriott");
        filter.setRating(rating);
        filter.setRegion(region);
        filterList.add(filter);

        assertEquals(parser.parseNode(hotelFilterNode), filterList);
    }

}
