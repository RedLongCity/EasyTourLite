package com.redlongcitywork.easytourlite.utils;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.parsers.CountryNodeParser;
import com.redlongcitywork.easytourlite.parsers.CurrencyNodeParser;
import com.redlongcitywork.easytourlite.parsers.From_CitiesNodeParser;
import com.redlongcitywork.easytourlite.parsers.Hotel_RatingNodeParser;
import com.redlongcitywork.easytourlite.parsers.Meal_TypeNodeParser;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.singletons.AppConstants;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
/**
 *
 * @author redlongcity
 */

@Service
public class ItToursHotToursFiltersParser {

    private static final Logger LOG = Logger.getLogger(ItToursHotToursFiltersParser.class.getName());
    
    @Autowired
    CountryService countryService;
    
    @Autowired
    From_CitiesService from_CitiesService;
    
    @Autowired
    Hotel_RatingService hotel_RatingService;
    
    @Autowired
    Meal_TypeService meal_TypeService;
    
    @Autowired
    CurrencyService currencyService;
    
    @Autowired
    AppConstants projectConstantsSingletone;
    
    @Autowired
    CountryNodeParser countryNodeParser;
    
    @Autowired
    From_CitiesNodeParser from_CitiesNodeParser;
    
    @Autowired
    Hotel_RatingNodeParser hotel_RatingNodeParser;
    
    @Autowired
    Meal_TypeNodeParser meal_TypeNodeParser;
    
    @Autowired
    CurrencyNodeParser currencyNodeParser;
    
    public void extractHotToursFilters(JsonNode rootNode){

        if(rootNode.isMissingNode()){
            LOG.log(Level.WARNING,"rootNode is Missing");
            return;
        }
        projectConstantsSingletone.setFiltersUpdate(true);//inform application about upadating filters
        
        ArrayNode countriesNode = (ArrayNode) rootNode.path("countries");
        if(countriesNode.isMissingNode()){
            LOG.log(Level.WARNING,"countriesNode is missing");
            return;
        }
        if(!parseCountries(countriesNode)){
            LOG.log(Level.WARNING, "Country Parser return false");
            return;
        }
        
        ArrayNode from_CitiesNode = (ArrayNode) rootNode.path("from_cities");
        if(from_CitiesNode.isMissingNode()){
            LOG.log(Level.WARNING,"From_CitiesNode is missing");
            return;
        }
        if(!parseFrom_Cities(from_CitiesNode)){
            LOG.log(Level.WARNING, "From_Cities Parser return false");
            return; 
        }
        ArrayNode hotel_RatingNode = (ArrayNode) rootNode.path("hotel_ratings");
        if(hotel_RatingNode.isMissingNode()){
            LOG.log(Level.WARNING,"Hotel_RatingNode is missing");
            return; 
        }
        if(!parseHotel_rating(hotel_RatingNode)){
            LOG.log(Level.WARNING, "Hotel_RatingNode Parser return false");
            return; 
        }
        ArrayNode meal_TypeNode = (ArrayNode) rootNode.path("meal_types");
        if(meal_TypeNode.isMissingNode()){
            LOG.log(Level.WARNING,"Meal_TypeNode is missing");
            return;
        }
        if(!parseMeal_Type(meal_TypeNode)){
            LOG.log(Level.WARNING, "Meal_TypeNode Parser return false");
            return;
        }
        ArrayNode currencyNode = (ArrayNode) rootNode.path("currencies");
        if(currencyNode.isMissingNode()){
            LOG.log(Level.WARNING,"CurrencyNode is missing");
            return; 
        }
        if(!parseCurrency(currencyNode)){
            LOG.log(Level.WARNING, "CurrencyNode Parser return false");
            return; 
        }
        
         projectConstantsSingletone.setFiltersUpdate(false);//inform application
         //about finish of upadating filters
    }
    private Boolean parseCountries(ArrayNode countriesNode){
        //countryService.deleteAllCountries();
        return countryNodeParser.parseNode(countriesNode);
    }
    private Boolean parseFrom_Cities(ArrayNode from_CitiesNode){
        //from_CitiesService.deleteAllFrom_Cities();
        return from_CitiesNodeParser.parseNode(from_CitiesNode);
    }
    private Boolean parseHotel_rating(ArrayNode hotel_RatingNode){
        //hotel_RatingService.deleteAllHotel_Rating();
        return hotel_RatingNodeParser.parseNode(hotel_RatingNode);
    }
    private Boolean parseMeal_Type(ArrayNode meal_TypeNode){
        //meal_TypeService.deleteAllMeal_Type();
        return meal_TypeNodeParser.parseNode(meal_TypeNode);
    }
    private Boolean parseCurrency(ArrayNode currencyNode){
        //currencyService.deleteAllCurrency();
        return currencyNodeParser.parseNode(currencyNode);
    }
}
