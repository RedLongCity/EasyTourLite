package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Hotel;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.RegionService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 23/02/2018
 */
@Service
public class HotelArrayNodeParser implements NodeParser<List<Hotel>> {

    private static final Logger LOG = Logger.getLogger(HotelArrayNodeParser.class.getName());

    private final RegionService regionService;

    private final CountryService countryService;

    private final Hotel_RatingService ratingService;

    private final FacilityArrayNodeParser facilityParser;

    private final HotelImageArrayNodeParser imageParser;

    private final TourCasualArrayNodeParser tourParser;

    public HotelArrayNodeParser(RegionService regionService,
            CountryService countryService,
            Hotel_RatingService ratingService,
            FacilityArrayNodeParser facilityParser,
            HotelImageArrayNodeParser imageParser,
            TourCasualArrayNodeParser tourParser) {
        this.regionService = regionService;
        this.countryService = countryService;
        this.ratingService = ratingService;
        this.facilityParser = facilityParser;
        this.imageParser = imageParser;
        this.tourParser = tourParser;
    }

    @Override
    public List<Hotel> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "HotelNode: arrayNode is missing");
            return null;
        }

        List<Hotel> list = new ArrayList<Hotel>();
        HotelNodeParser parser = new HotelNodeParser(
                facilityParser,
                imageParser,
                tourParser,
                regionService.findAll(),
                ratingService.findAll(),
                countryService.findAll()
        );
        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }
        return list;
    }

}
