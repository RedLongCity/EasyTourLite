package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.HotToursSessionDao;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.session.HotToursSession;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity 
 * 24/12/2017
 */
@Service("hotToursSessionService")
@Transactional
public class HotToursSessionServiceImpl implements HotToursSessionService {
    
    @Autowired
    HotToursSessionDao dao;
    
    @Override
    public HotToursSession findById(Integer id) {
        return dao.findById(id);
    }
    
    @Override
    public HotToursSession findByRequest(HotToursRequest request) {
        return dao.findByRequest(request);
    }
    
    @Override
    public void saveHotToursSession(HotToursSession session) {
        dao.save(session);
    }
    
    @Override
    public void updateHotToursSession(HotToursSession session) {
        HotToursSession entity = dao.findById(session.getId());
        if (entity != null) {
            entity.setRequest(session.getRequest());
            entity.setToursSet(session.getToursSet());
            dao.mergeHotToursSession(entity);
        }
    }
    
    @Override
    public void deleteHotToursSession(HotToursSession session) {
        dao.deleteHotToursSession(session);
    }
    
    @Override
    public List<HotToursSession> findAll() {
        return dao.findAll();
    }
    
    @Override
    public void deleteAllHotToursSessions() {
        List<HotToursSession> list = dao.findAll();
        if (list != null) {
            for (HotToursSession session : list) {
                dao.deleteHotToursSession(session);
            }
        }
    }
    
    @Override
    public void saveOrUpdateHotToursSession(HotToursSession session) {
        dao.saveOrUpdateHotToursSession(session);
    }
    
}
