package test.dao;

import com.redlongcitywork.easytourlite.dao.RegionDao;
import com.redlongcitywork.easytourlite.model.Region;
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
public class RegionDaoTest extends TestJPAConfig {

    @Autowired
    private RegionDao dao;

    private Region region;

    @Before
    public void populate() {
        region = new Region();
        region.setId(1);
        region.setName("RegionName");
    }

    @Test
    public void crudTest() {
        dao.save(region);
        assertTrue(dao.findAll().contains(region));
        region.setName("AnotherRegionName");
        dao.mergeRegion(region);
        assertTrue(dao.findAll().contains(region));
        dao.deleteRegion(region);
        assertFalse(dao.findAll().contains(region));
    }

    @Test(expected = HibernateException.class)
    public void exceptionTest() {
        region.setId(null);
        dao.save(region);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        region.setId(0);
        region.setName(null);
        dao.save(region);
        dao.findAll();
    }

}
