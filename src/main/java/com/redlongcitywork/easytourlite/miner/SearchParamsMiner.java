package com.redlongcitywork.easytourlite.miner;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
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
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.io.IOException;
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

    private final CurrencyArrayNodeParser currencyParser;

    private final CurrencyService currencyService;

    private final CountryArrayNodeParser countryParser;

    private final CountryService countryService;

    private final RegionArrayNodeParser regionParser;

    private final RegionService regionService;

    private final Hotel_RatingService ratingService;

    private final RatingArrayNodeParser ratingParser;

    public SearchParamsMiner(TypeService typeService, TypeArrayNodeParser typeExtractor, CurrencyArrayNodeParser currencyParser, CurrencyService currencyService, CountryArrayNodeParser countryParser, CountryService countryService, RegionArrayNodeParser regionParser, RegionService regionService, Hotel_RatingService ratingService, RatingArrayNodeParser ratingParser) {
        this.typeService = typeService;
        this.typeParser = typeExtractor;
        this.currencyParser = currencyParser;
        this.currencyService = currencyService;
        this.countryParser = countryParser;
        this.countryService = countryService;
        this.regionParser = regionParser;
        this.regionService = regionService;
        this.ratingService = ratingService;
        this.ratingParser = ratingParser;
    }

    @Override
    public void mine() {
        try {
            JsonNode node = HttpUtils.getJsonNodeFromUrl(api_base_url + api_params_url);

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
                }
            }
        } catch (IOException ex) {
            Logger.getLogger(SearchParamsMiner.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
