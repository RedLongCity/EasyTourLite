package test.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.redlongcitywork.easytourlite.parsers.FacilityArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.FacilityNodeParser;
import com.redlongcitywork.easytourlite.parsers.HotelFilterArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.RegionArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.RegionNodeParser;
import com.redlongcitywork.easytourlite.parsers.TypeArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.TypeNodeParser;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author redlongcity 24/02/2018
 */
@ContextConfiguration
public class TestJsonConfig {

    @Configuration
    static class ContextConfiguration {

        private static final Logger LOG = Logger.getLogger(ContextConfiguration.class.getName());

        @Bean
        public RegionNodeParser regionNodeParser() {
            return new RegionNodeParser();
        }

        @Bean
        public RegionArrayNodeParser regionArrayNodeParser() {
            return new RegionArrayNodeParser();
        }

        @Bean
        public TypeNodeParser typeNodeParser() {
            return new TypeNodeParser();
        }

        @Bean
        public TypeArrayNodeParser typeArrayNodeParser() {
            return new TypeArrayNodeParser();
        }

        @Bean
        public HotelFilterArrayNodeParser hotelFilterArrayNodeParser() {
            return new HotelFilterArrayNodeParser();
        }

        @Bean
        public FacilityNodeParser facilityNodeParser() {
            return new FacilityNodeParser();
        }

        @Bean
        public FacilityArrayNodeParser facilityArrayNodeParser() {
            return new FacilityArrayNodeParser();
        }

        @Bean
        public JsonNode mainJsonNode() {
            return getJsonFromFile("json/api.json");
        }

        @Bean
        public JsonNode regionJsonNode() {
            JsonNode node = getJsonFromFile("json/region.json");
            return node.path("regions");
        }

        @Bean
        public JsonNode typeJsonNode() {
            JsonNode node = getJsonFromFile("json/type.json");
            return node.path("types");
        }

        @Bean
        public JsonNode hotelFilterNode() {
            JsonNode node = getJsonFromFile("json/hotel_filter.json");
            return node.path("hotels");
        }

        @Bean
        public JsonNode facilityNode() {
            JsonNode node = getJsonFromFile("json/facility.json");
            return node.path("hotel_facilities");
        }

        private JsonNode getJsonFromFile(String path) {
            JsonNode result = null;
            File file = new File(getClass().getResource(path).getFile());
            ObjectMapper mapper = new ObjectMapper();
            try {
                result = mapper.readTree(file);
            } catch (IOException ex) {
                Logger.getLogger(TestJsonConfig.class.getName()).log(Level.SEVERE, null, ex);
            }
            return result;
        }

    }
}
