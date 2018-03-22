package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.SearchingRequestDao;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity 08/02/2018
 */
@Service("searchingRequestService")
@Transactional
public class SearchingRequestServiceImpl implements SearchingRequestService {

    @Autowired
    private SearchingRequestDao dao;

    @Override
    public SearchingRequest findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public void saveSearchingRequest(SearchingRequest request) {
        dao.saveSearchingRequest(request);
    }

    @Override
    public void updateSearchingRequest(SearchingRequest request) {
        SearchingRequest entity = dao.findById(request.getId());
        if (entity != null) {
            entity.setType(request.getType());
            entity.setKind(request.getKind());
            entity.setKind(request.getKind());
            entity.setCountry(request.getCountry());
            entity.setCity(request.getCity());
            entity.setRegions(request.getRegions());
            entity.setRatingSet(request.getRatingSet());
            entity.setAdultAmount(request.getAdultAmount());
            entity.setChildAmount(request.getChildAmount());
            entity.setChildAge(request.getChildAge());
            entity.setNightFrom(request.getNightFrom());
            entity.setNightTill(request.getNightTill());
            entity.setDateFrom(request.getDateFrom());
            entity.setDateTill(request.getDateTill());
            entity.setMealTypes(request.getMealTypes());
            entity.setPriceFrom(request.getPriceFrom());
            entity.setPriceTill(request.getPriceTill());
            entity.setCurrency(request.getCurrency());
            entity.setOnlyStandart(request.getOnlyStandart());
            entity.setRequestTime(request.getRequestTime());
            dao.mergeSearchingRequest(entity);
        }
    }

    @Override
    public void deleteSearchingRequest(SearchingRequest request) {
        dao.deleteSearchingRequest(request);
    }

    @Override
    public List<SearchingRequest> findAll() {
        return dao.findAll();
    }

    @Override
    public void deleteAllSearchingRequests() {
        List<SearchingRequest> list = dao.findAll();
        if (list != null) {
            for (SearchingRequest request : list) {
                dao.deleteSearchingRequest(request);
            }
        }
    }

    @Override
    public void saveOrUpdateSearchingRequest(SearchingRequest request) {
        dao.saveOrUpdateSearchingRequest(request);
    }

    @Override
    public SearchingRequest findByCriterions(List<Criterion> criterions) {
        return dao.findByCriterions(criterions);
    }

}
