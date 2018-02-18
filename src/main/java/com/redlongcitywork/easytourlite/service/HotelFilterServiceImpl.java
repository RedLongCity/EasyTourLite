package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.HotelFilter;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.redlongcitywork.easytourlite.dao.HotelFilterDao;

/**
 *
 * @author redlongcity 16/02/2018
 */
@Service("hotelService")
@Transactional
public class HotelFilterServiceImpl implements HotelFilterService {

    @Autowired
    HotelFilterDao hotelDao;

    @Override
    public HotelFilter findById(String id) {
        return hotelDao.findById(id);
    }

    @Override
    public void saveHotel(HotelFilter hotel) {
        hotelDao.save(hotel);
    }

    @Override
    public void updateHotel(HotelFilter hotel) {
        HotelFilter entity = hotelDao.findById(hotel.getId());
        if (entity != null) {
            entity.setName(hotel.getName());
            hotelDao.mergeHotel(entity);
        }
    }

    @Override
    public void deleteHotel(HotelFilter hotel) {
        hotelDao.deleteHotel(hotel);
    }

    @Override
    public List<HotelFilter> findAll() {
        return hotelDao.findAll();
    }

    @Override
    public void deleteAllCountries() {
        List<HotelFilter> hotelList = hotelDao.findAll();
        if (hotelList != null) {
            for (HotelFilter hotel : hotelList) {
                hotelDao.deleteHotel(hotel);
            }
        }
    }

    @Override
    public void saveOrUpdateHotel(HotelFilter hotel) {
        hotelDao.saveOrUpdateHotel(hotel);
    }

}
