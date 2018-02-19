package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.Region;
import java.util.List;

/**
 *
 * @author redlongcity 14/02/2018
 */
public interface RegionDao {

    List<Region> findAll();

    Region findById(String id);

    void save(Region region);

    void mergeRegion(Region region);

    void deleteRegion(Region region);

    void saveOrUpdateRegion(Region region);

}
