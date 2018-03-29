package test.service;

import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
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
 * @author redlongcity
 * 12/02/2018
 */

@RunWith(SpringJUnit4ClassRunner.class)
@Transactional
public class Meal_TypeServiceTest extends TestJPAConfig{
    
    @Autowired
    private Meal_TypeService service;

    private Meal_Type type;

    @Before
    public void populate() {
        type = new Meal_Type();
        type.setId("-1");
        type.setName("Meal_TypeName");
        type.setName_Full("Meal_TypeNameFull");
    }

    @Test
    public void crudTest() {
        service.saveMeal_Type(type);
        assertTrue(service.findAll().contains(type));
        type.setName("AnotherMeal_TypeName");
        type.setName_Full("AnotherMeal_TypeNameFull");
        service.updateMeal_Type(type);
        assertTrue(service.findAll().contains(type));
        service.deleteMeal_Type(type);
        assertFalse(service.findAll().contains(type));
    }

    @Test(expected = HibernateException.class)
    public void exceptionTest() {
        type.setId(null);
        service.saveMeal_Type(type);
    }

    @Test(expected = ConstraintViolationException.class)
    public void exceptionTest_2() {
        type.setId("-1");
        type.setName(null);
        service.saveMeal_Type(type);
        service.findAll();
    }
    
    
}
