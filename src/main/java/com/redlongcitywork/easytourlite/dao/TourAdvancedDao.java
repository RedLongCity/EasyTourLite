package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.TourAdvanced;
import java.util.List;

/**
 *
 * @author redlongcity 03/03/2018
 */
public interface TourAdvancedDao {

    List<TourAdvanced> findAll();

    TourAdvanced findByKey(String key);

    void save(TourAdvanced tour);

    void mergeTourAdvanced(TourAdvanced tour);

    void deleteTourAdvanced(TourAdvanced tour);

    void saveOrUpdateTourAdvanced(TourAdvanced tour);

}
