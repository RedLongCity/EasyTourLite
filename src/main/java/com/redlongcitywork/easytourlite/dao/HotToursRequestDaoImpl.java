package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
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
        crit.addOrder(Order.asc("id"));
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
    public HotToursRequest findByFields(HotToursRequest request) {
                Criteria crit = createCriteria();
        if(request.getCountry()!=null){
        crit.add(Restrictions.eq("country", 
                request.getCountry()));
        }else{
            crit.add(Restrictions.isNull("country"));
        }
        if(request.getFrom_Cities()!=null){
        crit.add(Restrictions.eq("from_Cities", 
                request.getFrom_Cities()));
        }else{
            crit.add(Restrictions.isNull("from_Cities"));
        }
        crit.add(Restrictions.eq("hotel_Rating",
                request.getHotel_Rating()));
        crit.add(Restrictions.eq("night_From", 
                request.getNight_From()));
        crit.add(Restrictions.eq("night_Till", 
                request.getNight_Till()));
        if(request.getMeal_Type()!=null){
        crit.add(Restrictions.eq("meal_Type", 
                request.getMeal_Type()));
        }else{
            crit.add(Restrictions.isNull("meal_Type"));
        }
        HotToursRequest entity = (HotToursRequest) crit.uniqueResult();
        if(entity!=null){
              Hibernate.initialize(entity.getCountry());
              Hibernate.initialize(entity.getFrom_Cities());
              Hibernate.initialize(entity.getHotel_Rating());
              Hibernate.initialize(entity.getMeal_Type());
        }
        return entity;
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

    @Override
    public void saveOrUpdateHotToursRequest(HotToursRequest request) {
        saveOrUpdate(request);
    }
    
}
