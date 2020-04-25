package test.dao;

import com.redlongcitywork.easytourlite.model.HotelFilter;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import com.redlongcitywork.easytourlite.service.RegionService;
import com.redlongcitywork.easytourlite.service.TypeService;
import javax.validation.ConstraintViolationException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import test.config.TestJPAConfig;
import com.redlongcitywork.easytourlite.dao.HotelFilterDao;
import javax.persistence.PersistenceException;

/**
 *
 * @author redlongcity 16/02/18
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Ignore("testWithoutDB")
public class HotelFilterDaoTest extends TestJPAConfig {

    @Autowired
    private HotelFilterDao dao;

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
        dao.save(hotel);
        assertTrue(dao.findAll().contains(hotel));
        hotel.setName("AnotherHotelName");
        dao.mergeHotel(hotel);
        assertTrue(dao.findAll().contains(hotel));
        dao.deleteHotel(hotel);
        assertFalse(dao.findAll().contains(hotel));
    }

    @Test(expected = PersistenceException.class)
    public void exceptionTest() {
        hotel.setId(null);
        dao.save(hotel);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        hotel.setId("id");
        hotel.setName(null);
        dao.save(hotel);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_3() {
        hotel.setName("HotelName");
        hotel.setRating(null);
        dao.save(hotel);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_4() {
        hotel.setRating(rating);
        hotel.setRegion(null);
        dao.save(hotel);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_5() {
        hotel.setRegion(region);
        hotel.setTypeSet(null);
        dao.save(hotel);
        dao.findAll();
    }

}
