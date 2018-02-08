package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.SearchingRequest;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 *
 * @author redlongcity 08/02/2018
 */
@Repository("searchingRequestDao")
public class SearchingRequestDaoImpl extends AbstractDao<Integer, SearchingRequest>
        implements SearchingRequestDao {

    @Override
    public List<SearchingRequest> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("id"));
        List<SearchingRequest> list = crit.list();
        if (list != null) {
            for (SearchingRequest request : list) {
                Hibernate.initialize(request.getCity());
                Hibernate.initialize(request.getCountry());
                Hibernate.initialize(request.getCurrency());
                Hibernate.initialize(request.getMealType());
                Hibernate.initialize(request.getRatingSet());
            }
        }
        return list;
    }

    @Override
    public SearchingRequest findById(Integer id) {
        SearchingRequest request = getByKey(id);
        if (request != null) {
            Hibernate.initialize(request.getCity());
            Hibernate.initialize(request.getCountry());
            Hibernate.initialize(request.getCurrency());
            Hibernate.initialize(request.getMealType());
            Hibernate.initialize(request.getRatingSet());
        }
        return request;
    }

    @Override
    public void saveSearchingRequest(SearchingRequest request) {
        persist(request);
    }

    @Override
    public void mergeSearchingRequest(SearchingRequest request) {
        merge(request);
    }

    @Override
    public void deleteSearchingRequest(SearchingRequest request) {
        delete(request);
    }

    @Override
    public void saveOrUpdateSearchingRequest(SearchingRequest request) {
        saveOrUpdate(request);
    }

}
