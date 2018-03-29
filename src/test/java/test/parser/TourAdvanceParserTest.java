package test.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.parsers.FacilityArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.HotelImageArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.TourAdvancedArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.TourAdvancedNodeParser;
import com.redlongcitywork.easytourlite.storage.CityStorage;
import com.redlongcitywork.easytourlite.storage.CountryStorage;
import com.redlongcitywork.easytourlite.storage.CurrencyStorage;
import com.redlongcitywork.easytourlite.storage.HotelRatingStorage;
import com.redlongcitywork.easytourlite.storage.MealTypeStorage;
import com.redlongcitywork.easytourlite.storage.RegionStorage;
import com.redlongcitywork.easytourlite.storage.TypeStorage;
import java.sql.Date;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import static org.mockito.Matchers.anyObject;
import org.mockito.Mock;
import static org.mockito.Mockito.stub;
import org.mockito.runners.MockitoJUnitRunner;

/**
 *
 * @author redlongcity 04/03/2018
 */
@RunWith(MockitoJUnitRunner.class)
public class TourAdvanceParserTest extends TestJsonConfig {

    @Mock
    private CountryStorage countryStorage;

    @Mock
    private TypeStorage typeStorage;

    @Mock
    private RegionStorage regionStorage;

    @Mock
    private MealTypeStorage mealTypeStorage;

    @Mock
    private CurrencyStorage currencyStorage;

    @Mock
    private CityStorage cityStorage;

    @Mock
    private HotelRatingStorage ratingStorage;

    @Mock
    private HotelImageArrayNodeParser imageParser;

    @Mock
    private FacilityArrayNodeParser facilityParser;

    @InjectMocks
    private final TourAdvancedNodeParser parser = new TourAdvancedNodeParser(
            imageParser,
            facilityParser,
            countryStorage,
            cityStorage,
            typeStorage,
            mealTypeStorage,
            regionStorage,
            currencyStorage,
            ratingStorage
    );

    TourAdvancedArrayNodeParser arrayParser = new TourAdvancedArrayNodeParser(parser);

    private JsonNode node;

    @Before
    public void populate() {
        node = getJsonFromFile("json/tour_advance.json").path("offers");
    }

    @Test
    public void parsingTest() {
        stub(countryStorage.getContent()).toReturn(new ArrayList<>());
        stub(typeStorage.getContent()).toReturn(new ArrayList<>());
        stub(regionStorage.getContent()).toReturn(new ArrayList<>());
        stub(mealTypeStorage.getContent()).toReturn(new ArrayList<>());
        stub(currencyStorage.getContent()).toReturn(new ArrayList<>());
        stub(cityStorage.getContent()).toReturn(new ArrayList<>());
        stub(ratingStorage.getContent()).toReturn(new ArrayList<>());
        stub(imageParser.parseNode(anyObject())).toReturn(new ArrayList<>());
        stub(facilityParser.parseNode(anyObject())).toReturn(new ArrayList<>());

        Currency currency = new Currency();
        currency.setId("1");
        Currency currency_2 = new Currency();
        currency_2.setId("2");
        Currency currency_10 = new Currency();
        currency_10.setId("10");

        Price priceUsd = new Price();
        priceUsd.setCurrency(currency);
        priceUsd.setCost(510);

        Price priceUah = new Price();
        priceUah.setCurrency(currency_2);
        priceUah.setCost(13208);

        Price priceEur = new Price();
        priceEur.setCurrency(currency_10);
        priceEur.setCost(458);

        TourAdvanced tour = new TourAdvanced();
        tour.setKey("01-03-1e3fb3891a40c9025d145e58014b80d4");
        tour.setHotelId(68);
        tour.setHotelName("Luna Sharm");
        tour.setAdultAmount(2);
        tour.setChildAmount(0);
        tour.setAccomodation("DBL");
        tour.setRoomType("Standart");
        tour.setDuration(6);
        Date date = Date.valueOf(LocalDate.parse("2017-01-26"));
        tour.setDateFrom(date);
        tour.setCombined(Boolean.TRUE);
        tour.getPrices().add(priceUsd);
        tour.getPrices().add(priceUah);
        tour.getPrices().add(priceEur);
        tour.setTransportType("flight");
        tour.setRate("2.3");
        tour.setReviewCount("12");
        List<TourAdvanced> list = new ArrayList<>();
        list.add(tour);
        assertTrue(arrayParser.parseNode(node).equals(list));
    }

}
