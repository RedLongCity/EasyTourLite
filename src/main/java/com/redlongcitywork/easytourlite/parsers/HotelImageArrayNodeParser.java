package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Hotel_Image;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 23/02/2018
 */
@Service
public class HotelImageArrayNodeParser
        implements NodeParser<List<Hotel_Image>> {

    private static final Logger LOG = Logger.getLogger(HotelImageArrayNodeParser.class.getName());

    @Autowired
    private HotelImageNodeParser parser;

    @Override
    public List<Hotel_Image> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "Hotel_ImageNode: arrayNode is missing");
            return null;
        }

        return StreamSupport.stream(arrayNode.spliterator(), false)
                .map(e -> parser.parseNode(e))
                .collect(Collectors.toList());
    }
}
