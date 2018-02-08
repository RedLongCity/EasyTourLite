package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.SearchingRequest;
import java.util.List;

/**
 *
 * @author redlongcity 08/02/2018
 */
public interface SearchingRequestDao {

    List<SearchingRequest> findAll();

    SearchingRequest findById(Integer id);

    void saveSearchingRequest(SearchingRequest request);

    void mergeSearchingRequest(SearchingRequest request);

    void deleteSearchingRequest(SearchingRequest request);

    void saveOrUpdateSearchingRequest(SearchingRequest request);

}
