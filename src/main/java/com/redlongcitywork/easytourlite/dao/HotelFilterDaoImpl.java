package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.HotelFilter;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.Hibernate;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 *
 * @author redlongcity 16/02/2018
 */
@Repository("hotelFilterDao")
public class HotelFilterDaoImpl extends AbstractDao<String, HotelFilter> implements HotelFilterDao {

    @Override
    public List<HotelFilter> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("name"));
        List<HotelFilter> list = crit.list();
        if (list != null) {
            for (HotelFilter hotel : list) {
                Hibernate.initialize(hotel.getRating());
                Hibernate.initialize(hotel.getRegion());
                Hibernate.initialize(hotel.getTypeSet());
            }
        }
        return list;
    }

    @Override
    public HotelFilter findById(String id) {
        HotelFilter hotel = getByKey(id);
        if (hotel != null) {
            Hibernate.initialize(hotel.getRating());
            Hibernate.initialize(hotel.getRegion());
            Hibernate.initialize(hotel.getTypeSet());
        }
        return hotel;
    }

    @Override
    public void save(HotelFilter hotel) {
        persist(hotel);
    }

    @Override
    public void mergeHotel(HotelFilter hotel) {
        merge(hotel);
    }

    @Override
    public void deleteHotel(HotelFilter hotel) {
        delete(hotel);
    }

    @Override
    public void saveOrUpdateHotel(HotelFilter hotel) {
        saveOrUpdate(hotel);
    }

}
