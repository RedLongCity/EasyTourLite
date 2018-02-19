package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Type;
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
public class RegionArrayNodeParser implements NodeParser<List<Type>> {

    private static final Logger LOG = Logger.getLogger(TypeArrayNodeParser.class.getName());

    @Autowired
    private TypeNodeParser parser;

    @Override
    public List<Type> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "RegionNode: regionNode is missing");
            return null;
        }

        List<Type> list = new ArrayList<Type>();

        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }
        return list;
    }
}
