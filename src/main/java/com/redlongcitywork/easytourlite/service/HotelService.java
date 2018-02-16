package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.Hotel;
import java.util.List;

/**
 *
 * @author redlongcity 16/02/2018
 */
public interface HotelService {

    Hotel findById(String id);

    void saveHotel(Hotel hotel);

    void updateHotel(Hotel hotel);

    void deleteHotel(Hotel hotel);

    List<Hotel> findAll();

    void deleteAllCountries();

    void saveOrUpdateHotel(Hotel hotel);

}
