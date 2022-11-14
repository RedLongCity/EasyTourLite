package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.HotToursRequestDao;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
 *
 * @author redlongcity
 * 13/12/2017
 */

@Service("hotToursRequestService")
@Transactional
public class HotToursRequestServiceImpl implements HotToursRequestService {

    @Autowired
    HotToursRequestDao dao;

    @Override
    public HotToursRequest findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public HotToursRequest findByFields(HotToursRequest request) {
        return dao.findByFields(request);
    }

    @Override
    public void saveHotToursRequest(HotToursRequest request) {
        dao.save(request);
    }

    @Override
    public void updateHotToursRequest(HotToursRequest request) {
        HotToursRequest entity = dao.findById(request.getId());
        if (entity != null) {
            entity.setCountry(request.getCountry());
            entity.setFrom_Cities(request.getFrom_Cities());
            entity.setHotel_Rating(request.getHotel_Rating());
            entity.setMeal_Type(request.getMeal_Type());
            entity.setNight_From(request.getNight_From());
            entity.setNight_Till(request.getNight_Till());
            entity.setRequestTime(request.getRequestTime());
            dao.mergeHotToursRequest(entity);
        }
    }

    @Override
    public void deleteHotToursRequest(HotToursRequest request) {
        dao.deleteHotToursRequest(request);
    }

    @Override
    public List<HotToursRequest> findAllHotRequests() {
        return dao.findAll();
    }

    @Override
    public void deleteAllHotToursRequests() {
        List<HotToursRequest> requestList = dao.findAll();
        if (requestList != null) {
            for (HotToursRequest request : requestList) {
                dao.deleteHotToursRequest(request);
            }
        }
    }

    @Override
    public void saveOrUpdateHotToursRequest(HotToursRequest request) {
        dao.saveOrUpdateHotToursRequest(request);
    }
    
    
}
