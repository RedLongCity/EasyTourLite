package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.CountryDao;
import com.redlongcitywork.easytourlite.model.Country;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author redlongcity
 */
@Service("countryService")
@Transactional
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryDao countryDao;

    @Override
    public Country findById(String id) {
        return countryDao.findById(id);
    }

    @Override
    public void saveCountry(Country country) {
        countryDao.save(country);
    }

    @Override
    public void updateCountry(Country country) {
        Country entity = countryDao.findById(country.getId());
        if (entity != null) {
            entity.setName(country.getName());
            countryDao.mergeCountry(entity);
        }
    }

    @Override
    public void deleteCountry(Country country) {
        countryDao.deleteCountry(country);
    }

    @Override
    public List<Country> findAll() {
        return countryDao.findAll();
    }

    @Override
    public void deleteAllCountries() {
        List<Country> countryList = countryDao.findAll();
        if (countryList != null) {
            for (Country country : countryList) {
                countryDao.deleteCountry(country);
            }
        }
    }

    @Override
    public void saveOrUpdateCountry(Country country) {
        countryDao.saveOrUpdateCountry(country);
    }
    
}
