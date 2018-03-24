package test.dao;

import com.redlongcitywork.easytourlite.dao.TourAdvancedSessionDao;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.model.TourAdvancedSession;
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
 * @author redlongcity 24/03/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TourAdvancedSessionDaoTest extends TestJPAConfig {

    @Autowired
    private TourAdvancedSessionDao dao;

    private SearchingRequest request;

    private TourAdvanced tour;

    private TourAdvancedSession session;

    @Before
    public void populate() {
        session = new TourAdvancedSession();
        request = Instances.getSearchingRequest();
        tour = Instances.getTourAdvanced();
        session.setRequest(request);
        session.getTours().add(tour);
    }

    @Test
    public void crudTest() {
        dao.save(session);
        assertTrue(dao.findAll().contains(session));
        session.getTours().add(Instances.getTourAdvanced());
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
