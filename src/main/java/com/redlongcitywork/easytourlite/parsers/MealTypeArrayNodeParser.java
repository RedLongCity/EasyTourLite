package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Meal_Type;
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
 * class for parsing List of Meal_Type from ArrayNode
 */
@Service
public class MealTypeArrayNodeParser implements NodeParser<List<Meal_Type>> {

    private static final Logger LOG = Logger.getLogger(MealTypeArrayNodeParser.class.getName());

    @Autowired
    private MealTypeNodeParser parser;

    @Override
    public List<Meal_Type> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "MealTypeNode: arrayNode is missing");
            return null;
        }

        List<Meal_Type> list = new ArrayList<Meal_Type>();

        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }
        
        return list;
    }

}
