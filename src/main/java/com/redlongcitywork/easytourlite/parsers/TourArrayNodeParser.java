package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.model.Tour;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 
 * 21/12/2017 
 * class for parsing List of Tour from Array Node
 */
@Service
public class TourArrayNodeParser implements NodeParser<List<Tour>> {

    private static final Logger LOG = Logger.getLogger(TourArrayNodeParser.class.getName());

    @Autowired
    private TourNodeParser parser;

    @Override
    public List<Tour> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "TourNode: arrayNode is missing");
            return null;
        }

        List<Tour> list = new ArrayList<Tour>();
        ArrayNode offersNode = (ArrayNode) arrayNode.path("offers");

        for (int i = 0; i < offersNode.size(); i++) {
            list.add(parser.parseNode(offersNode.get(i)));
        }

        return list;
    }

}
