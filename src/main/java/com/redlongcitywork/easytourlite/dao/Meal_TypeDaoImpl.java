package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.Meal_Type;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
/**
 *
 * @author redlongcity
 */
@Repository("meal_TypeDao")
public class Meal_TypeDaoImpl extends AbstractDao<String,Meal_Type> implements Meal_TypeDao {

    @Override
    public List<Meal_Type> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("name"));
        List<Meal_Type> meal_TypeList = (List<Meal_Type>) crit.list();
        return meal_TypeList;
    }

    @Override
    public Meal_Type findById(String id) {
        Meal_Type meal_Type = getByKey(id);
        return meal_Type;
    }

    @Override
    public Meal_Type findByName(String name) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("name",name));
        Meal_Type meal_Type = (Meal_Type)crit.uniqueResult();
        return meal_Type;
    }
    
    

    @Override
    public void save(Meal_Type meal_Type) {
        persist(meal_Type);
    }

    @Override
    public void deleteMeal_Type(Meal_Type meal_Type) {
        delete(meal_Type);
    }

    @Override
    public void mergeMeal_Type(Meal_Type meal_Type) {
        merge(meal_Type);
    }

    @Override
    public void saveOrUpdateMeal_Type(Meal_Type type) {
        saveOrUpdate(type);
    }
    
}
