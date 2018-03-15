package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 04/03/2018
 */
@Service
public class TourAdvancedArrayNodeParser implements NodeParser<List<TourAdvanced>> {

    private static final Logger LOG = Logger.getLogger(TourAdvancedArrayNodeParser.class.getName());

    private final TourAdvancedNodeParser parser;

    public TourAdvancedArrayNodeParser(TourAdvancedNodeParser parser) {
        this.parser = parser;
    }

    @Override
    public List<TourAdvanced> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "TourAdvanceNode: arrayNode is missing");
            return null;
        }

        List<TourAdvanced> list = new ArrayList<>();
        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }
        return list;
    }

}
