package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvancedSession;
import java.util.List;

/**
 *
 * @author redlongcity 23/03/2018
 */
public interface TourAdvancedSessionService {

    TourAdvancedSession findById(Integer id);

    TourAdvancedSession findByRequest(SearchingRequest request);

    void saveTourAdvancedSession(TourAdvancedSession session);

    void updateTourAdvancedSession(TourAdvancedSession session);

    void deleteTourAdvancedSession(TourAdvancedSession session);

    List<TourAdvancedSession> findAll();

    void deleteAllTourAdvancedSessions();

    void saveOrUpdateTourAdvancedSession(TourAdvancedSession session);

}
