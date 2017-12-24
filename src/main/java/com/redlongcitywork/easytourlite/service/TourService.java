package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.Tour;
import java.util.List;

/**
 *
 * @author redlongcity
 */

public interface TourService {
    
   Tour findByKey(String key);
   
   void saveTour(Tour tour);
   
   void saveOrUpdateTour(Tour tour);
   
   void updateTour(Tour tour);
   
   void deleteTour(Tour tour);
   
   void deleteToursBeforeDate(Integer date);
   
   void deleteToursBetweenDats(Integer dateFrom,
           Integer dateTill);
   
   List<Tour> findAll();
   
   void deleteAllTours();
    
}
