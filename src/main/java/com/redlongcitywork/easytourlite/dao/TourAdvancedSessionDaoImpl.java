package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvancedSession;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author redlongcity 23/03/2018
 */
@Repository("tourAdvancedSessionDao")
public class TourAdvancedSessionDaoImpl extends AbstractDao<Integer, TourAdvancedSession>
        implements TourAdvancedSessionDao {

    @Override
    public List<TourAdvancedSession> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("id"));
        List<TourAdvancedSession> list = crit.list();
        if (list != null) {
            for (TourAdvancedSession session : list) {
                Hibernate.initialize(session.getRequest());
                Hibernate.initialize(session.getTours());
            }
        }
        return list;
    }

    @Override
    public TourAdvancedSession findById(Integer id) {
        TourAdvancedSession session = getByKey(id);
        if (session != null) {
            Hibernate.initialize(session.getRequest());
            Hibernate.initialize(session.getTours());
        }
        return session;
    }

    @Override
    public TourAdvancedSession findByRequest(SearchingRequest request) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("request", request));
        TourAdvancedSession session = (TourAdvancedSession) crit.uniqueResult();
        if (session != null) {
            Hibernate.initialize(session.getRequest());
            Hibernate.initialize(session.getTours());
        }
        return session;
    }

    @Override
    public void save(TourAdvancedSession session) {
        persist(session);
    }

    @Override
    public void mergeTourAdvancedSession(TourAdvancedSession session) {
        merge(session);
    }

    @Override
    public void deleteTourAdvancedSession(TourAdvancedSession session) {
        delete(session);
    }

    @Override
    public void saveOrUpdateTourAdvancedSession(TourAdvancedSession session) {
        saveOrUpdate(session);
    }

}
