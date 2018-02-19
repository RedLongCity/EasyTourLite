package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Type;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 19/02/2018
 */
@Service
public class TypeNodeParser implements NodeParser<Type> {

    private static final Logger LOG = Logger.getLogger(TypeNodeParser.class.getName());

    @Override
    public Type parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "TypeNode: typeNode is missing");
            return null;
        }

        Type type = new Type();

        if (jsonNode.has("id")) {
            type.setId(jsonNode.path("id").asText());
        }

        if (jsonNode.has("name")) {
            type.setName(jsonNode.path("name").asText());
        }

        return type;
    }

}
