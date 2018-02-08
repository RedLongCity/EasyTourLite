package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.SearchingRequest;
import java.util.List;

/**
 *
 * @author redlongcity 08/02/2018
 */
public interface SearchingRequestService {

    SearchingRequest findById(Integer id);

    void saveSearchingRequest(SearchingRequest request);

    void updateSearchingRequest(SearchingRequest request);

    void deleteSearchingRequest(SearchingRequest request);

    List<SearchingRequest> findAll();

    void deleteAllSearchingRequests();

    void saveOrUpdateSearchingRequest(SearchingRequest request);

}
