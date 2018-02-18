package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.HotelFilter;
import java.util.List;

/**
 *
 * @author redlongcity 16/02/2018
 */
public interface HotelFilterDao {

    List<HotelFilter> findAll();

    HotelFilter findById(String id);

    void save(HotelFilter hotel);

    void mergeHotel(HotelFilter hotel);

    void deleteHotel(HotelFilter hotel);

    void saveOrUpdateHotel(HotelFilter hotel);

}
