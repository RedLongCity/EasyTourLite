package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import java.util.List;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author redlongcity 03/03/2018
 */
public interface TourAdvancedService {

    TourAdvanced findByKey(String key);

    void saveTourAdvanced(TourAdvanced tour);

    void updateTourAdvanced(TourAdvanced tour);

    void deleteTourAdvanced(TourAdvanced tour);

    List<TourAdvanced> findAll();

    void deleteAllTours();

    void saveOrUpdateTourAdvanced(TourAdvanced tour);

    List<TourAdvanced> findByCriterions(List<Criterion> criterions);

    List<TourAdvanced> findByPrices(Integer priceFrom, Integer priceTill, Currency currency);

    List<TourAdvanced> findByPricesAndCriterions(
            Integer priceFrom,
            Integer priceTill,
            Currency currency,
            List<Criterion> criterions);

}
