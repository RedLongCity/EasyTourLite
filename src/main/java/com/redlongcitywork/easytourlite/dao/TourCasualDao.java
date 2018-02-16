package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.TourCasual;
import java.util.List;

/**
 *
 * @author redlongcity 16/02/2018
 */
public interface TourCasualDao {

    List<TourCasual> findAll();

    TourCasual findById(String id);

    void save(TourCasual tour);

    void mergeTourCasual(TourCasual tour);

    void deleteTourCasual(TourCasual tour);

    void saveOrUpdateTourCasual(TourCasual tour);

}
