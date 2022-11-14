package com.redlongcitywork.easytourlite.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.parsers.CitiesArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.CountryArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.CurrencyArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.MealTypeArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.RatingArrayNodeParser;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import static com.redlongcitywork.easytourlite.utils.ItToursUrls.api_base_url;
import static com.redlongcitywork.easytourlite.utils.ItToursUrls.api_showcases;
import static com.redlongcitywork.easytourlite.utils.ItToursUrls.api_showcases_filters;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 15/12/2017
 * class for extracting Hot Tours Filters from outside database
 */
@Service
public class HotFiltersExtractor implements Extractor, ItToursUrls {

    private static final Logger LOG = Logger.getLogger(HotFiltersExtractor.class.getName());

    @Autowired
    CountryService countryService;

    @Autowired
    From_CitiesService cityService;

    @Autowired
    Hotel_RatingService ratingService;

    @Autowired
    Meal_TypeService mealTypeService;

    @Autowired
    CurrencyService currencyService;

    @Autowired
    CountryArrayNodeParser countryParser;

    @Autowired
    CitiesArrayNodeParser citiesParser;

    @Autowired
    RatingArrayNodeParser ratingParser;

    @Autowired
    MealTypeArrayNodeParser mealTypeParser;

    @Autowired
    CurrencyArrayNodeParser currencyParser;

    @Override
    public void extract(HttpUtils.GetCallBack callBack) {
        try {
            HttpUtils.getJsonNodeFromUrl(api_base_url
                    + api_showcases
                    + api_showcases_filters,
                    new HttpUtils.GetCallBack() {
                @Override
                public void onDataReceived(JsonNode node) {
                    ArrayNode countriesNode = (ArrayNode) node.path("countries");
                    if (countriesNode.isMissingNode()) {
                        LOG.log(Level.WARNING, "countriesNode is missing");
                        return;
                    }
                    List<Country> countryList = countryParser.parseNode(countriesNode);
                    if (countryList != null) {
                        for (Country country : countryList) {
                            countryService.saveOrUpdateCountry(country);
                        }
                    }

                    ArrayNode cityNode = (ArrayNode) node.path("from_cities");
                    if (cityNode.isMissingNode()) {
                        LOG.log(Level.WARNING, "From_CitiesNode is missing");
                        return;
                    }
                    List<From_Cities> cityList = citiesParser.parseNode(cityNode);
                    if (cityList != null) {
                        for (From_Cities city : cityList) {
                            cityService.saveOrUpdateFrom_Cities(city);
                        }
                    }

                    ArrayNode ratingNode = (ArrayNode) node.path("hotel_ratings");
                    if (ratingNode.isMissingNode()) {
                        LOG.log(Level.WARNING, "Hotel_RatingNode is missing");
                        return;
                    }
                    List<Hotel_Rating> ratingList = ratingParser.parseNode(ratingNode);
                    if (ratingList != null) {
                        for (Hotel_Rating rating : ratingList) {
                            ratingService.saveOrUpdateHotel_Rating(rating);
                        }
                    }

                    ArrayNode mealTypeNode = (ArrayNode) node.path("meal_types");
                    if (mealTypeNode.isMissingNode()) {
                        LOG.log(Level.WARNING, "Meal_TypeNode is missing");
                        return;
                    }
                    List<Meal_Type> mealTypeList = mealTypeParser.parseNode(mealTypeNode);
                    if (mealTypeList != null) {
                        for (Meal_Type type : mealTypeList) {
                            mealTypeService.saveOrUpdateMeal_Type(type);
                        }
                    }

                    ArrayNode currencyNode = (ArrayNode) node.path("currencies");
                    if (currencyNode.isMissingNode()) {
                        LOG.log(Level.WARNING, "CurrencyNode is missing");
                        return;
                    }
                    List<Currency> currencyList = currencyParser.parseNode(currencyNode);
                    if (currencyList != null) {
                        for (Currency currency : currencyList) {
                            currencyService.saveOrUpdateCurrency(currency);
                        }
                    }
                    callBack.onDataReceived(null);
                }

                @Override
                public void onDataNotAwailable() {
                    callBack.onDataNotAwailable();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(HotFiltersExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
