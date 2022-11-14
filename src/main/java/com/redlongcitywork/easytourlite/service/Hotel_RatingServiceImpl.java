package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.Hotel_RatingDao;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author redlongcity
 */
@Service("hotel_RatingService")
@Transactional
public class Hotel_RatingServiceImpl implements Hotel_RatingService {

    @Autowired
    Hotel_RatingDao dao;

    @Override
    public Hotel_Rating findById(String id) {
        return dao.findById(id);
    }

    @Override
    public void saveHotel_Rating(Hotel_Rating hotel_Rating) {
        dao.save(hotel_Rating);
    }

    @Override
    public void updateHotel_Rating(Hotel_Rating hotel_Rating) {
        Hotel_Rating entity = dao.findById(hotel_Rating.getId());
        if (entity != null) {
            entity.setName(hotel_Rating.getName());
            dao.mergeHotel_Rating(entity);
        }
    }

    @Override
    public void deleteHotel_Rating(Hotel_Rating hotel_Rating) {
        dao.deleteHotel_Rating(hotel_Rating);
    }

    @Override
    public List<Hotel_Rating> findAll() {
        return dao.findAll();
    }

    @Override
    public void deleteAllHotel_Rating() {
        List<Hotel_Rating> hotel_RatingList = dao.findAll();
        if (hotel_RatingList != null) {
            for (Hotel_Rating hotel_Rating : hotel_RatingList) {
                dao.deleteHotel_Rating(hotel_Rating);
            }
        }
    }

    @Override
    public Hotel_Rating findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public void saveOrUpdateHotel_Rating(Hotel_Rating rating) {
        dao.saveOrUpdateHotel_Rating(rating);
    }
    
}
