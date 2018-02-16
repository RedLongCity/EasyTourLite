package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.HotelDao;
import com.redlongcitywork.easytourlite.model.Hotel;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity 16/02/2018
 */
@Service("hotelService")
@Transactional
public class HotelServiceImpl implements HotelService {

    @Autowired
    HotelDao hotelDao;

    @Override
    public Hotel findById(String id) {
        return hotelDao.findById(id);
    }

    @Override
    public void saveHotel(Hotel hotel) {
        hotelDao.save(hotel);
    }

    @Override
    public void updateHotel(Hotel hotel) {
        Hotel entity = hotelDao.findById(hotel.getId());
        if (entity != null) {
            entity.setName(hotel.getName());
            hotelDao.mergeHotel(entity);
        }
    }

    @Override
    public void deleteHotel(Hotel hotel) {
        hotelDao.deleteHotel(hotel);
    }

    @Override
    public List<Hotel> findAll() {
        return hotelDao.findAll();
    }

    @Override
    public void deleteAllCountries() {
        List<Hotel> hotelList = hotelDao.findAll();
        if (hotelList != null) {
            for (Hotel hotel : hotelList) {
                hotelDao.deleteHotel(hotel);
            }
        }
    }

    @Override
    public void saveOrUpdateHotel(Hotel hotel) {
        hotelDao.saveOrUpdateHotel(hotel);
    }

}
