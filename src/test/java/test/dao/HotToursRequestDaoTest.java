package test.dao;

import com.redlongcitywork.easytourlite.dao.HotToursRequestDao;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import java.sql.Timestamp;
import javax.validation.ConstraintViolationException;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import test.database.TestJPAConfig;

/**
 *
 * @author redlongcity
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class HotToursRequestDaoTest extends TestJPAConfig {

    @Autowired
    private HotToursRequestDao dao;

    HotToursRequest request;

    @Before
    public void populate() {
        request = new HotToursRequest();
        request.setHotel_Rating("3");
        request.setNight_From(1);
        request.setNight_Till(2);
        request.setRequestTime(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void crudTest() {
        dao.save(request);
        assertTrue(dao.findAll().contains(request));
        request.setHotel_Rating("4");
        dao.mergeHotToursRequest(request);
        assertTrue(dao.findAll().contains(request));
        dao.deleteHotToursRequest(request);
        assertFalse(dao.findAll().contains(request));
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest() {
        request.setHotel_Rating(null);
        dao.save(request);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        request.setHotel_Rating("2");
        request.setNight_From(null);
        dao.save(request);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_3() {
        request.setNight_From(1);
        request.setNight_Till(null);
        dao.save(request);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_4() {
        request.setNight_Till(2);
        request.setRequestTime(null);
        dao.save(request);
        dao.findAll();
    }

}
