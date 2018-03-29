package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.TourCasual;
import com.redlongcitywork.easytourlite.storage.CityStorage;
import com.redlongcitywork.easytourlite.storage.CurrencyStorage;
import com.redlongcitywork.easytourlite.storage.MealTypeStorage;
import java.sql.Date;
import java.time.LocalDate;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 19/02/2018
 */
@Service
public class TourCasualNodeParser implements NodeParser<TourCasual> {

    private static final Logger LOG = Logger.getLogger(TourCasualNodeParser.class.getName());

    private final MealTypeStorage mealTypeStorage;
    
    private final CurrencyStorage currencyStorage;
    
    private final CityStorage cityStorage;

    public TourCasualNodeParser(MealTypeStorage mealTypeStorage, CurrencyStorage currencyStorage, CityStorage cityStorage) {
        this.mealTypeStorage = mealTypeStorage;
        this.currencyStorage = currencyStorage;
        this.cityStorage = cityStorage;
    }

    @Override
    public TourCasual parseNode(JsonNode jsonNode) {
        if (jsonNode.isMissingNode()) {
            LOG.log(Level.WARNING, "TourCasualNode: Node is missing");
            return null;
        }

        TourCasual tour = new TourCasual();

        if (jsonNode.has("key")) {
            tour.setKey(jsonNode.path("key").asText());
        }

        if (jsonNode.has("meal_type")) {
            tour.setMealType(findMealType(jsonNode.path("meal_type").asText()));
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
            tour.setCombined(jsonNode.path("is_combined").asBoolean());
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

        return tour;
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
}
