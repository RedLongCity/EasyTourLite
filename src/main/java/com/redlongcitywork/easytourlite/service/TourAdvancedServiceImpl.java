package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.TourAdvancedDao;
import com.redlongcitywork.easytourlite.model.Currency;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity 03/03/2018
 */
@Service("tourAdvancedService")
@Transactional
public class TourAdvancedServiceImpl implements TourAdvancedService {

    @Autowired
    private TourAdvancedDao dao;

    @Override
    public TourAdvanced findByKey(String key) {
        return dao.findByKey(key);
    }

    @Override
    public void saveTourAdvanced(TourAdvanced tour) {
        dao.save(tour);
    }

    @Override
    public void updateTourAdvanced(TourAdvanced tour) {
        TourAdvanced entity = dao.findByKey(tour.getKey());
        if (entity != null) {
            entity.setCountry(tour.getCountry());
            entity.setType(tour.getType());
            entity.setRegion(tour.getRegion());
            entity.setHotelId(tour.getHotelId());
            entity.setHotelName(tour.getHotelName());
            entity.setMealType(tour.getMealType());
            entity.setAdultAmount(tour.getAdultAmount());
            entity.setChildAmount(tour.getChildAmount());
            entity.setAccomodation(tour.getAccomodation());
            entity.setRoomType(tour.getRoomType());
            entity.setDuration(tour.getDuration());
            entity.setDateFrom(tour.getDateFrom());
            entity.setCombined(tour.getCombined());
            entity.setCurrency(tour.getCurrency());
            entity.setPrices(tour.getPrices());
            entity.setCity(tour.getCity());
            entity.setTransportType(tour.getTransportType());
            entity.setImages(tour.getImages());
            entity.setRate(tour.getRate());
            entity.setReviewCount(tour.getReviewCount());
            entity.setFacilities(tour.getFacilities());
            entity.setRating(tour.getRating());
            dao.mergeTourAdvanced(entity);
        }
    }

    @Override
    public void deleteTourAdvanced(TourAdvanced tour) {
        dao.deleteTourAdvanced(tour);
    }

    @Override
    public List<TourAdvanced> findAll() {
        return dao.findAll();
    }

    @Override
    public void deleteAllTours() {
        List<TourAdvanced> list = dao.findAll();
        if (list != null) {
            for (TourAdvanced tour : list) {
                dao.deleteTourAdvanced(tour);
            }
        }
    }

    @Override
    public void saveOrUpdateTourAdvanced(TourAdvanced tour) {
        dao.saveOrUpdateTourAdvanced(tour);
    }

    @Override
    public List<TourAdvanced> findByCriterions(List<Criterion> criterions) {
        return dao.findByCriteries(criterions);
    }

    @Override
    public List<TourAdvanced> findByPrices(Integer priceFrom, Integer priceTill, Currency currency) {
        return dao.findByPrices(priceFrom, priceTill, currency);
    }

    @Override
    public List<TourAdvanced> findByPricesAndCriterions(Integer priceFrom, Integer priceTill, Currency currency, List<Criterion> criterions) {
        return dao.findByPricesAndCriterions(priceFrom, priceTill, currency, criterions);
    }

}
