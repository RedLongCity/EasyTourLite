package test.parser;

import test.config.TestJsonConfig;
import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.parsers.RegionArrayNodeParser;
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
 * @author redlongcity 24/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
public class RegionParserTest extends TestJsonConfig {

    @Autowired
    private RegionArrayNodeParser parser;

    private JsonNode regionJsonNode;

    @Before
    public void populate() {
        regionJsonNode = getJsonFromFile("json/region.json").path("regions");
    }

    @Test
    public void parsingTest() {
        Region region = new Region();
        region.setId("8688");
        region.setName("Абано Терме");
        List<Region> list = new ArrayList<>();
        list.add(region);
        assertEquals(parser.parseNode(regionJsonNode), list);
    }

}
