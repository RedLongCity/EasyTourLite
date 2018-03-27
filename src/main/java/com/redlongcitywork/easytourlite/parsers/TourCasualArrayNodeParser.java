package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.TourCasual;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 19/02/2018
 */
@Service
public class TourCasualArrayNodeParser implements NodeParser<List<TourCasual>> {

    private static final Logger LOG = Logger.getLogger(TourCasualArrayNodeParser.class.getName());

    private final TourCasualNodeParser parser;

    public TourCasualArrayNodeParser(TourCasualNodeParser parser) {
        this.parser = parser;
    }

    @Override
    public List<TourCasual> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "TourCasualArrayNode: Node is missing");
            return null;
        }

        List<TourCasual> list = new ArrayList<TourCasual>();
        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }

        return list;
    }

}
