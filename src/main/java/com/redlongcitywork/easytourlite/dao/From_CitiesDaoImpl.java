package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.From_Cities;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;
/**
 *
 * @author redlongcity
 */
@Repository("from_CitiesDao")
public class From_CitiesDaoImpl extends AbstractDao<String,From_Cities> implements From_CitiesDao {

    @Override
    public List<From_Cities> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("name"));
        List<From_Cities> from_CitiesList = (List<From_Cities>)crit.list();
        return from_CitiesList;
    }

    @Override
    public From_Cities findById(String id) {
        From_Cities from_Cities = getByKey(id);
        return from_Cities;
    }

    @Override
    public void save(From_Cities from_Cities) {
        persist(from_Cities);
    }

    @Override
    public void deleteFrom_Cities(From_Cities from_Cities) {
        delete(from_Cities);
    }


    @Override
    public void mergeFrom_Cities(From_Cities from_Cities) {
        merge(from_Cities);
    }
    
    @Override
    public void saveOrUpdateFrom_Cities(From_Cities city){
        saveOrUpdate(city);
    }
    
}
