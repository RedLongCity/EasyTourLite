package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.HotelFilter;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Region;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 19/02/2018
 */
@Service
public class HotelFilterNodeParser implements NodeParser<HotelFilter> {

    private static final Logger LOG = Logger.getLogger(HotelFilterNodeParser.class.getName());

    private final List<Hotel_Rating> ratingList;

    private final List<Region> regionList;

    public HotelFilterNodeParser(List<Hotel_Rating> ratingList, List<Region> regionList) {
        this.ratingList = ratingList;
        this.regionList = regionList;
    }

    @Override
    public HotelFilter parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "HotelFilterNode: countriesNode is missing");
            return null;
        }

        HotelFilter filter = new HotelFilter();

        if (jsonNode.has("id")) {
            filter.setId(jsonNode.path("id").asText());
        }

        if (jsonNode.has("name")) {
            filter.setName(jsonNode.path("name").asText());
        }

        if (jsonNode.has("hotel_rating_name")) {
            filter.setRating(findRating(jsonNode.path("hotel_rating_id").asText()));
        }

        if (jsonNode.has("region_id")) {
            filter.setRegion(findRegion(jsonNode.path("region_id").asText()));
        }

        return filter;
    }

    private Hotel_Rating findRating(String name) {
        for (Hotel_Rating rating : ratingList) {
            if (rating.getName().equals(name)) {
                return rating;
            }
        }
        return null;
    }

    private Region findRegion(String id) {
        for (Region region : regionList) {
            if (region.getId().equals(id)) {
                return region;
            }
        }
        return null;
    }
}
