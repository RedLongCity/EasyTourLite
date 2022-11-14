package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.session.HotToursSession;
import java.util.List;

/**
 *
 * @author redlongcity 
 * 24/12/20147
 */
public interface HotToursSessionDao {

    List<HotToursSession> findAll();

    HotToursSession findById(Integer id);

    HotToursSession findByRequest(HotToursRequest request);

    void save(HotToursSession session);

    void mergeHotToursSession(HotToursSession session);

    void deleteHotToursSession(HotToursSession session);

    void saveOrUpdateHotToursSession(HotToursSession session);

}
