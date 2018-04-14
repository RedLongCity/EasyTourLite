package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.HotelFilter;
import com.redlongcitywork.easytourlite.storage.HotelRatingStorage;
import com.redlongcitywork.easytourlite.storage.RegionStorage;
import com.redlongcitywork.easytourlite.storage.TypeStorage;
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

    private final HotelRatingStorage ratingStorage;

    private final RegionStorage regionStorage;

    private final TypeStorage typeStorage;

    public HotelFilterNodeParser(HotelRatingStorage ratingStorage, RegionStorage regionStorage, TypeStorage typeStorage) {
        this.ratingStorage = ratingStorage;
        this.regionStorage = regionStorage;
        this.typeStorage = typeStorage;
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

        if (jsonNode.has("hotel_rating_id")) {
            filter.setRating(ratingStorage
                    .findByName(jsonNode.path("hotel_rating_id")
                            .asText()));
        }

        if (jsonNode.has("region_id")) {
            filter.setRegion(regionStorage
                    .findById(jsonNode.path("region_id")
                            .asText()));
        }

        if (jsonNode.has("type_id")) {
            String[] array = jsonNode.path("type_id").asText().split(",");
            for (String str : array) {
                filter.getTypeSet().add(typeStorage.findById(str));
            }
        }

        return filter;
    }
}
