package test.dao;

import com.redlongcitywork.easytourlite.dao.TypeDao;
import com.redlongcitywork.easytourlite.model.Type;
import javax.persistence.PersistenceException;
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
 * @author redlongcity 14/02/2018
 */
@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class TypeDaoTest extends TestJPAConfig {

    @Autowired
    private TypeDao dao;

    private Type type;

    @Before
    public void populate() {
        type = new Type();
        type.setId("-1");
        type.setName("TypeName");
    }

    @Test
    public void crudTest() {
        dao.save(type);
        assertTrue(dao.findAll().contains(type));
        type.setName("AnotherTypeName");
        dao.mergeType(type);
        assertTrue(dao.findAll().contains(type));
        dao.deleteType(type);
        assertFalse(dao.findAll().contains(type));
    }

    @Test(expected = PersistenceException.class)
    public void exceptionTest() {
        type.setId(null);
        dao.save(type);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        type.setId("-1");
        type.setName(null);
        dao.save(type);
        dao.findAll();
    }
}
