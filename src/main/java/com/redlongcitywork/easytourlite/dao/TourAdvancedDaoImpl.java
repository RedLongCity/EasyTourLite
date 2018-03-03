package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.TourAdvanced;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 *
 * @author redlongcity 03/03/2018
 */
@Repository("tourAdvancedDao")
public class TourAdvancedDaoImpl extends AbstractDao<String, TourAdvanced>
        implements TourAdvancedDao {

    @Override
    public List<TourAdvanced> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("key"));
        List<TourAdvanced> list = crit.list();
        if (list != null) {
            for (TourAdvanced tour : list) {
                Hibernate.initialize(tour.getCountry());
                Hibernate.initialize(tour.getType());
                Hibernate.initialize(tour.getRegion());
                Hibernate.initialize(tour.getMealType());
                Hibernate.initialize(tour.getCurrency());
                Hibernate.initialize(tour.getPrices());
                Hibernate.initialize(tour.getCity());
                Hibernate.initialize(tour.getImages());
                Hibernate.initialize(tour.getFacilities());
            }
        }
        return list;
    }

    @Override
    public TourAdvanced findByKey(String key) {
        TourAdvanced tour = getByKey(key);
        if (tour != null) {
            Hibernate.initialize(tour.getCountry());
            Hibernate.initialize(tour.getType());
            Hibernate.initialize(tour.getRegion());
            Hibernate.initialize(tour.getMealType());
            Hibernate.initialize(tour.getCurrency());
            Hibernate.initialize(tour.getPrices());
            Hibernate.initialize(tour.getCity());
            Hibernate.initialize(tour.getImages());
            Hibernate.initialize(tour.getFacilities());
        }
        return tour;
    }

    @Override
    public void save(TourAdvanced tour) {
        persist(tour);
    }

    @Override
    public void mergeTourAdvanced(TourAdvanced tour) {
        merge(tour);
    }

    @Override
    public void deleteTourAdvanced(TourAdvanced tour) {
        delete(tour);
    }

    @Override
    public void saveOrUpdateTourAdvanced(TourAdvanced tour) {
        saveOrUpdate(tour);
    }

}
