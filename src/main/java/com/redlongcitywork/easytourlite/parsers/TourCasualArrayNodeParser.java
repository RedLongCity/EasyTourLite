package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.model.TourCasual;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.service.TypeService;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 19/02/2018
 */
@Service
public class TourCasualArrayNodeParser implements NodeParser<List<TourCasual>> {

    private static final Logger LOG = Logger.getLogger(TourCasualArrayNodeParser.class.getName());

    private final Meal_TypeService mealTypeService;

    private final CurrencyService currencyService;

    private final From_CitiesService cityService;

    private final TypeService typeService;

    public TourCasualArrayNodeParser(
            Meal_TypeService mealTypeService,
            CurrencyService currencyService,
            From_CitiesService cityService,
            TypeService typeService) {
        this.mealTypeService = mealTypeService;
        this.currencyService = currencyService;
        this.cityService = cityService;
        this.typeService = typeService;
    }

    @Override
    public List<TourCasual> parseNode(JsonNode arrayNode) {
        if (arrayNode.isMissingNode()) {
            LOG.log(Level.WARNING, "TourCasualArrayNode: Node is missing");
            return null;
        }

        List<TourCasual> list = new ArrayList<TourCasual>();
        TourCasualNodeParser parser = new TourCasualNodeParser(
                mealTypeService.findAll(),
                currencyService.findAll(),
                cityService.findAll(),
                typeService.findAll());
        for (int i = 0; i < arrayNode.size(); i++) {
            list.add(parser.parseNode(arrayNode.get(i)));
        }

        return list;
    }

}
