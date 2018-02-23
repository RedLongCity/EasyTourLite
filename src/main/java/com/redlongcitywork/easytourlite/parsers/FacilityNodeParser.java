package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Facility;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 23/02/2018
 */
@Service
public class FacilityNodeParser implements NodeParser<Facility> {

    private static final Logger LOG = Logger.getLogger(FacilityNodeParser.class.getName());

    @Override
    public Facility parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "FacilityNode: countriesNode is missing");
            return null;
        }

        Facility facility = new Facility();

        if (jsonNode.has("id")) {
            facility.setId(jsonNode.path("id").asText());
        }

        if (jsonNode.has("name")) {
            facility.setName(jsonNode.path("name").asText());
        }

        if (jsonNode.has("category_id")) {
            facility.setCategoryId(jsonNode.path("category_id").asText());
        }

        if (jsonNode.has("category")) {
            facility.setCategory(jsonNode.path("category").asText());
        }

        if (jsonNode.has("is_main")) {
            facility.setMain(1 == (jsonNode.path("is_main").asInt()));
        }

        if (jsonNode.has("is_paid")) {
            facility.setPaid(1 == (jsonNode.path("is_paid").asInt()));
        }
        return facility;
    }
}
