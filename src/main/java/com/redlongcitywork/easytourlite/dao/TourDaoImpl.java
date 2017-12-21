package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.Tour;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;
/**
 *
 * @author redlongcity
 */

@Repository("tourDao")
public class TourDaoImpl extends AbstractDao<Integer, Tour> implements TourDao {

    @Override
    public List<Tour> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("country"));
        List<Tour> tourList = (List<Tour>) crit.list();
        crit.setMaxResults(100);
        if (tourList != null) {
            for (Tour tour : tourList) {
                Hibernate.initialize(tour.getCountry());
                Hibernate.initialize(tour.getFrom_Cities());
                Hibernate.initialize(tour.getPrices());
                Hibernate.initialize(tour.getHotel_Rating());
                Hibernate.initialize(tour.getHotel_ImageSet());
                Hibernate.initialize(tour.getMeal_Type());
            }
        }
        return tourList;
    }

    @Override
    public List<Tour> getToursBeforeDate(Integer date) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.le("date_From_Unix", date));
        crit.setMaxResults(100);
        List<Tour> tourList = crit.list();
        if (tourList != null) {
            for (Tour tour : tourList) {
                Hibernate.initialize(tour.getCountry());
                Hibernate.initialize(tour.getFrom_Cities());
                Hibernate.initialize(tour.getPrices());
                Hibernate.initialize(tour.getHotel_Rating());
                Hibernate.initialize(tour.getHotel_ImageSet());
                Hibernate.initialize(tour.getMeal_Type());
            }
        }
        return tourList;
    }

    @Override
    public List<Tour> getToursBetweenDates(Integer dateBefore, Integer dateTill) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.between("date_From_Unix", dateBefore, dateTill));
        crit.setMaxResults(100);
        List<Tour> tourList = crit.list();
        if (tourList != null) {
            for (Tour tour : tourList) {
                Hibernate.initialize(tour.getCountry());
                Hibernate.initialize(tour.getFrom_Cities());
                Hibernate.initialize(tour.getPrices());
                Hibernate.initialize(tour.getHotel_Rating());
                Hibernate.initialize(tour.getHotel_ImageSet());
                Hibernate.initialize(tour.getMeal_Type());
            }
        }
        return tourList;
    }

    @Override
    public Tour findById(Integer id) {
        Tour tour = getByKey(id);
        if (tour != null) {
            Hibernate.initialize(tour.getCountry());
            Hibernate.initialize(tour.getFrom_Cities());
            Hibernate.initialize(tour.getPrices());
            Hibernate.initialize(tour.getHotel_ImageSet());
            Hibernate.initialize(tour.getHotel_Rating());
            Hibernate.initialize(tour.getMeal_Type());

        }
        return tour;
    }

    @Override
    public Tour findByKey(String key) {
        Criteria crit = createCriteria();
        crit.add(Restrictions.eq("key", key));
        Tour tour = (Tour) crit.uniqueResult();
        if (tour != null) {
            Hibernate.initialize(tour.getCountry());
            Hibernate.initialize(tour.getFrom_Cities());
            Hibernate.initialize(tour.getPrices());
            Hibernate.initialize(tour.getHotel_ImageSet());
            Hibernate.initialize(tour.getHotel_Rating());
            Hibernate.initialize(tour.getMeal_Type());
        }
        return tour;
    }

    @Override
    public void save(Tour tour) {
        persist(tour);
    }

    @Override
    public void mergeTour(Tour tour) {
        merge(tour);
    }

    @Override
    public void deleteTour(Tour tour) {
        delete(tour);
    }

}
