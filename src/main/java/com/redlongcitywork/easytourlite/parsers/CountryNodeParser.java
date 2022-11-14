package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import com.redlongcitywork.easytourlite.model.Country;
import java.util.logging.Level;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * class for parsing Country.class from JsonNode
 */
@Service
public class CountryNodeParser implements NodeParser<Country> {

    private static final Logger LOG = Logger.getLogger(CountryNodeParser.class.getName());

    @Override
    public Country parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "CountryNode: countriesNode is missing");
            return null;
        }

        Country country = new Country();

        if (jsonNode.has("id")) {
            country.setId(jsonNode.path("id").asText());
        }

        if (jsonNode.has("name")) {
            country.setName(jsonNode.path("name").asText());
        }

        return country;
    }

}
