package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.TourDao;
import com.redlongcitywork.easytourlite.model.Tour;
import java.util.List;
import org.hibernate.criterion.Criterion;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author redlongcity
 */

@Service("tourService")
@Transactional
public class TourServiceImpl implements TourService {

    @Autowired
    TourDao dao;

    @Override
    public Tour findByKey(String key) {
        return dao.findByKey(key);
    }

    @Override
    public void saveTour(Tour tour) {
        dao.save(tour);
    }

    @Override
    public void saveOrUpdateTour(Tour tour) {
        dao.saveOrUpdateTour(tour);
    }
    
    @Override
    public void updateTour(Tour tour) {
        Tour entity = dao.findByKey(tour.getKey());
        if (entity != null) {
            entity.setType(tour.getType());
            entity.setCountry(tour.getCountry());
            entity.setRegion(tour.getRegion());
            entity.setHotel_id(tour.getHotel_id());
            entity.setHotel(tour.getHotel());
            entity.setHotel_Rating(tour.getHotel_Rating());
            entity.setMeal_Type(tour.getMeal_Type());
            entity.setRoom_Type(tour.getRoom_Type());
            entity.setAdult_Amount(tour.getAdult_Amount());
            entity.setChild_Amount(tour.getChild_Amount());
            entity.setAccomodation(tour.getAccomodation());
            entity.setDuration(tour.getDuration());
            entity.setDate_From(tour.getDate_From());
            entity.setDate_From_Unix(tour.getDate_From_Unix());
            entity.setCurrency_id(tour.getCurrency_id());
            entity.setCurrency_Symbol(tour.getCurrency_Symbol());
            entity.setPrices(tour.getPrices());
            entity.setPrice_Old(tour.getPrice_Old());
            entity.setPrice_Change_Percent(tour.getPrice_Change_Percent());
            entity.setFrom_Cities(tour.getFrom_Cities());
            entity.setFrom_City_Gen(tour.getFrom_City_Gen());
            entity.setTransport_Type(tour.getTransport_Type());
            entity.setHotel_ImageSet(tour.getHotel_ImageSet());
            dao.mergeTour(entity);
        }
    }

    @Override
    public void deleteTour(Tour tour) {
        dao.deleteTour(tour);
    }

    @Override
    public void deleteToursBeforeDate(Integer date) {
        List<Tour> tourList = dao.getToursBeforeDate(date);
        if (tourList != null) {
            for (Tour tour : tourList) {
                dao.deleteTour(tour);
            }
        }
    }

    @Override
    public void deleteToursBetweenDats(Integer dateFrom, Integer dateTill) {
        List<Tour> tourList = dao.getToursBetweenDates(dateFrom, dateTill);
        if (tourList != null) {
            for (Tour tour : tourList) {
                dao.deleteTour(tour);
            }
        }
    }

    @Override
    public List<Tour> findAll() {
        return dao.findAll();
    }

    @Override
    public List<Tour> findByCriterions(List<Criterion> list) {
        return dao.getToursByCriterions(list);
    }
    
    @Override
    public void deleteAllTours() {
        List<Tour> tourList = dao.findAll();
        if (tourList != null) {
            for (Tour tour : tourList) {
                dao.deleteTour(tour);
            }
        }
    }

}
