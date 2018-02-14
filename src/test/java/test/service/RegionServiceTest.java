package test.service;

import com.redlongcitywork.easytourlite.model.Region;
import com.redlongcitywork.easytourlite.service.RegionService;
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
import test.database.TestJPAConfig;

/**
 *
 * @author redlongcity 14/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class RegionServiceTest extends TestJPAConfig {

    @Autowired
    private RegionService service;

    private Region region;

    @Before
    public void populate() {
        region = new Region();
        region.setId(1);
        region.setName("RegionName");
    }

    @Test
    public void crudTest() {
        service.saveRegion(region);
        assertTrue(service.findAll().contains(region));
        region.setName("AnotherRegionName");
        service.updateRegion(region);
        assertTrue(service.findAll().contains(region));
        service.deleteRegion(region);
        assertFalse(service.findAll().contains(region));
    }

    @Test(expected = HibernateException.class)
    public void exceptionTest() {
        region.setId(null);
        service.saveRegion(region);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        region.setId(0);
        region.setName(null);
        service.saveRegion(region);
        service.findAll();
    }

}
