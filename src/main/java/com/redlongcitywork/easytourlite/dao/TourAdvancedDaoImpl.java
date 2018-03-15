package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.converter.SearchConverter;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Criterion;
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
                Hibernate.initialize(tour.getRating());
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
            Hibernate.initialize(tour.getRating());
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

    @Override
    public List<TourAdvanced> findByCriteries(List<Criterion> criteries) {
        Criteria crit = createCriteria();
        if (criteries != null) {
            for (Criterion criterion : criteries) {
                crit.add(criterion);
            }
        }
        crit.setMaxResults(100);
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
                Hibernate.initialize(tour.getRating());
            }
        }
        return list;
    }

    @Override
    public List<TourAdvanced> findByPrices(Integer priceFrom, Integer priceTill, Currency currency) {
        Criteria crit = createCriteria();
        List<TourAdvanced> list = null;
        if (currency != null && (priceFrom != null || priceTill != null)) {
            SearchConverter.addPriceCriteria(crit, priceFrom, priceTill, currency);
            list = crit.list();
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
                    Hibernate.initialize(tour.getRating());
                }
            }
        }
        return list;
    }

    @Override
    public List<TourAdvanced> findByPricesAndCriterions(
            Integer priceFrom,
            Integer priceTill,
            Currency currency,
            List<Criterion> criterions) {
        Criteria crit = createCriteria();
        List<TourAdvanced> list = null;
        if (currency != null && (priceFrom != null || priceTill != null)) {
            SearchConverter.addPriceCriteria(crit, priceFrom, priceTill, currency);
            if (criterions != null) {
                for (Criterion criterion : criterions) {
                    crit.add(criterion);
                }
            }
            list = crit.list();
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
                    Hibernate.initialize(tour.getRating());
                }
            }
        }
        return list;
    }

}
