package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import java.util.List;

/**
 *
 * @author redlongcity
 * 13/12/2017
 */
public interface HotToursRequestService {
    
        
    HotToursRequest findById(Integer id);
    
    HotToursRequest findByFields(HotToursRequest request);
    
    void saveHotToursRequest(HotToursRequest request);
    
    void updateHotToursRequest(HotToursRequest request);
    
    void deleteHotToursRequest(HotToursRequest request);
    
    List<HotToursRequest> findAllHotRequests();
    
    void deleteAllHotToursRequests();
    
    void saveOrUpdateHotToursRequest(HotToursRequest request);
    
}
