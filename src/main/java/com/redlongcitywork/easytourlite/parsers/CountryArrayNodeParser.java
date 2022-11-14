package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Country;
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
 * class for parsing List of Countries from ArrayNode
 */
@Service
public class CountryArrayNodeParser implements NodeParser<List<Country>> {

    private static final Logger LOG = Logger.getLogger(CountryArrayNodeParser.class.getName());

    @Autowired
    private CountryNodeParser parser;

    @Override
    public List<Country> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "CountryNode: arrayNode is missing");
            return null;
        }

        List<Country> list = new ArrayList<Country>();

        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }
        return list;
    }

}
