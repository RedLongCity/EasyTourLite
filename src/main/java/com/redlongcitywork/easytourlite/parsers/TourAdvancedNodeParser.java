package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.storage.CityStorage;
import com.redlongcitywork.easytourlite.storage.CountryStorage;
import com.redlongcitywork.easytourlite.storage.CurrencyStorage;
import com.redlongcitywork.easytourlite.storage.HotelRatingStorage;
import com.redlongcitywork.easytourlite.storage.MealTypeStorage;
import com.redlongcitywork.easytourlite.storage.RegionStorage;
import com.redlongcitywork.easytourlite.storage.TypeStorage;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 04/03/2018
 */
@Service
public class TourAdvancedNodeParser implements NodeParser<TourAdvanced> {

    private static final Logger LOG = Logger.getLogger(TourAdvancedNodeParser.class.getName());

    private final HotelImageArrayNodeParser imageParser;

    private final FacilityArrayNodeParser facilityParser;

    private final CountryStorage countryStorage;

    private final CityStorage cityStorage;

    private final TypeStorage typeStorage;

    private final MealTypeStorage mealTypeStorage;

    private final RegionStorage regionStorage;

    private final CurrencyStorage currencyStorage;

    private final HotelRatingStorage ratingStorage;

    public TourAdvancedNodeParser(HotelImageArrayNodeParser imageParser, FacilityArrayNodeParser facilityParser, CountryStorage countryStorage, CityStorage cityStorage, TypeStorage typeStorage, MealTypeStorage mealTypeStorage, RegionStorage regionStorage, CurrencyStorage currencyStorage, HotelRatingStorage ratingStorage) {
        this.imageParser = imageParser;
        this.facilityParser = facilityParser;
        this.countryStorage = countryStorage;
        this.cityStorage = cityStorage;
        this.typeStorage = typeStorage;
        this.mealTypeStorage = mealTypeStorage;
        this.regionStorage = regionStorage;
        this.currencyStorage = currencyStorage;
        this.ratingStorage = ratingStorage;
    }

    @Override
    public TourAdvanced parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "TourAdvancedNode: node is missing");
            return null;
        }

        TourAdvanced tour = new TourAdvanced();

        if (jsonNode.has("key")) {
            tour.setKey(jsonNode.path("key").asText());
        }

        if (jsonNode.has("country")) {
            tour.setCountry(findCountry(jsonNode.path("country").asText()));
        }

        if (jsonNode.has("region_id")) {
            tour.setRegion(findRegion(jsonNode.path("region_id").asText()));
        }

        if (jsonNode.has("hotel_id")) {
            tour.setHotelId(jsonNode.path("hotel_id").asInt());
        }

        if (jsonNode.has("hotel")) {
            tour.setHotelName(jsonNode.path("hotel").asText());
        }

        if (jsonNode.has("hotel_rating")) {
            tour.setRating(findRating(jsonNode.path("hotel_rating").asText()));
        }

        if (jsonNode.has("meal_type")) {
            tour.setMealType(findMealType(jsonNode.path("meal_type").asText()));
        }

        if (jsonNode.has("adult_amount")) {
            tour.setAdultAmount(jsonNode.path("adult_amount").asInt());
        }

        if (jsonNode.has("child_amount")) {
            tour.setChildAmount(jsonNode.path("child_amount").asInt());
        }

        if (jsonNode.has("accomodation")) {
            tour.setAccomodation(jsonNode.path("accomodation").asText());
        }

        if (jsonNode.has("room_type")) {
            tour.setRoomType(jsonNode.path("room_type").asText());
        }

        if (jsonNode.has("duration")) {
            tour.setDuration(jsonNode.path("duration").asInt());
        }

        if (jsonNode.has("date_from")) {
            Date date = Date.valueOf(
                    LocalDate.parse(jsonNode.path("date_from").asText()));
            tour.setDateFrom(date);
        }

        if (jsonNode.has("is_combined")) {
            tour.setCombined(jsonNode.path("is_combined").asInt() == 1);
        }

        if (jsonNode.has("currency_id")) {
            tour.setCurrency(findCurrency(jsonNode.path("currency_id").asText()));
        }

        if (jsonNode.has("prices")) {
            Price priceUsd = new Price();
            priceUsd.setCurrency(findCurrency("1"));
            priceUsd.setCost(jsonNode.path("prices").path("1").asInt());

            Price priceUah = new Price();
            priceUah.setCurrency(findCurrency("2"));
            priceUah.setCost(jsonNode.path("prices").path("2").asInt());

            Price priceEur = new Price();
            priceEur.setCurrency(findCurrency("10"));
            priceEur.setCost(jsonNode.path("prices").path("10").asInt());

            tour.getPrices().add(priceUsd);
            tour.getPrices().add(priceUah);
            tour.getPrices().add(priceEur);
        }

        if (jsonNode.has("from_city")) {
            tour.setCity(findCity(jsonNode.path("from_city").asText()));
        }

        if (jsonNode.has("transport_type")) {
            tour.setTransportType(jsonNode.path("transport_type").asText());
        }

        if (jsonNode.has("hotel_images")) {
            tour.getImages().addAll(imageParser.parseNode(jsonNode.path("hotel_images")));
        }

        if (jsonNode.has("hotel_review_rate")) {
            tour.setRate(jsonNode.path("hotel_review_rate").asText());
        }

        if (jsonNode.has("hotel_review_count")) {
            tour.setReviewCount(jsonNode.path("hotel_review_count").asText());
        }

        if (jsonNode.has("hotel_facilities")) {
            tour.getFacilities().addAll(facilityParser.parseNode(jsonNode.path("hotel_facilities")));
        }

        return tour;
    }

    private Country findCountry(String name) {
        for (Country country : countryStorage.getContent()) {
            if (country.getName().equals(name)) {
                return country;
            }
        }
        return null;
    }

    private Type findType(String name) {
        for (Type type : typeStorage.getContent()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        return null;
    }

    private Meal_Type findMealType(String name) {
        for (Meal_Type type : mealTypeStorage.getContent()) {
            if (type.getName().equals(name)) {
                return type;
            }
        }
        return null;
    }

    private Currency findCurrency(String id) {
        for (Currency currency : currencyStorage.getContent()) {
            if (currency.getId().equals(id)) {
                return currency;
            }
        }
        return null;
    }

    private From_Cities findCity(String name) {
        for (From_Cities city : cityStorage.getContent()) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }

    private Region findRegion(String id) {
        for (Region region : regionStorage.getContent()) {
            if (region.getId().equals(id)) {
                return region;
            }
        }
        return null;
    }

    private Hotel_Rating findRating(String name) {
        for (Hotel_Rating rating : ratingStorage.getContent()) {
            if (rating.getName().equals(name)) {
                return rating;
            }
        }
        return null;
    }

}
