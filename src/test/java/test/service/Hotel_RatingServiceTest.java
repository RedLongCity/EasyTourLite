package test.service;

import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.service.Hotel_RatingService;
import javax.validation.ConstraintViolationException;
import org.hibernate.HibernateException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import test.config.TestJPAConfig;

/**
 *
 * @author redlongcity 13/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class Hotel_RatingServiceTest extends TestJPAConfig {

    @Autowired
    private Hotel_RatingService service;

    private Hotel_Rating rating;

    @Before
    public void populate() {
        rating = new Hotel_Rating();
        rating.setId("-1");
        rating.setName("Hotel_RatingName");
    }

    @Test
    public void crudTest() {
        service.saveHotel_Rating(rating);
        assertTrue(service.findAll().contains(rating));
        rating.setName("AnotherHotel_RatingName");
        service.updateHotel_Rating(rating);
        assertTrue(service.findAll().contains(rating));
        service.deleteHotel_Rating(rating);
        assertFalse(service.findAll().contains(rating));
    }

    @Test(expected = HibernateException.class)
    public void exceptionTest() {
        rating.setId(null);
        service.saveHotel_Rating(rating);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        rating.setId("-1");
        rating.setName(null);
        service.saveHotel_Rating(rating);
        service.findAll();
    }

}
