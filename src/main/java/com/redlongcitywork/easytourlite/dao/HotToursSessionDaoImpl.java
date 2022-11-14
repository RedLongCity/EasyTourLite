package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.session.HotToursSession;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author redlongcity 24/12/2017
 */
@Repository("hotToursSessionDao")
public class HotToursSessionDaoImpl
        extends AbstractDao<Integer, HotToursSession>
        implements HotToursSessionDao {

    @Override
    public List<HotToursSession> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("id"));
        List<HotToursSession> sessionList = crit.list();
        if (sessionList != null) {
            for (HotToursSession session : sessionList) {
                Hibernate.initialize(session.getRequest());
                Hibernate.initialize(session.getToursSet());
            }
        }
        return sessionList;
    }

    @Override
    public HotToursSession findById(Integer id) {
        HotToursSession session = getByKey(id);
        if (session != null) {
            Hibernate.initialize(session.getRequest());
            Hibernate.initialize(session.getToursSet());
        }
        return session;
    }

    @Override
    public HotToursSession findByRequest(HotToursRequest request) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("request", request));
        HotToursSession session = (HotToursSession) crit.uniqueResult();
        if (session != null) {
            Hibernate.initialize(session.getRequest());
            Hibernate.initialize(session.getToursSet());
        }
        return session;
    }

    @Override
    public void save(HotToursSession session) {
        persist(session);
    }

    @Override
    public void mergeHotToursSession(HotToursSession session) {
        merge(session);
    }

    @Override
    public void deleteHotToursSession(HotToursSession session) {
        delete(session);
    }

    @Override
    public void saveOrUpdateHotToursSession(HotToursSession session) {
        saveOrUpdate(session);
    }

}
