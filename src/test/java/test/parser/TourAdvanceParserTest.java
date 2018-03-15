package test.parser;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.Price;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.parsers.FacilityArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.HotelImageArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.TourAdvancedArrayNodeParser;
import com.redlongcitywork.easytourlite.parsers.TourAdvancedNodeParser;
import com.redlongcitywork.easytourlite.service.CountryService;
import com.redlongcitywork.easytourlite.service.CurrencyService;
import com.redlongcitywork.easytourlite.service.From_CitiesService;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import com.redlongcitywork.easytourlite.service.RegionService;
import com.redlongcitywork.easytourlite.service.TypeService;
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
    private CountryService countryService;

    @Mock
    private TypeService typeService;

    @Mock
    private RegionService regionService;

    @Mock
    private Meal_TypeService mealTypeService;

    @Mock
    private CurrencyService currencyService;

    @Mock
    private From_CitiesService cityService;

    @Mock
    private Hotel_RatingService ratingService;

    @Mock
    private HotelImageArrayNodeParser imageParser;

    @Mock
    private FacilityArrayNodeParser facilityParser;

    @InjectMocks
    private final TourAdvancedNodeParser parser = new TourAdvancedNodeParser(
            countryService,
            typeService,
            regionService,
            mealTypeService,
            currencyService,
            cityService,
            ratingService,
            imageParser,
            facilityParser
    );

    TourAdvancedArrayNodeParser arrayParser = new TourAdvancedArrayNodeParser(parser);

    private JsonNode node;

    @Before
    public void populate() {
        node = getJsonFromFile("json/tour_advance.json").path("offers");
    }

    @Test
    public void parsingTest() {
        stub(countryService.findAll()).toReturn(new ArrayList<>());
        stub(typeService.findAll()).toReturn(new ArrayList<>());
        stub(regionService.findAll()).toReturn(new ArrayList<>());
        stub(mealTypeService.findAll()).toReturn(new ArrayList<>());
        stub(currencyService.findAll()).toReturn(new ArrayList<>());
        stub(cityService.findAll()).toReturn(new ArrayList<>());
        stub(ratingService.findAll()).toReturn(new ArrayList<>());
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
