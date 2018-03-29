package test.service;

import com.redlongcitywork.easytourlite.model.Type;
import com.redlongcitywork.easytourlite.service.TypeService;
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
public class TypeServiceTest extends TestJPAConfig {

    @Autowired
    private TypeService service;

    private Type type;

    @Before
    public void populate() {
        type = new Type();
        type.setId("-1");
        type.setName("TypeName");
    }

    @Test
    public void crudTest() {
        service.saveType(type);
        assertTrue(service.findAll().contains(type));
        type.setName("AnotherTypeName");
        service.updateType(type);
        assertTrue(service.findAll().contains(type));
        service.deleteType(type);
        assertFalse(service.findAll().contains(type));
    }

    @Test(expected = HibernateException.class)
    public void exceptionTest() {
        type.setId(null);
        service.saveType(type);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        type.setId("-1");
        type.setName(null);
        service.saveType(type);
        service.findAll();
    }

}
