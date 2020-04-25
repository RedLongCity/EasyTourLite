package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.model.Hotel_Image;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.storage.CityStorage;
import com.redlongcitywork.easytourlite.storage.CountryStorage;
import com.redlongcitywork.easytourlite.storage.CurrencyStorage;
import com.redlongcitywork.easytourlite.storage.HotelRatingStorage;
import com.redlongcitywork.easytourlite.storage.MealTypeStorage;
import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author redlongcity 22/12/2017 class for deserialization Tour object from
 * JsonNode
 */
@Service
public class TourNodeParser implements NodeParser<Tour> {

    private static final Logger LOG = Logger.getLogger(TourNodeParser.class.getName());

    private final CountryStorage countryStorage;

    private final CityStorage cityStorage;

    private final MealTypeStorage mealTypeStorage;

    private final HotelRatingStorage ratingStorage;

    private final CurrencyStorage currencyStorage;

    public TourNodeParser(CountryStorage countryStorage, CityStorage cityStorage, MealTypeStorage mealTypeStorage, HotelRatingStorage ratingStorage, CurrencyStorage currencyStorage) {
        this.countryStorage = countryStorage;
        this.cityStorage = cityStorage;
        this.mealTypeStorage = mealTypeStorage;
        this.ratingStorage = ratingStorage;
        this.currencyStorage = currencyStorage;
    }

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
            tour.setCountry(countryStorage
                    .findById(jsonNode.path("country_id")
                            .asText()));
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
            tour.setHotel_Rating(ratingStorage
                    .findByName(jsonNode.path("hotel_rating")
                            .asText()));
        }

        if (jsonNode.has("meal_type")) {
            tour.setMeal_Type(mealTypeStorage
                    .findByName(jsonNode.path("meal_type")
                            .asText()));
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
            List<String> currencyArray = Arrays.asList("1", "2", "10");
            currencyArray.forEach((p) -> {
                tour.getPrices()
                        .add(new Price(
                                currencyStorage.findById(p),
                                jsonNode.path("prices").path("1").asInt()
                        ));
            });
        }

        if (jsonNode.has("price_old")) {
            tour.setPrice_Old(jsonNode.path("price_old").asInt());
        }

        if (jsonNode.has("price_change_percent")) {
            tour.setPrice_Change_Percent(
                    jsonNode.path("price_change_percent").floatValue());
        }

        if (jsonNode.has("from_city_id")) {
            tour.setFrom_Cities(cityStorage.findById(
                    jsonNode.path("from_city_id")
                            .asText()));
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
                tour.getHotel_ImageSet().add(hotel_Image);
            }
        }

        return tour;
    }
}
