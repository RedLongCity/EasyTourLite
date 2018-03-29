package test.service;

import com.redlongcitywork.easytourlite.model.HotelFilter;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.RegionService;
import com.redlongcitywork.easytourlite.service.TypeService;
import javax.validation.ConstraintViolationException;
import org.hibernate.id.IdentifierGenerationException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import test.config.TestJPAConfig;
import com.redlongcitywork.easytourlite.service.HotelFilterService;

/**
 *
 * @author redlongcity 16/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class HotelFilterServiceTest extends TestJPAConfig {

    @Autowired
    private HotelFilterService service;

    @Autowired
    private Hotel_RatingService hotelService;

    @Autowired
    private RegionService regionService;

    @Autowired
    private TypeService typeService;

    private HotelFilter hotel;

    private Hotel_Rating rating;

    private Region region;

    private Type type1;

    private Type type2;

    @Before
    public void populate() {
        hotel = new HotelFilter();
        hotel.setId("id");
        hotel.setName("HotelName");
        rating = new Hotel_Rating();
        rating.setId("0");
        rating.setName("RatingName");
        region = new Region();
        region.setId("id");
        region.setName("RegionName");
        type1 = new Type();
        type1.setId("id_1");
        type1.setName("Type1Name");
        type2 = new Type();
        type2.setId("id_2");
        type2.setName("Type2Name");
        hotelService.saveHotel_Rating(rating);
        regionService.saveRegion(region);
        typeService.saveType(type1);
        typeService.saveType(type2);
        hotel.setRating(rating);
        hotel.setRegion(region);
        hotel.getTypeSet().add(type1);
        hotel.getTypeSet().add(type2);
    }

    @Test
    public void crudTest() {
        service.saveHotel(hotel);
        assertTrue(service.findAll().contains(hotel));
        hotel.setName("AnotherHotelName");
        service.updateHotel(hotel);
        assertTrue(service.findAll().contains(hotel));
        service.deleteHotel(hotel);
        assertFalse(service.findAll().contains(hotel));
    }

    @Test(expected = IdentifierGenerationException.class)
    public void exceptionTest() {
        hotel.setId(null);
        service.saveHotel(hotel);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        hotel.setId("id");
        hotel.setName(null);
        service.saveHotel(hotel);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_3() {
        hotel.setName("HotelName");
        hotel.setRating(null);
        service.saveHotel(hotel);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_4() {
        hotel.setRating(rating);
        hotel.setRegion(null);
        service.saveHotel(hotel);
        service.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_5() {
        hotel.setRegion(region);
        hotel.setTypeSet(null);
        service.saveHotel(hotel);
        service.findAll();
    }
}
