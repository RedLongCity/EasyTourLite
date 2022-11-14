package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * class for parsing From_Cities.class from JsonNode
 */
@Service
public class CitiesNodeParser implements NodeParser<From_Cities> {

    private static final Logger LOG = Logger.getLogger(CitiesNodeParser.class.getName());

    @Override
    public From_Cities parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "From_CitiesNode: from_CitiesNode is missing");
            return null;
        }

        From_Cities city = new From_Cities();

        if (jsonNode.has("id")) {
            city.setId(jsonNode.path("id").asText());
        }

        if (jsonNode.has("name")) {
            city.setName(jsonNode.path("name").asText());
        }
        return city;
    }

}
