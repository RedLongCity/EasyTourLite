package test.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.From_Cities;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.TourCasual;
import com.redlongcitywork.easytourlite.parsers.TourCasualArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.TourCasualNodeParser;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.Mockito.stub;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author redlongcity 25/02/2018
 */
@RunWith(MockitoJUnitRunner.class)
public class TourCasualParserTest extends TestJsonConfig {

    @Mock
    private Meal_TypeService mealTypeService;

    @Mock
    private CurrencyService currencyService;

    @Mock
    private From_CitiesService cityService;

    @InjectMocks
    private TourCasualNodeParser nodeParser;

    private TourCasualArrayNodeParser arrayNodeParser
            = new TourCasualArrayNodeParser(nodeParser);

    private JsonNode tourCasualNode;

    @Before
    public void populate() {
        tourCasualNode = getJsonFromFile("json/tour_casual.json").
                path("offers");
    }

    @Test
    public void parsingTest() {
        Meal_Type mealType = new Meal_Type();
        mealType.setName("AI");
        mealType.setName_Full("Всё включено");
        Currency currency = new Currency();
        currency.setId("1");
        Currency currency_2 = new Currency();
        currency_2.setId("2");
        Currency currency_10 = new Currency();
        currency_10.setId("10");
        From_Cities city = new From_Cities();
        city.setName("Киев");
        List<Meal_Type> mealTypeList = new ArrayList<>();
        List<Currency> currencyList = new ArrayList<>();
        List<From_Cities> cityList = new ArrayList<>();
        mealTypeList.add(mealType);
        currencyList.add(currency);
        currencyList.add(currency_2);
        currencyList.add(currency_10);
        cityList.add(city);

        Price priceUsd = new Price();
        priceUsd.setCurrency(currency);
        priceUsd.setCost(510);

        Price priceUah = new Price();
        priceUah.setCurrency(currency_2);
        priceUah.setCost(13208);

        Price priceEur = new Price();
        priceEur.setCurrency(currency_10);
        priceEur.setCost(458);

        Set<Price> prices = new HashSet<>();
        prices.add(priceUsd);
        prices.add(priceUah);
        prices.add(priceEur);

        stub(mealTypeService.findAll()).toReturn(mealTypeList);
        stub(currencyService.findAll()).toReturn(currencyList);
        stub(cityService.findAll()).toReturn(cityList);

        TourCasual tour = new TourCasual();
        tour.setKey("10-02-7859fb5585b9e58792c85c75325a0691");
        tour.setMealType(mealType);
        tour.setRoomType("Standart - Dbl");
        tour.setDuration(6);
        tour.setCombined(false);
        tour.setCurrency(currency);
        tour.setPrices(prices);
        tour.setCity(city);
        tour.setTransportType("flight");

        List<TourCasual> tourList = new ArrayList<>();
        tourList.add(tour);

        assertEquals(arrayNodeParser.parseNode(tourCasualNode), tourList);

    }

}
