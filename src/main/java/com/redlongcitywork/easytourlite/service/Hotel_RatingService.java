package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import java.util.List;
/**
 *
 * @author redlongcity
 */
public interface Hotel_RatingService {
    
   Hotel_Rating findById(String id);
   
   Hotel_Rating findByName(String name);
   
   void saveHotel_Rating(Hotel_Rating hotel_Rating);
   
   void updateHotel_Rating(Hotel_Rating hotel_Rating);
   
   void deleteHotel_Rating(Hotel_Rating hotel_Rating);
   
   List<Hotel_Rating> findAll();
   
   void deleteAllHotel_Rating();
   
   void saveOrUpdateHotel_Rating(Hotel_Rating rating);
    
}
