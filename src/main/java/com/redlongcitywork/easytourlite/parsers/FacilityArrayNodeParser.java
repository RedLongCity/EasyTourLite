package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Facility;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 23/02/2018
 */
@Service
public class FacilityArrayNodeParser implements NodeParser<List<Facility>> {

    private static final Logger LOG = Logger.getLogger(FacilityArrayNodeParser.class.getName());

    @Autowired
    private FacilityNodeParser parser;

    @Override
    public List<Facility> parseNode(JsonNode arrayNode) {
        if (arrayNode != null && arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "FacilityNode: arrayNode is missing");
            return null;
        }

        List<Facility> list = new ArrayList<Facility>();

        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }
        return list;
    }
}
