package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.HotelFilter;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.RegionService;
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
    private Hotel_RatingService ratingService;
    
    @Autowired
    private RegionService regionService;

    @Override
    public List<HotelFilter> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "HotelFilterNode: arrayNode is missing");
            return null;
        }

        List<HotelFilter> list = new ArrayList<HotelFilter>();
        HotelFilterNodeParser parser = new HotelFilterNodeParser(
                ratingService.findAll(),
                regionService.findAll()
        );
        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }
        return list;
    }
}
