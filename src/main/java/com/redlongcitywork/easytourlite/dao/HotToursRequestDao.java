package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import java.util.List;

/**
 *
 * @author redlongcity
 * 13/12/2017
 */
public interface HotToursRequestDao {

    List<HotToursRequest> findAll();

    HotToursRequest findById(Integer id);

    void save(HotToursRequest request);

    void mergeHotToursRequest(HotToursRequest request);

    void deleteHotToursRequest(HotToursRequest request);
}
