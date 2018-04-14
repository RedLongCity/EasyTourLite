package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.convertor.SearchConvertor;
import com.redlongcitywork.easytourlite.model.Hotel_Rating;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import java.util.Collection;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
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

    @Override
    public SearchingRequest findByCriterions(List<Criterion> criterions) {
        SearchingRequest result = null;
        Criteria crit = createCriteria();
        if (criterions != null) {
            for (Criterion criterion : criterions) {
                crit.add(criterion);
            }
            result = (SearchingRequest) crit.uniqueResult();
            if (result != null) {
                Hibernate.initialize(result.getCity());
                Hibernate.initialize(result.getCountry());
                Hibernate.initialize(result.getCurrency());
            }
        }
        return result;
    }
}
