package test.dao;

import com.redlongcitywork.easytourlite.dao.TourAdvancedSessionDao;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.model.TourAdvancedSession;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;
import test.database.TestJPAConfig;

/**
 *
 * @author redlongcity
 * 24/03/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TourAdvancedSessionDaoTest extends TestJPAConfig{
    
    @Autowired
    private TourAdvancedSessionDao dao;
    
    private SearchingRequest request;
    
    private TourAdvanced tour;
    
    private TourAdvancedSession session;
    
    @Before
    public void populate(){
        session = new TourAdvancedSession();
        request = new SearchingRequest();
        tour = new TourAdvanced();
        
        request.setId("id");
        req
    }
    
}
