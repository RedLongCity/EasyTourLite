package test.parser;

import test.config.TestJsonConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Facility;
import com.redlongcitywork.easytourlite.parsers.FacilityArrayNodeParser;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author redlongcity 25/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class FacilityParserTest extends TestJsonConfig {

    @Autowired
    private FacilityArrayNodeParser parser;

    private JsonNode facilityNode;

    @Before
    public void populate() {
        facilityNode = getJsonFromFile("json/facility.json").path("hotel_facilities");
    }

    @Test
    public void parsingTest() {
        List<Facility> list = new ArrayList<>();
        Facility facility = new Facility();
        facility.setId("1");
        facility.setName("Wi-fi");
        facility.setCategory("Интернет");
        facility.setCategoryId("1");
        facility.setMain(false);
        facility.setPaid(false);
        list.add(facility);
        assertEquals(parser.parseNode(facilityNode), list);
    }

}
