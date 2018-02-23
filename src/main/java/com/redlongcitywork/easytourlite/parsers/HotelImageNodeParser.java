package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Hotel_Image;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 23/02/2018
 */
@Service
public class HotelImageNodeParser implements NodeParser<Hotel_Image> {

    private static final Logger LOG = Logger.getLogger(HotelImageNodeParser.class.getName());

    @Override
    public Hotel_Image parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "Hotel_ImageNode: countriesNode is missing");
            return null;
        }

        Hotel_Image image = new Hotel_Image();

        if (jsonNode.has("full:")) {
            image.setFull(jsonNode.path("full:").asText());
        }

        if (jsonNode.has("thumb")) {
            image.setThumb(jsonNode.path("thumb").asText());
        }

        return image;
    }
}
