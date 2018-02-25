package test.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.parsers.TypeArrayNodeParser;
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
public class TypeParserTest extends TestJsonConfig {

    @Autowired
    private TypeArrayNodeParser parser;

    private JsonNode typeJsonNode;

    @Before
    public void populate() {
        typeJsonNode = getJsonFromFile("json/type.json").path("types");
    }

    @Test
    public void parsingTest() {
        Type type = new Type();
        type.setId("1");
        type.setName("Проезд включен");
        List<Type> list = new ArrayList<>();
        list.add(type);
        assertEquals(parser.parseNode(typeJsonNode), list);
    }

}
