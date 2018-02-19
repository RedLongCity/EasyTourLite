package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.HotelFilter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 19/02/2018
 */
@Service
public class HotelFilterArrayNodeParser implements NodeParser<List<HotelFilter>> {

    private static final Logger LOG = Logger.getLogger(HotelFilterArrayNodeParser.class.getName());

    @Autowired
    private HotelFilterNodeParser parser;

    @Override
    public List<HotelFilter> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "HotelFilterNode: arrayNode is missing");
            return null;
        }

        List<HotelFilter> list = new ArrayList<HotelFilter>();

        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }
        return list;
    }
}
