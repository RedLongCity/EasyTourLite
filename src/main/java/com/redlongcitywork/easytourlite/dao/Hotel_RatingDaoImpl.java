package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
/**
 *
 * @author redlongcity
 */
@Repository("hotel_RatingDao")
public class Hotel_RatingDaoImpl extends AbstractDao<String,Hotel_Rating> implements Hotel_RatingDao {

    @Override
    public List<Hotel_Rating> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("name"));
        List<Hotel_Rating> hotel_RatingList = (List<Hotel_Rating>)crit.list();
        return hotel_RatingList;
    }

    @Override
    public Hotel_Rating findById(String id) {
        Hotel_Rating hotel_Rating = getByKey(id);
        return hotel_Rating;
    }

    @Override
    public Hotel_Rating findByName(String name) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("name",name));
        Hotel_Rating hotel_Rating = (Hotel_Rating)crit.uniqueResult();
        return hotel_Rating;
    }
    
    

    @Override
    public void save(Hotel_Rating hotel_Rating) {
        persist(hotel_Rating);
    }

    @Override
    public void deleteHotel_Rating(Hotel_Rating hotel_Rating) {
        delete(hotel_Rating);
    }

    @Override
    public void mergeHotel_Rating(Hotel_Rating hotel_Rating) {
        merge(hotel_Rating);
    }

    @Override
    public void saveOrUpdateHotel_Rating(Hotel_Rating rating) {
        saveOrUpdate(rating);
    }
    
}
