package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.Region;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 *
 * @author redlongcity 14/02/2018
 */
@Repository("regionDao")
public class RegionDaoImpl extends AbstractDao<String, Region>
        implements RegionDao {

    @Override
    public List<Region> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("name"));
        List<Region> list = crit.list();
        return list;
    }

    @Override
    public Region findById(String id) {
        return getByKey(id);
    }

    @Override
    public void save(Region region) {
        persist(region);
    }

    @Override
    public void mergeRegion(Region region) {
        merge(region);
    }

    @Override
    public void deleteRegion(Region region) {
        delete(region);
    }

    @Override
    public void saveOrUpdateRegion(Region region) {
        saveOrUpdate(region);
    }

}
