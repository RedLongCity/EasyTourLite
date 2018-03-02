package com.redlongcitywork.easytourlite.extractor;

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
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 02/03/2018
 */
@Service
public class FilterParamsExtractor implements Extractor, ItToursUrls {

    private static final Logger LOG = Logger.getLogger(FilterParamsExtractor.class.getName());

    @Autowired
    private CountryService countryService;

    @Autowired
    private HotelFilterService hotelService;

    @Autowired
    private Meal_TypeService typeService;

    @Autowired
    private From_CitiesService cityService;

    @Autowired
    private HotelFilterArrayNodeParser hotelParser;

    @Autowired
    private MealTypeArrayNodeParser typeParser;

    @Autowired
    private CitiesArrayNodeParser cityParser;

    @Override
    public void extract(HttpUtils.GetCallBack callBack) {
        List<Country> list = countryService.findAll();
        if (list != null) {
            for (Country country : list) {
                try {
                    HttpUtils.getJsonNodeFromUrl(
                            api_base_url
                            + api_params_url
                            + country.getId()
                            + api_country_params,
                            new HttpUtils.GetCallBack<JsonNode>() {
                        @Override
                        public void onDataReceived(JsonNode node) {

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
                            }
                        }

                        @Override
                        public void onDataNotAwailable() {
                            callBack.onDataNotAwailable();
                        }
                    });
                } catch (IOException e) {
                    LOG.log(Level.WARNING, e.getMessage());
                }
            }
        }
    }

}
