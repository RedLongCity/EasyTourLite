package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Currency;
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
 * class for parsing List of Currencies from ArrayNode
 */
@Service
public class CurrencyArrayNodeParser implements NodeParser<List<Currency>> {

    private static final Logger LOG = Logger.getLogger(CurrencyArrayNodeParser.class.getName());

    @Autowired
    private CurrencyNodeParser parser;

    @Override
    public List<Currency> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "CurrencyNode: arrayNode is missing");
            return null;
        }

        List<Currency> list = new ArrayList<Currency>();
        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }
        return list;
    }

}
