package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.Hotel;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 *
 * @author redlongcity 18/02/2018
 */
@Repository("hotelDao")
public class HotelDaoImpl extends AbstractDao<Integer, Hotel> implements HotelDao {

    @Override
    public List<Hotel> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("hotelName"));
        List<Hotel> list = (List<Hotel>) crit.list();
        if (list != null) {
            for (Hotel hotel : list) {
                Hibernate.initialize(hotel.getCountry());
                Hibernate.initialize(hotel.getRegion());
                Hibernate.initialize(hotel.getFacilities());
                Hibernate.initialize(hotel.getImages());
                Hibernate.initialize(hotel.getRating());
                Hibernate.initialize(hotel.getTours());
            }
        }
        return list;
    }

    @Override
    public Hotel findById(Integer id) {
        Hotel hotel = getByKey(id);
        if (hotel != null) {
            Hibernate.initialize(hotel.getCountry());
            Hibernate.initialize(hotel.getRegion());
            Hibernate.initialize(hotel.getFacilities());
            Hibernate.initialize(hotel.getImages());
            Hibernate.initialize(hotel.getRating());
            Hibernate.initialize(hotel.getTours());
        }
        return hotel;
    }

    @Override
    public void save(Hotel hotel) {
        persist(hotel);
    }

    @Override
    public void deleteHotel(Hotel hotel) {
        delete(hotel);
    }

    @Override
    public void mergeHotel(Hotel hotel) {
        merge(hotel);
    }

    @Override
    public void saveOrUpdateHotel(Hotel hotel) {
        saveOrUpdate(hotel);
    }
}
