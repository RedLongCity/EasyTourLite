package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.model.Tour;
import java.util.HashSet;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 22/12/2017 class for parsing List of Tour from ArrayNode
 */
@Service
public class TourArrayNodeParser implements NodeParser<Set<Tour>> {

    private static final Logger LOG = Logger.getLogger(TourArrayNodeParser.class.getName());

    private final TourNodeParser parser;

    public TourArrayNodeParser(TourNodeParser parser) {
        this.parser = parser;
    }

    @Override
    public Set<Tour> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "TourNode: arrayNode is missing");
            return null;
        }

        Set<Tour> set = new HashSet<Tour>();
        ArrayNode offersNode = (ArrayNode) arrayNode.path("offers");
        for (int i = 0; i < offersNode.size(); i++) {
            set.add(parser.parseNode(offersNode.get(i)));
        }

        return set;
    }

}
