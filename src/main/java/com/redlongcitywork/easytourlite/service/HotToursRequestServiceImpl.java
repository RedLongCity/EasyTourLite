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
    HotToursRequestDao requestDao;

    @Override
    public HotToursRequest findById(Integer id) {
        return requestDao.findById(id);
    }

    @Override
    public void saveHotToursRequest(HotToursRequest request) {
        requestDao.save(request);
    }

    @Override
    public void updateHotToursRequest(HotToursRequest request) {
        HotToursRequest entity = requestDao.findById(request.getId());
        if (entity != null) {
            entity.setCountry(request.getCountry());
            entity.setFrom_Cities(request.getFrom_Cities());
            entity.setHotel_Rating(request.getHotel_Rating());
            entity.setMeal_Type(request.getMeal_Type());
            entity.setNight_From(request.getNight_From());
            entity.setNight_Till(request.getNight_Till());
            entity.setRequestTime(request.getRequestTime());
            requestDao.mergeHotToursRequest(entity);
        }
    }

    @Override
    public void deleteHotToursRequest(HotToursRequest request) {
        requestDao.deleteHotToursRequest(request);
    }

    @Override
    public List<HotToursRequest> findAll() {
        return requestDao.findAll();
    }

    @Override
    public void deleteAllHotToursRequests() {
        List<HotToursRequest> requestList = requestDao.findAll();
        if (requestList != null) {
            for (HotToursRequest request : requestList) {
                requestDao.deleteHotToursRequest(request);
            }
        }
    }
}
