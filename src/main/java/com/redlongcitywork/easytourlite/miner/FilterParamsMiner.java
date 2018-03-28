package com.redlongcitywork.easytourlite.miner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.HotelFilter;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.parsers.CitiesArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.HotelFilterArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.MealTypeArrayNodeParser;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.HotelFilterService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.storage.CityStorage;
import com.redlongcitywork.easytourlite.storage.MealTypeStorage;
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 02/03/2018
 */
@Service
public class FilterParamsMiner implements Miner, ItToursUrls {

    private static final Logger LOG = Logger.getLogger(FilterParamsMiner.class.getName());

    private final CountryService countryService;

    private final HotelFilterService hotelService;

    private final Meal_TypeService typeService;

    private final From_CitiesService cityService;

    private final HotelFilterArrayNodeParser hotelParser;

    private final MealTypeArrayNodeParser typeParser;

    private final CitiesArrayNodeParser cityParser;

    private final MealTypeStorage mealTypeStorage;

    private final CityStorage cityStorage;

    public FilterParamsMiner(CountryService countryService, HotelFilterService hotelService, Meal_TypeService typeService, From_CitiesService cityService, HotelFilterArrayNodeParser hotelParser, MealTypeArrayNodeParser typeParser, CitiesArrayNodeParser cityParser, MealTypeStorage mealTypeStorage, CityStorage cityStorage) {
        this.countryService = countryService;
        this.hotelService = hotelService;
        this.typeService = typeService;
        this.cityService = cityService;
        this.hotelParser = hotelParser;
        this.typeParser = typeParser;
        this.cityParser = cityParser;
        this.mealTypeStorage = mealTypeStorage;
        this.cityStorage = cityStorage;
    }

    @Override
    public void mine() {
        List<Country> list = countryService.findAll();
        if (list != null) {
            for (Country country : list) {
                try {
                    JsonNode node = HttpUtils.getJsonNodeFromUrl(
                            api_base_url
                            + api_params_url
                            + country.getId()
                            + api_country_params);

                    if (node != null) {
                        ArrayNode hotelNode = (ArrayNode) node.path("hotels");
                        if (hotelNode.isMissingNode()) {
                            LOG.log(Level.WARNING, "Node is missing");
                            return;
                        }
                        List<HotelFilter> hotelList = hotelParser.parseNode(hotelNode);
                        if (hotelList != null) {
                            for (HotelFilter hotel : hotelList) {
                                hotelService.saveOrUpdateHotel(hotel);
                            }
                        }

                        ArrayNode typeNode = (ArrayNode) node.path("meal_types");
                        if (typeNode.isMissingNode()) {
                            LOG.log(Level.WARNING, "Node is missing");
                            return;
                        }
                        List<Meal_Type> typeList = typeParser.parseNode(typeNode);
                        if (typeList != null) {
                            for (Meal_Type type : typeList) {
                                typeService.saveOrUpdateMeal_Type(type);
                            }
                            mealTypeStorage.updateStorage();
                        }

                        ArrayNode cityNode = (ArrayNode) node.path("from_cities");
                        if (cityNode.isMissingNode()) {
                            LOG.log(Level.WARNING, "Node is missing");
                            return;
                        }
                        List<From_Cities> cityList = cityParser.parseNode(cityNode);
                        if (cityList != null) {
                            for (From_Cities city : cityList) {
                                cityService.saveOrUpdateFrom_Cities(city);
                            }
                            cityStorage.updateStorage();
                        }
                    }
                } catch (IOException e) {
                    LOG.log(Level.WARNING, e.getMessage());
                }
            }
        }
    }

}
