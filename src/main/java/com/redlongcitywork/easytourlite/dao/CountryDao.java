package com.redlongcitywork.easytourlite.dao;

import java.util.List;
import com.redlongcitywork.easytourlite.model.Country;

/**
 *
 * @author redlongcity
 */

public interface CountryDao {
    
    List<Country> findAll();
    
    Country findById(String id);
    
    void save(Country country);
    
    void mergeCountry(Country country);
    
    void deleteCountry(Country country);
    
    void saveOrUpdateCountry(Country country);
}
