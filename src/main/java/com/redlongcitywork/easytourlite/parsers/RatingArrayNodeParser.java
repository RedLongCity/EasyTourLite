package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 15/12/2017
 * class for parsinf List of Rating from ArrayNode
 */
@Service
public class RatingArrayNodeParser implements NodeParser<List<Hotel_Rating>> {

    private static final Logger LOG = Logger.getLogger(RatingArrayNodeParser.class.getName());

    @Autowired
    private RatingNodeParser parser;

    @Override
    public List<Hotel_Rating> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "RatingNode: arrayNode is missing");
            return null;
        }
        List<Hotel_Rating> list = new ArrayList<Hotel_Rating>();

        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }
        return list;
    }

}
