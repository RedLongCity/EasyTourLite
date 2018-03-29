package test.dao;

import com.redlongcitywork.easytourlite.dao.Hotel_RatingDao;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
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
public class Hotel_RatingDaoTest extends TestJPAConfig {

    @Autowired
    private Hotel_RatingDao dao;

    private Hotel_Rating rating;

    @Before
    public void populate() {
        rating = new Hotel_Rating();
        rating.setId("-1");
        rating.setName("Hotel_RatingName");
    }

    @Test
    public void crudTest() {
        dao.save(rating);
        assertTrue(dao.findAll().contains(rating));
        rating.setName("AnotherHotel_RatingName");
        dao.mergeHotel_Rating(rating);
        assertTrue(dao.findAll().contains(rating));
        dao.deleteHotel_Rating(rating);
        assertFalse(dao.findAll().contains(rating));
    }

    @Test(expected = HibernateException.class)
    public void exceptionTest() {
        rating.setId(null);
        dao.save(rating);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        rating.setId("-1");
        rating.setName(null);
        dao.save(rating);
        dao.findAll();
    }

}
