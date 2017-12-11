package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * class for parsing From_Cities.class from JsonNode
 */
@Service
public class From_CitiesNodeParser implements NodeParser {

    private static final Logger LOG = Logger.getLogger(From_CitiesNodeParser.class.getName());

    @Autowired
    From_CitiesService service;
    
    @Override
    public Boolean parseNode(ArrayNode from_CitiesNode) {
        if (from_CitiesNode.isMissingNode()) {
            LOG.log(Level.WARNING, "From_CitiesNode: from_CitiesNode is missing");
            return false;
        }
        for (int i = 0; i < from_CitiesNode.size(); i++) {
            From_Cities from_Cities = new From_Cities();
            JsonNode node;

            node = from_CitiesNode.get(i).path("id");
            if (node.isMissingNode()) {
                LOG.log(Level.WARNING, "From_CitiesNode: id node is missing");
                return false;
            }
            from_Cities.setId(node.asText());

            node = from_CitiesNode.get(i).path("name");
            if (node.isMissingNode()) {
                LOG.log(Level.WARNING, "From_CitiesNode: name node is missing");
                return false;
            }
            from_Cities.setName(node.asText());

            service.saveFrom_Cities(from_Cities);
        }
        return true;
    }

}
