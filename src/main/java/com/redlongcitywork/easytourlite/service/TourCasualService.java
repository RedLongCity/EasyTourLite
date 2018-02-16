package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.TourCasual;
import java.util.List;

/**
 *
 * @author redlongcity 16/02/2018
 */
public interface TourCasualService {

    TourCasual findById(String id);

    void saveTourCasual(TourCasual tour);

    void updateTourCasual(TourCasual tour);

    void deleteTourCasual(TourCasual tour);

    List<TourCasual> findAll();

    void deleteAllCountries();

    void saveOrUpdateTourCasual(TourCasual tour);
}
