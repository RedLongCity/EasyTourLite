package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Country;
import com.redlongcitywork.easytourlite.model.Hotel;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Region;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author redlongcity 23/02/2018
 */
public class HotelNodeParser implements NodeParser<Hotel> {

    private static final Logger LOG = Logger.getLogger(HotelNodeParser.class.getName());

    private final FacilityArrayNodeParser facilityParser;

    private final HotelImageArrayNodeParser imageParser;

    private final TourCasualArrayNodeParser tourParser;

    private final List<Region> regionList;

    private final List<Hotel_Rating> ratingList;

    private final List<Country> countryList;

    public HotelNodeParser(FacilityArrayNodeParser facilityParser, HotelImageArrayNodeParser imageParser, TourCasualArrayNodeParser tourParser, List<Region> regionList, List<Hotel_Rating> ratingList, List<Country> countryList) {
        this.facilityParser = facilityParser;
        this.imageParser = imageParser;
        this.tourParser = tourParser;
        this.regionList = regionList;
        this.ratingList = ratingList;
        this.countryList = countryList;
    }

    @Override
    public Hotel parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "TourNode: tourNode is missing");
            return null;
        }

        Hotel hotel = new Hotel();

        if (jsonNode.has("country")) {
            hotel.setCountry(findCountry(jsonNode.path("country").asText()));
        }

        if (jsonNode.has("region_id")) {
            hotel.setRegion(findRegion(jsonNode.path("region_id").asText()));
        }

        if (jsonNode.has("hotel_rating")) {
            hotel.setRating(findRating(jsonNode.path("hotel_rating").asText()));
        }

        if (jsonNode.has("adult_amount")) {
            hotel.setAdultAmount(jsonNode.path("adult_amount").asInt());
        }

        if (jsonNode.has("child_amount")) {
            hotel.setChildAmount(jsonNode.path("child_amount").asInt());
        }

        if (jsonNode.has("accomodation")) {
            hotel.setAccomodation(jsonNode.path("accomodation").asText());
        }

        if (jsonNode.has("hotel")) {
            hotel.setHotelName(jsonNode.path("hotel").asText());
        }

        if (jsonNode.has("hotel_review_rate")) {
            hotel.setHotelReviewRate(jsonNode.path("hotel_review_rate").asText());
        }

        if (jsonNode.has("hotel_review_count")) {
            hotel.setHotelReviewCount(jsonNode.path("hotel_review_count").asText());
        }

        if (jsonNode.has("hotel_facilities")) {
            hotel.getFacilities().addAll(
                    facilityParser.parseNode(jsonNode.path("hotel_facilities")));
        }

        if (jsonNode.has("lat")) {
            hotel.setLat(jsonNode.path("lat").asText());
        }

        if (jsonNode.has("lng")) {
            hotel.setLat(jsonNode.path("lng").asText());
        }

        if (jsonNode.has("wifi_free")) {
            hotel.setWifiFree(jsonNode.path("wifi_free").asBoolean());
        }

        if (jsonNode.has("images")) {
            hotel.getImages().addAll(
                    imageParser.parseNode(jsonNode.path("images")));
        }

        if (jsonNode.has("offers")) {
            hotel.getTours().addAll(
                    tourParser.parseNode(jsonNode.path("offers")));
        }

        return hotel;
    }

    private Region findRegion(String id) {
        for (Region region : regionList) {
            if (region.getId().equals(id)) {
                return region;
            }
        }
        return null;
    }

    private Country findCountry(String name) {
        for (Country country : countryList) {
            if (country.getName().equals(name)) {
                return country;
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
}
