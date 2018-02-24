package test.parser;

import com.fasterxml.jackson.databind.JsonNode;
import java.io.File;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;

/**
 *
 * @author redlongcity
 * 24/02/2018
 */
@ContextConfiguration
public class TestJsonConfig {
    
    @Configuration
    static class ContextConfiguration{
        
        @Bean
        public JsonNode mainJsonNode(){
                File file = new File(getClass().getResource("jsonschema.json").getFile());
JsonNode mySchema = JsonLoader.fromFile(file);
    }
    
}
