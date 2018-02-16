package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.TourCasual;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 *
 * @author redlongcity 16/02/2018
 */
@Repository("tourCasualDao")
public class TourCasualDaoImpl extends AbstractDao<String, TourCasual>
        implements TourCasualDao {

    @Override
    public List<TourCasual> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("key"));
        List<TourCasual> list = (List<TourCasual>) crit.list();
        if (list != null) {
            for (TourCasual tour : list) {
                Hibernate.initialize(tour.getCity());
                Hibernate.initialize(tour.getCurrency());
                Hibernate.initialize(tour.getMealType());
                Hibernate.initialize(tour.getPrices());
                Hibernate.initialize(tour.getType());
            }
        }
        return list;
    }

    @Override
    public TourCasual findById(String id) {
        TourCasual tour = getByKey(id);
        if (tour != null) {
            Hibernate.initialize(tour.getCity());
            Hibernate.initialize(tour.getCurrency());
            Hibernate.initialize(tour.getMealType());
            Hibernate.initialize(tour.getPrices());
            Hibernate.initialize(tour.getType());
        }
        return tour;
    }

    @Override
    public void save(TourCasual tour) {
        persist(tour);
    }

    @Override
    public void deleteTourCasual(TourCasual tour) {
        delete(tour);
    }

    @Override
    public void mergeTourCasual(TourCasual tour) {
        merge(tour);
    }

    @Override
    public void saveOrUpdateTourCasual(TourCasual tour) {
        saveOrUpdate(tour);
    }

}
