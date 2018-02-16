package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.TourCasualDao;
import com.redlongcitywork.easytourlite.model.TourCasual;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity 16/02/2018
 */
@Service("tourCasualService")
@Transactional
public class TourCasualServiceImpl implements TourCasualService {
    
    @Autowired
    TourCasualDao dao;
    
    @Override
    public TourCasual findById(String id) {
        return dao.findById(id);
    }
    
    @Override
    public void saveTourCasual(TourCasual tour) {
        dao.save(tour);
    }
    
    @Override
    public void updateTourCasual(TourCasual tour) {
        TourCasual entity = dao.findById(tour.getKey());
        if (entity != null) {
            entity.setType(tour.getType());
            entity.setMealType(tour.getMealType());
            entity.setRoomType(tour.getRoomType());
            entity.setDuration(tour.getDuration());
            entity.setDateFrom(tour.getDateFrom());
            entity.setCombined(tour.getCombined());
            entity.setCurrency(tour.getCurrency());
            entity.setPrices(tour.getPrices());
            entity.setCity(tour.getCity());
            entity.setTransportType(tour.getTransportType());
            dao.mergeTourCasual(entity);
        }
    }
    
    @Override
    public void deleteTourCasual(TourCasual tour) {
        dao.deleteTourCasual(tour);
    }
    
    @Override
    public List<TourCasual> findAll() {
        return dao.findAll();
    }
    
    @Override
    public void deleteAllCountries() {
        List<TourCasual> tourList = dao.findAll();
        if (tourList != null) {
            for (TourCasual tour : tourList) {
                dao.deleteTourCasual(tour);
            }
        }
    }
    
    @Override
    public void saveOrUpdateTourCasual(TourCasual tour) {
        dao.saveOrUpdateTourCasual(tour);
    }
}
