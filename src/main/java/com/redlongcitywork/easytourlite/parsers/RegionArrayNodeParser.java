package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Region;
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
public class RegionArrayNodeParser implements NodeParser<List<Region>> {

    private static final Logger LOG = Logger.getLogger(TypeArrayNodeParser.class.getName());

    @Autowired
    private RegionNodeParser parser;

    @Override
    public List<Region> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "RegionNode: regionNode is missing");
            return null;
        }

        List<Region> list = new ArrayList<>();

        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }
        return list;
    }
}
