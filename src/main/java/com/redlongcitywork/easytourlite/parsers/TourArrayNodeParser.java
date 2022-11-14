/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 22/12/2017
 * class for parsing List of Tour from ArrayNode
 */
@Service
public class TourArrayNodeParser implements NodeParser<List<Tour>> {

    private static final Logger LOG = Logger.getLogger(TourArrayNodeParser.class.getName());

    @Autowired
    private CountryService countryService;

    @Autowired
    private From_CitiesService cityService;

    @Autowired
    private Meal_TypeService typeService;

    @Autowired
    private CurrencyService currencyService;

    @Autowired
    private Hotel_RatingService ratingService;
    
    @Autowired
    private TimeUtils utils;
    
    private long time;

    @Override
    public List<Tour> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "TourNode: arrayNode is missing");
            return null;
        }

        List<Tour> list = new ArrayList<Tour>();
        ArrayNode offersNode = (ArrayNode) arrayNode.path("offers");
        TourNodeParser parser = new TourNodeParser(
                countryService.findAll(),
                cityService.findAll(),
                typeService.findAll(),
                ratingService.findAll(),
                currencyService.findAll());
        time = System.currentTimeMillis();
        for (int i = 0; i < offersNode.size(); i++) {
            list.add(parser.parseNode(offersNode.get(i)));
        }
        LOG.log(Level.INFO, "Time of operation: " + (System.currentTimeMillis() - time));

        return list;
    }

}
