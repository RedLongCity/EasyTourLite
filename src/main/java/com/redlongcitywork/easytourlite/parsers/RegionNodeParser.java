package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Region;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 19/02/2018
 */
@Service
public class RegionNodeParser implements NodeParser<Region> {

    private static final Logger LOG = Logger.getLogger(RegionNodeParser.class.getName());

    @Override
    public Region parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "RegionNode: countriesNode is missing");
            return null;
        }

        Region region = new Region();

        if (jsonNode.has("id")) {
            region.setId(jsonNode.path("id").asText());
        }

        if (jsonNode.has("name")) {
            region.setName(jsonNode.path("name").asText());
        }

        return region;
    }
}
