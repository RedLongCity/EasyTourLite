package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.Tour;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Order;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Repository;

/**
 *
 * @author redlongcity
 */
@Repository("tourDao")
public class TourDaoImpl extends AbstractDao<String, Tour> implements TourDao {

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
                Hibernate.initialize(tour.getSessions());
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
                Hibernate.initialize(tour.getSessions());
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
                Hibernate.initialize(tour.getSessions());
            }
        }
        return tourList;
    }

    @Override
    public Tour findByKey(String key) {
        Tour tour = getByKey(key);
        if (tour != null) {
            Hibernate.initialize(tour.getCountry());
            Hibernate.initialize(tour.getFrom_Cities());
            Hibernate.initialize(tour.getPrices());
            Hibernate.initialize(tour.getHotel_ImageSet());
            Hibernate.initialize(tour.getHotel_Rating());
            Hibernate.initialize(tour.getMeal_Type());
            Hibernate.initialize(tour.getSessions());
        }
        return tour;
    }

    @Override
    public List<Tour> getToursByCriterions(List<Criterion> list) {
        Criteria crit = createCriteria();
        if (list != null) {
            for (Criterion criterion : list) {
                crit.add(criterion);
            }
        }
        crit.setMaxResults(100);
        List<Tour> tourList = (List<Tour>) crit.list();
        if (tourList != null) {
            for (Tour tour : tourList) {
                Hibernate.initialize(tour.getCountry());
                Hibernate.initialize(tour.getFrom_Cities());
                Hibernate.initialize(tour.getPrices());
                Hibernate.initialize(tour.getHotel_Rating());
                Hibernate.initialize(tour.getHotel_ImageSet());
                Hibernate.initialize(tour.getMeal_Type());
                Hibernate.initialize(tour.getSessions());
            }
        }
        return tourList;
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

    @Override
    public void saveOrUpdateTour(Tour tour) {
        saveOrUpdate(tour);
    }

}
