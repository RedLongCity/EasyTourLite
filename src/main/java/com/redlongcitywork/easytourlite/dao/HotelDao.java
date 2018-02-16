package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.Hotel;
import java.util.List;

/**
 *
 * @author redlongcity 16/02/2018
 */
public interface HotelDao {

    List<Hotel> findAll();

    Hotel findById(String id);

    void save(Hotel hotel);

    void mergeHotel(Hotel hotel);

    void deleteHotel(Hotel hotel);

    void saveOrUpdateHotel(Hotel hotel);

}
