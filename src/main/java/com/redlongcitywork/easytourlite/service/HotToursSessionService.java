package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.session.HotToursSession;
import java.util.List;

/**
 *
 * @author redlongcity 
 * 24/12/2017
 */
public interface HotToursSessionService {

    HotToursSession findById(Integer id);

    HotToursSession findByRequest(HotToursRequest request);

    void saveHotToursSession(HotToursSession session);

    void updateHotToursSession(HotToursSession session);

    void deleteHotToursSession(HotToursSession session);

    List<HotToursSession> findAll();

    void deleteAllHotToursSessions();

    void saveOrUpdateHotToursSession(HotToursSession session);

}
