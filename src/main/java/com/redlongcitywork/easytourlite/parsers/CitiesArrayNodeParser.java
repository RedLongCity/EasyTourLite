package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.From_Cities;
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
 * class for parsing List of From_Citites from ArrayNode
 */
@Service
public class CitiesArrayNodeParser implements NodeParser<List<From_Cities>> {

    private static final Logger LOG = Logger.getLogger(CitiesArrayNodeParser.class.getName());

    @Autowired
    private CitiesNodeParser parser;

    @Override
    public List<From_Cities> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "CityNode: arrayNode is missing");
            return null;
        }
        List<From_Cities> list = new ArrayList<From_Cities>();

        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }
        return list;
    }
}
