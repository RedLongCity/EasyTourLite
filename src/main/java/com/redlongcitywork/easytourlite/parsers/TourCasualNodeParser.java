package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.TourCasual;
import com.redlongcitywork.easytourlite.model.Type;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author redlongcity 19/02/2018
 */
public class TourCasualNodeParser implements NodeParser<TourCasual> {

    private static final Logger LOG = Logger.getLogger(TourCasualNodeParser.class.getName());

    private final List<Meal_Type> mealTypeList;

    private final List<Currency> currencyList;

    private final List<From_Cities> cityList;

    public TourCasualNodeParser(
            List<Meal_Type> mealTypeList,
            List<Currency> currencyList,
            List<From_Cities> cityList) {
        this.mealTypeList = mealTypeList;
        this.currencyList = currencyList;
        this.cityList = cityList;
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
            SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            try {
                tour.setDateFrom((Date) formatter.parse(
                        jsonNode.path("date_from").asText()
                ));
            } catch (ParseException ex) {
                Logger.getLogger(TourCasualNodeParser.class.getName()).log(Level.SEVERE, null, ex);
            }
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
        for (Meal_Type type : mealTypeList) {
            if (type.getName().equals(name)) {
                return type;
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

    private From_Cities findCity(String name) {
        for (From_Cities city : cityList) {
            if (city.getName().equals(name)) {
                return city;
            }
        }
        return null;
    }
}
