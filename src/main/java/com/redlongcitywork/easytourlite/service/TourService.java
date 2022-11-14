package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.Tour;
import java.util.List;
import org.hibernate.criterion.Criterion;

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
   
   List<Tour> findByCriterions(List<Criterion> list);
   
   void deleteAllTours();
    
}
