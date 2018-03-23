package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.TourAdvancedSessionDao;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvancedSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 23/03/2018
 */
@Service("tourAdvancedSessionService")
public class TourAdvancedSessionServiceImpl implements TourAdvancedSessionService {
    
    @Autowired
    private TourAdvancedSessionDao dao;
    
    @Override
    public TourAdvancedSession findById(Integer id) {
        return dao.findById(id);
    }
    
    @Override
    public TourAdvancedSession findByRequest(SearchingRequest request) {
        return dao.findByRequest(request);
    }
    
    @Override
    public void saveTourAdvancedSession(TourAdvancedSession session) {
        dao.save(session);
    }
    
    @Override
    public void updateTourAdvancedSession(TourAdvancedSession session) {
        TourAdvancedSession entity = dao.findById(session.getId());
        if (entity != null) {
            entity.setId(session.getId());
            entity.setRequest(session.getRequest());
            entity.setTours(session.getTours());
            dao.mergeTourAdvancedSession(entity);
        }
    }
    
    @Override
    public void deleteTourAdvancedSession(TourAdvancedSession session) {
        dao.deleteTourAdvancedSession(session);
    }
    
    @Override
    public List<TourAdvancedSession> findAll() {
        return dao.findAll();
    }
    
    @Override
    public void deleteAllTourAdvancedSessions() {
        List<TourAdvancedSession> list = dao.findAll();
        if (list != null) {
            for (TourAdvancedSession session : list) {
                dao.deleteTourAdvancedSession(session);
            }
        }
    }
    
    @Override
    public void saveOrUpdateTourAdvancedSession(TourAdvancedSession session) {
        dao.saveOrUpdateTourAdvancedSession(session);
    }
    
}
