package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 *
 * @author redlongcity
 * 12/12/2017
 */

@Repository("hotToursRequestDao")
public class HotToursRequestDaoImpl extends AbstractDao<Integer, HotToursRequest> implements HotToursRequestDao {

    @Override
    public List<HotToursRequest> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("request_id"));
        List<HotToursRequest> list = crit.list();
        if (list != null) {
            for (HotToursRequest request : list) {
                Hibernate.initialize(request.getCountry());
                Hibernate.initialize(request.getFrom_Cities());
                Hibernate.initialize(request.getHotel_Rating());
                Hibernate.initialize(request.getMeal_Type());
            }
        }
        return list;
    }

    @Override
    public HotToursRequest findById(Integer id) {
        HotToursRequest request = getByKey(id);
        if (request != null) {
            Hibernate.initialize(request.getCountry());
            Hibernate.initialize(request.getFrom_Cities());
            Hibernate.initialize(request.getHotel_Rating());
            Hibernate.initialize(request.getMeal_Type());
        }
        return request;
    }

    @Override
    public void save(HotToursRequest request) {
        persist(request);
    }

    @Override
    public void mergeHotToursRequest(HotToursRequest request) {
        merge(request);
    }

    @Override
    public void deleteHotToursRequest(HotToursRequest request) {
        delete(request);
    }

}
