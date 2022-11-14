package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * class for parsing Hotel_ratingNode
 */
@Service
public class RatingNodeParser implements NodeParser<Hotel_Rating> {

    private static final Logger LOG = Logger.getLogger(RatingNodeParser.class.getName());

    @Override
    public Hotel_Rating parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "Hotel_Rating: hotel_ratingNode is missing");
            return null;
        }

        Hotel_Rating rating = new Hotel_Rating();

        if (jsonNode.has("id")) {
            rating.setId(jsonNode.path("id").asText());
        }

        if (jsonNode.has("name")) {
            rating.setName(jsonNode.path("name").asText());
        }

        return rating;
    }

}
