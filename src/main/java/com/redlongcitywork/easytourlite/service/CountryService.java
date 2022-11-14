package com.redlongcitywork.easytourlite.service;

import java.util.List;
import com.redlongcitywork.easytourlite.model.Country;

/**
 *
 * @author redlongcity
 */

public interface CountryService {
    
    Country findById(String id);
    
    void saveCountry(Country country);
    
    void updateCountry(Country country);
    
    void deleteCountry(Country country);
    
    List<Country> findAll();
    
    void deleteAllCountries();
    
    void saveOrUpdateCountry(Country country);
    
}
