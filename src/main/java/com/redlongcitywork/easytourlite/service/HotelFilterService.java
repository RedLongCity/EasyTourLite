package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.HotelFilter;
import java.util.List;

/**
 *
 * @author redlongcity 16/02/2018
 */
public interface HotelFilterService {

    HotelFilter findById(String id);

    void saveHotel(HotelFilter hotel);

    void updateHotel(HotelFilter hotel);

    void deleteHotel(HotelFilter hotel);

    List<HotelFilter> findAll();

    void deleteAllCountries();

    void saveOrUpdateHotel(HotelFilter hotel);

}
