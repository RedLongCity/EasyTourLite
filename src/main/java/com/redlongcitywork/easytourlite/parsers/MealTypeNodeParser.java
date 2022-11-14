package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * class for parsing Meal_Type.class from JsonNode
 */
@Service
public class MealTypeNodeParser implements NodeParser<Meal_Type> {

    private static final Logger LOG = Logger.getLogger(MealTypeNodeParser.class.getName());

    @Override
    public Meal_Type parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "Meal_TypeNode: meal_typeNode is misssing");
            return null;
        }

        Meal_Type type = new Meal_Type();

        if (jsonNode.has("id")) {
            type.setId(jsonNode.path("id").asText());
        }

        if (jsonNode.has("name")) {
            type.setName(jsonNode.path("name").asText());
        }

        if (jsonNode.has("name_full")) {
            type.setName_Full(jsonNode.path("name_full").asText());
        }

        return type;
    }

}
