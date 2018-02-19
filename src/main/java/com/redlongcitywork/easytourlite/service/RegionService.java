package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.Region;
import java.util.List;

/**
 *
 * @author redlongcity 14/02/2018
 */
public interface RegionService {

    Region findById(String id);

    void saveRegion(Region region);

    void updateRegion(Region region);

    void deleteRegion(Region region);

    List<Region> findAll();

    void deleteAllCountries();

    void saveOrUpdateRegion(Region region);

}
