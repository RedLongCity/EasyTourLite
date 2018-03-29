package test.service;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.service.HotToursRequestService;
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
import test.dao.Instances;
import test.config.TestJPAConfig;

/**
 *
 * @author redlongcity 13/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class HotToursRequestServiceTest extends TestJPAConfig {

    @Autowired
    private HotToursRequestService service;

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
        service.saveHotToursRequest(request);
        assertTrue(service.findAllHotRequests().contains(request));
        request.getRatings().add(instances.getRating());
        service.updateHotToursRequest(request);
        assertTrue(service.findAllHotRequests().contains(request));
        service.deleteHotToursRequest(request);
        assertFalse(service.findAllHotRequests().contains(request));
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest() {
        request.setRatings(null);
        service.saveHotToursRequest(request);
        service.findAllHotRequests();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        request.getRatings().add(instances.getRating());
        request.setNight_From(null);
        service.saveHotToursRequest(request);
        service.findAllHotRequests();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_3() {
        request.setNight_From(1);
        request.setNight_Till(null);
        service.saveHotToursRequest(request);
        service.findAllHotRequests();
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_4() {
        request.setNight_Till(2);
        request.setRequestTime(null);
        service.saveHotToursRequest(request);
        service.findAllHotRequests();
    }

}
