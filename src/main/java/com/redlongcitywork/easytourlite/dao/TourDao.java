package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.model.session.HotToursSession;
import java.util.List;
/**
 *
 * @author redlongcity
 * 09.09.2017
 * Interface for dao manipulations
 */

public interface TourDao {

    List<Tour> findAll();

    List<Tour> getToursBeforeDate(Integer date);

    List<Tour> getToursBetweenDates(Integer dateBefore,
            Integer dateTill);
    
    Tour findByKey(String key);

    void save(Tour tour);

    void mergeTour(Tour tour);

    void deleteTour(Tour tour);
    
    void saveOrUpdateTour(Tour tour);

}
