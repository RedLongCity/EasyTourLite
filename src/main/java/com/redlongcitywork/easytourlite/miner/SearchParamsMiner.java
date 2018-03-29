package com.redlongcitywork.easytourlite.miner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.http.HttpService;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.parsers.CountryArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.CurrencyArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.RatingArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.RegionArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.TypeArrayNodeParser;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.RegionService;
import com.redlongcitywork.easytourlite.service.TypeService;
import com.redlongcitywork.easytourlite.storage.CountryStorage;
import com.redlongcitywork.easytourlite.storage.CurrencyStorage;
import com.redlongcitywork.easytourlite.storage.HotelRatingStorage;
import com.redlongcitywork.easytourlite.storage.RegionStorage;
import com.redlongcitywork.easytourlite.storage.TypeStorage;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 28/20/2018
 */
@Service
public class SearchParamsMiner implements Miner, ItToursUrls {

    private static final Logger LOG = Logger.getLogger(SearchParamsMiner.class.getName());

    private final TypeService typeService;

    private final TypeArrayNodeParser typeParser;

    private final TypeStorage typeStorage;

    private final CurrencyArrayNodeParser currencyParser;

    private final CurrencyService currencyService;

    private final CurrencyStorage currencyStorage;

    private final CountryArrayNodeParser countryParser;

    private final CountryService countryService;

    private final CountryStorage countryStorage;

    private final RegionArrayNodeParser regionParser;

    private final RegionService regionService;

    private final RegionStorage regionStorage;

    private final Hotel_RatingService ratingService;

    private final RatingArrayNodeParser ratingParser;

    private final HotelRatingStorage ratingStorage;

    private final HttpService httpService;

    public SearchParamsMiner(TypeService typeService, TypeArrayNodeParser typeParser, TypeStorage typeStorage, CurrencyArrayNodeParser currencyParser, CurrencyService currencyService, CurrencyStorage currencyStorage, CountryArrayNodeParser countryParser, CountryService countryService, CountryStorage countryStorage, RegionArrayNodeParser regionParser, RegionService regionService, RegionStorage regionStorage, Hotel_RatingService ratingService, RatingArrayNodeParser ratingParser, HotelRatingStorage ratingStorage, HttpService httpService) {
        this.typeService = typeService;
        this.typeParser = typeParser;
        this.typeStorage = typeStorage;
        this.currencyParser = currencyParser;
        this.currencyService = currencyService;
        this.currencyStorage = currencyStorage;
        this.countryParser = countryParser;
        this.countryService = countryService;
        this.countryStorage = countryStorage;
        this.regionParser = regionParser;
        this.regionService = regionService;
        this.regionStorage = regionStorage;
        this.ratingService = ratingService;
        this.ratingParser = ratingParser;
        this.ratingStorage = ratingStorage;
        this.httpService = httpService;
    }

    @Override
    public void mine() {
        JsonNode node = httpService.getJsonNodeFromUrl(api_base_url + api_params_url);

        if (node != null) {
            ArrayNode typeNode = (ArrayNode) node.path("types");
            if (typeNode.isMissingNode()) {
                LOG.log(Level.WARNING, "typeNode is missing");
                return;
            }
            List<Type> typeList = typeParser.parseNode(typeNode);
            if (typeList != null) {
                for (Type type : typeList) {
                    typeService.saveOrUpdateType(type);
                }
                typeStorage.updateStorage();
            }

            ArrayNode currencyNode = (ArrayNode) node.path("currencies");
            if (currencyNode.isMissingNode()) {
                LOG.log(Level.WARNING, "currencyNode is missing");
                return;
            }
            List<Currency> currencyList = currencyParser.parseNode(currencyNode);
            if (currencyList != null) {
                for (Currency currency : currencyList) {
                    currencyService.saveOrUpdateCurrency(currency);
                }
                currencyStorage.updateStorage();
            }

            ArrayNode countryNode = (ArrayNode) node.path("countries");
            if (countryNode.isMissingNode()) {
                LOG.log(Level.WARNING, "countryNode is missing");
                return;
            }
            List<Country> countryList = countryParser.parseNode(countryNode);
            if (countryList != null) {
                for (Country country : countryList) {
                    countryService.saveOrUpdateCountry(country);
                }
                countryStorage.updateStorage();
            }

            ArrayNode regionNode = (ArrayNode) node.path("regions");
            if (regionNode.isMissingNode()) {
                LOG.log(Level.WARNING, "regionNode is missing");
                return;
            }
            List<Region> regionList = regionParser.parseNode(regionNode);
            if (regionList != null) {
                for (Region region : regionList) {
                    regionService.saveOrUpdateRegion(region);
                }
                regionStorage.updateStorage();
            }

            ArrayNode ratingNode = (ArrayNode) node.path("hotel_ratings");
            if (ratingNode.isMissingNode()) {
                LOG.log(Level.WARNING, "ratingNode is missing");
                return;
            }
            List<Hotel_Rating> ratingList = ratingParser.parseNode(ratingNode);
            if (ratingList != null) {
                for (Hotel_Rating rating : ratingList) {
                    ratingService.saveOrUpdateHotel_Rating(rating);
                }
                ratingStorage.updateStorage();
            }
        }
    }

}
