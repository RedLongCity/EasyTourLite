package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Image;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.service.TourService;
import java.sql.Date;
import java.time.LocalDate;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author redlongcity 
 * 22/12/2017 
 * class for deserialization Tour object from
 * JsonNode
 */
public class TourNodeParser implements NodeParser<Tour> {

    private List<Country> countryList;

    private List<From_Cities> cityList;

    private List<Meal_Type> typeList;

    private List<Hotel_Rating> ratingList;

    private List<Currency> currencyList;

    @Autowired
    private TourService tourService;

    public TourNodeParser(List<Country> countryList, List<From_Cities> cityList, List<Meal_Type> typeList, List<Hotel_Rating> ratingList, List<Currency> currencyList) {
        this.countryList = countryList;
        this.cityList = cityList;
        this.typeList = typeList;
        this.ratingList = ratingList;
        this.currencyList = currencyList;
    }

    private static final Logger LOG = Logger.getLogger(TourNodeParser.class.getName());

    @Override
    public Tour parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "TourNode: tourNode is missing");
            return null;
        }
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        Tour tour = new Tour();

        if (jsonNode.has("key")) {
            tour.setKey(jsonNode.path("key").asText());
        }

        if (jsonNode.has("type")) {
            tour.setType(jsonNode.path("type").asInt());
        }

        if (jsonNode.has("country_id")) {
            tour.setCountry(findCountry(jsonNode.path("country_id").asText()));
        }

        if (jsonNode.has("region")) {
            tour.setRegion(jsonNode.path("region").asText());
        }

        if (jsonNode.has("hotel_id")) {
            tour.setHotel_id(jsonNode.path("hotel_id").asInt());
        }

        if (jsonNode.has("hotel")) {
            tour.setHotel(jsonNode.path("hotel").asText());
        }

        if (jsonNode.has("hotel_rating")) {
            tour.setHotel_Rating(findRating(jsonNode.path("hotel_rating").asText()));
        }

        if (jsonNode.has("meal_type")) {
            tour.setMeal_Type(findType(jsonNode.path("meal_type").asText()));
        }

        if (jsonNode.has("room_type")) {
            tour.setRoom_Type(jsonNode.path("room_type").asText());
        }

        if (jsonNode.has("adult_amount")) {
            tour.setAdult_Amount(jsonNode.path("adult_amount").asInt());
        }

        if (jsonNode.has("child_amount")) {
            tour.setChild_Amount(jsonNode.path("child_amount").asInt());
        }

        if (jsonNode.has("accomodation")) {
            tour.setAccomodation(jsonNode.path("accomodation").asText());
        }

        if (jsonNode.has("duration")) {
            tour.setDuration(jsonNode.path("duration").asInt());
        }

        if (jsonNode.has("date_from")) {
            Date date = Date.valueOf(
                    LocalDate.parse(jsonNode.path("date_from").asText()));
            tour.setDate_From(date);
        }

        if (jsonNode.has("date_from_unix")) {
            tour.setDate_From_Unix(
                    jsonNode.path("date_from_unix").asInt());
        }

        if (jsonNode.has("currency_id")) {
            tour.setCurrency_id(jsonNode.path("currency_id").asInt());
        }

        if (jsonNode.has("currency_symbol")) {
            tour.setCurrency_Symbol(
                    jsonNode.path("currency_symbol").asText());
        }

        if (jsonNode.has("prices")) {
            Price priceUsd = new Price();
            priceUsd.setCurrency(findCurrency("1"));
            priceUsd.setCost(jsonNode.path("prices").path("1").asInt());
            priceUsd.setTour(tour);

            Price priceUah = new Price();
            priceUah.setCurrency(findCurrency("2"));
            priceUah.setCost(jsonNode.path("prices").path("2").asInt());
            priceUah.setTour(tour);

            Price priceEur = new Price();
            priceEur.setCurrency(findCurrency("10"));
            priceEur.setCost(jsonNode.path("prices").path("10").asInt());
            priceEur.setTour(tour);

            tour.getPrices().add(priceUsd);
            tour.getPrices().add(priceUah);
            tour.getPrices().add(priceEur);
        }

        if (jsonNode.has("price_old")) {
            tour.setPrice_Old(jsonNode.path("price_old").asInt());
        }

        if (jsonNode.has("price_change_percent")) {
            tour.setPrice_Change_Percent(
                    jsonNode.path("price_change_percent").floatValue());
        }

        if (jsonNode.has("from_city_id")) {
            tour.setFrom_Cities(findCity(
                    jsonNode.path("from_city_id").asText()));
        }

        if (jsonNode.has("from_city_gen")) {
            tour.setFrom_City_Gen(jsonNode.path("from_city_gen").asText());
        }

        if (jsonNode.has("transport_type")) {
            tour.setTransport_Type(jsonNode.path("transport_type").asText());
        }

        if (jsonNode.has("hotel_images")) {
            ArrayNode hotel_ImagesNode
                    = (ArrayNode) jsonNode.path("hotel_images");

            for (int j = 0; j < hotel_ImagesNode.size(); j++) {
                Hotel_Image hotel_Image = new Hotel_Image();
                hotel_Image.setFull(hotel_ImagesNode.get(j).path("full").asText());
                hotel_Image.setThumb(hotel_ImagesNode.get(j).path("thumb").asText());
                hotel_Image.setTour(tour);
                tour.getHotel_ImageSet().add(hotel_Image);
            }
        }

        return tour;
    }

    private Country findCountry(String id) {
        for (Country country : countryList) {
            if (country.getId().equals(id)) {
                return country;
            }
        }
        return null;
    }

    private From_Cities findCity(String id) {
        for (From_Cities city : cityList) {
            if (city.getId().equals(id)) {
                return city;
            }
        }
        return null;
    }

    private Meal_Type findType(String name) {
        for (Meal_Type type : typeList) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        return null;
    }

    private Hotel_Rating findRating(String name) {
        for (Hotel_Rating rating : ratingList) {
            if (rating.getName().equals(name)) {
                return rating;
            }
        }
        return null;
    }

    private Currency findCurrency(String id) {
        for (Currency currency : currencyList) {
            if (currency.getId().equals(id)) {
                return currency;
            }
        }
        return null;
    }
}
