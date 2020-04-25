package test.dao;

import com.redlongcitywork.easytourlite.dao.HotToursRequestDao;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import java.sql.Timestamp;
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

/**
 *
 * @author redlongcity
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Ignore("testWithoutDB")
public class HotToursRequestDaoTest extends TestJPAConfig {

    @Autowired
    private HotToursRequestDao dao;

    @Autowired
    private Instances instances;

    private HotToursRequest request;

    @Before
    public void populate() {
        request = new HotToursRequest();
        request.getRatings().add(instances.getRating());
        request.setNight_From(1);
        request.setNight_Till(2);
        request.setRequestTime(new Timestamp(System.currentTimeMillis()));
    }

    @Test
    public void crudTest() {
        dao.save(request);
        assertTrue(dao.findAll().contains(request));
        request.setNight_From(3);
        dao.mergeHotToursRequest(request);
        assertTrue(dao.findAll().contains(request));
        dao.deleteHotToursRequest(request);
        assertFalse(dao.findAll().contains(request));
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest() {
        request.setRatings(null);
        dao.save(request);
        dao.findAll();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        request.getRatings().add(instances.getRating());
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
