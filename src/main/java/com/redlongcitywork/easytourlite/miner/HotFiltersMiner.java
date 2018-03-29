package com.redlongcitywork.easytourlite.miner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.http.HttpService;
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
import com.redlongcitywork.easytourlite.storage.CityStorage;
import com.redlongcitywork.easytourlite.storage.CountryStorage;
import com.redlongcitywork.easytourlite.storage.CurrencyStorage;
import com.redlongcitywork.easytourlite.storage.HotelRatingStorage;
import com.redlongcitywork.easytourlite.storage.MealTypeStorage;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import static com.redlongcitywork.easytourlite.utils.ItToursUrls.api_base_url;
import static com.redlongcitywork.easytourlite.utils.ItToursUrls.api_showcases;
import static com.redlongcitywork.easytourlite.utils.ItToursUrls.api_showcases_filters;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 15/12/2017 class for extracting Hot Tours Filters from
 * outside database
 */
@Service
public class HotFiltersMiner implements Miner, ItToursUrls {

    private static final Logger LOG = Logger.getLogger(HotFiltersMiner.class.getName());

    private final CountryService countryService;

    private final From_CitiesService cityService;

    private final Hotel_RatingService ratingService;

    private final Meal_TypeService mealTypeService;

    private final CurrencyService currencyService;

    private final CountryArrayNodeParser countryParser;

    private final CitiesArrayNodeParser citiesParser;

    private final RatingArrayNodeParser ratingParser;

    private final MealTypeArrayNodeParser mealTypeParser;

    private final CurrencyArrayNodeParser currencyParser;

    private final CountryStorage countryStorage;

    private final CityStorage cityStorage;

    private final HotelRatingStorage ratingStorage;

    private final MealTypeStorage mealTypeStorage;

    private final CurrencyStorage currencyStorage;

    private final HttpService httpService;

    public HotFiltersMiner(CountryService countryService, From_CitiesService cityService, Hotel_RatingService ratingService, Meal_TypeService mealTypeService, CurrencyService currencyService, CountryArrayNodeParser countryParser, CitiesArrayNodeParser citiesParser, RatingArrayNodeParser ratingParser, MealTypeArrayNodeParser mealTypeParser, CurrencyArrayNodeParser currencyParser, CountryStorage countryStorage, CityStorage cityStorage, HotelRatingStorage ratingStorage, MealTypeStorage mealTypeStorage, CurrencyStorage currencyStorage, HttpService httpService) {
        this.countryService = countryService;
        this.cityService = cityService;
        this.ratingService = ratingService;
        this.mealTypeService = mealTypeService;
        this.currencyService = currencyService;
        this.countryParser = countryParser;
        this.citiesParser = citiesParser;
        this.ratingParser = ratingParser;
        this.mealTypeParser = mealTypeParser;
        this.currencyParser = currencyParser;
        this.countryStorage = countryStorage;
        this.cityStorage = cityStorage;
        this.ratingStorage = ratingStorage;
        this.mealTypeStorage = mealTypeStorage;
        this.currencyStorage = currencyStorage;
        this.httpService = httpService;
    }

    @Override
    public void mine() {
        JsonNode node = httpService.getJsonNodeFromUrl(api_base_url
                + api_showcases
                + api_showcases_filters);

        if (node != null) {
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
                countryStorage.updateStorage();
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
                cityStorage.updateStorage();
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
                ratingStorage.updateStorage();
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
                mealTypeStorage.updateStorage();
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
                currencyStorage.updateStorage();
            }
        }
    }

}
