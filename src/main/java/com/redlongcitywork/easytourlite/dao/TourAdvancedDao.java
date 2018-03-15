package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import java.util.List;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author redlongcity 03/03/2018
 */
public interface TourAdvancedDao {

    List<TourAdvanced> findAll();

    TourAdvanced findByKey(String key);

    void save(TourAdvanced tour);

    void mergeTourAdvanced(TourAdvanced tour);

    void deleteTourAdvanced(TourAdvanced tour);

    void saveOrUpdateTourAdvanced(TourAdvanced tour);

    List<TourAdvanced> findByCriteries(List<Criterion> criteries);

    List<TourAdvanced> findByPrices(Integer priceFrom, Integer priceTill, Currency currency);

    List<TourAdvanced> findByPricesAndCriterions(
            Integer priceFrom,
            Integer priceTill,
            Currency currency,
            List<Criterion> criterions);

}
