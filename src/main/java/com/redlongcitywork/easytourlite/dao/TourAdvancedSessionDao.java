package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvancedSession;
import java.util.List;

/**
 *
 * @author redlongcity 23/03/2018
 */
public interface TourAdvancedSessionDao {

    List<TourAdvancedSession> findAll();

    TourAdvancedSession findById(Integer id);

    TourAdvancedSession findByRequest(SearchingRequest request);

    void save(TourAdvancedSession session);

    void mergeTourAdvancedSession(TourAdvancedSession session);

    void deleteTourAdvancedSession(TourAdvancedSession session);

    void saveOrUpdateTourAdvancedSession(TourAdvancedSession session);

}
