package test.dao;

import com.redlongcitywork.easytourlite.dao.TourAdvancedSessionDao;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.model.TourAdvancedSession;
import com.redlongcitywork.easytourlite.service.SearchingRequestService;
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
 * @author redlongcity 24/03/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
@Ignore("testWithoutDB")
public class TourAdvancedSessionDaoTest extends TestJPAConfig {

    @Autowired
    private TourAdvancedSessionDao dao;

    @Autowired
    private Instances instances;
    
    @Autowired
    private SearchingRequestService service;

    private SearchingRequest request;

    private TourAdvanced tour;

    private TourAdvancedSession session;

    @Before
    public void populate() {
        session = new TourAdvancedSession();
        request = instances.getSearchingRequest();
        tour = instances.getTourAdvanced();
        instances.saveInstances();
        instances.saveSearchingRequest();
        
        session.setRequest(request);
        session.getTours().add(tour);
    }

    @Test
    public void crudTest() {
        dao.save(session);
        assertTrue(dao.findAll().contains(session));
        session.getTours().add(instances.getTourAdvanced());
        dao.mergeTourAdvancedSession(session);
        assertTrue(dao.findAll().contains(session));
        dao.deleteTourAdvancedSession(session);
        assertFalse(dao.findAll().contains(session));
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest() {
        session.setRequest(null);
        dao.save(session);
        dao.findAll();
    }

}
