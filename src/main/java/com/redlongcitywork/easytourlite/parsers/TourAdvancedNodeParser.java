package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.storage.CityStorage;
import com.redlongcitywork.easytourlite.storage.CountryStorage;
import com.redlongcitywork.easytourlite.storage.CurrencyStorage;
import com.redlongcitywork.easytourlite.storage.HotelRatingStorage;
import com.redlongcitywork.easytourlite.storage.MealTypeStorage;
import com.redlongcitywork.easytourlite.storage.RegionStorage;
import com.redlongcitywork.easytourlite.storage.TypeStorage;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
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
        if (jsonNode != null && jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "TourAdvancedNode: node is missing");
            return null;
        }

        TourAdvanced tour = new TourAdvanced();

        if (jsonNode.has("key")) {
            tour.setKey(jsonNode.path("key").asText());
        }
        
        if(jsonNode.has("type")){
            tour.setType(typeStorage
                    .findById(jsonNode.path("type")
                            .asText()));
        }

        if (jsonNode.has("country")) {
            tour.setCountry(countryStorage
                    .findByName(jsonNode.path("country")
                            .asText()));
        }

        if (jsonNode.has("region_id")) {
            tour.setRegion(regionStorage
                    .findById(jsonNode.path("region_id")
                            .asText()));
        }

        if (jsonNode.has("hotel_id")) {
            tour.setHotelId(jsonNode.path("hotel_id").asInt());
        }

        if (jsonNode.has("hotel")) {
            tour.setHotelName(jsonNode.path("hotel").asText());
        }

        if (jsonNode.has("hotel_rating")) {
            tour.setRating(ratingStorage
                    .findByName(jsonNode.path("hotel_rating")
                            .asText()));
        }

        if (jsonNode.has("meal_type")) {
            tour.setMealType(mealTypeStorage
                    .findByName(jsonNode.path("meal_type")
                            .asText()));
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
            tour.setCurrency(currencyStorage
                    .findById(jsonNode.path("currency_id")
                            .asText()));
        }

        if (jsonNode.has("prices")) {
            List<String> currencyArray = Arrays.asList("1", "2", "10");
            currencyArray.forEach((p) -> {
                tour.getPrices()
                        .add(new Price(
                                currencyStorage.findById(p),
                                jsonNode.path("prices").path("1").asInt()
                        ));
            });
        }

        if (jsonNode.has("from_city")) {
            tour.setCity(cityStorage
                    .findByName(jsonNode.path("from_city")
                            .asText()));
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
}
