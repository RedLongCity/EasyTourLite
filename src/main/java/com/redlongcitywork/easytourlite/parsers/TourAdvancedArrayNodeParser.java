package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 04/03/2018
 */
@Service
public class TourAdvancedArrayNodeParser implements NodeParser<Set<TourAdvanced>> {

    private static final Logger LOG = Logger.getLogger(TourAdvancedArrayNodeParser.class.getName());

    private final TourAdvancedNodeParser parser;

    public TourAdvancedArrayNodeParser(TourAdvancedNodeParser parser) {
        this.parser = parser;
    }

    @Override
    public Set<TourAdvanced> parseNode(JsonNode arrayNode) {
        if (arrayNode != null && arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "TourAdvanceNode: arrayNode is missing");
            return null;
        }

        return StreamSupport.stream(arrayNode.spliterator(), false)
                .map(p -> parser.parseNode(p))
                .collect(Collectors.toSet());
    }

}
