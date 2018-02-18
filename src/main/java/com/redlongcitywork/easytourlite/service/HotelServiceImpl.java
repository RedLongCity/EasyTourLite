package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.HotelDao;
import com.redlongcitywork.easytourlite.model.Hotel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity 18/02/2018
 */
@Service("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {

    @Autowired
    private HotelDao dao;

    @Override
    public Hotel findById(Integer id) {
        return dao.findById(id);
    }

    @Override
    public void saveHotel(Hotel hotel) {
        dao.save(hotel);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        Hotel entity = dao.findById(hotel.getId());
        if (entity != null) {
            entity.setCountry(hotel.getCountry());
            entity.setRegion(hotel.getRegion());
            entity.setHotelName(hotel.getHotelName());
            entity.setHotelReviewRate(hotel.getHotelReviewRate());
            entity.setHotelReviewCount(hotel.getHotelReviewCount());
            entity.setFacilities(hotel.getFacilities());
            entity.setLat(hotel.getLat());
            entity.setLng(hotel.getLng());
            entity.setWifiFree(hotel.getWifiFree());
            entity.setImages(hotel.getImages());
            entity.setRating(hotel.getRating());
            entity.setAdultAmount(hotel.getAdultAmount());
            entity.setChildAmount(hotel.getChildAmount());
            entity.setAccomodation(hotel.getAccomodation());
            entity.setTours(hotel.getTours());
            dao.mergeHotel(entity);
        }
    }

    @Override
    public void deleteHotel(Hotel hotel) {
        dao.deleteHotel(hotel);
    }

    @Override
    public List<Hotel> findAll() {
        return dao.findAll();
    }

    @Override
    public void deleteAllCountries() {
        List<Hotel> hotelList = dao.findAll();
        if (hotelList != null) {
            for (Hotel hotel : hotelList) {
                dao.deleteHotel(hotel);
            }
        }
    }

    @Override
    public void saveOrUpdateHotel(Hotel hotel) {
        dao.saveOrUpdateHotel(hotel);
    }

}
