package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.CurrencyDao;
import com.redlongcitywork.easytourlite.model.Currency;
import java.util.List;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity
 */

@Service("currencyService")
@Transactional
public class CurrencyServiceImpl implements CurrencyService {

    @Autowired
    CurrencyDao dao;
    
    @Override
    public Currency findById(String id) {
        return dao.findById(id);
    }

    @Override
    public void saveCurrency(Currency currency) {
        dao.save(currency);
    }

    @Override
    public void updateCurrency(Currency currency) {
        Currency entity = dao.findById(currency.getId());
        if(entity!=null){
            entity.setName(currency.getName());
            dao.mergeCurrency(entity);
    }
    }

    @Override
    public void deleteCurrency(Currency currency) {
        dao.deleteCurrency(currency);
    }

    @Override
    public List<Currency> findAll() {
        return dao.findAll();
    }

    @Override
    public void deleteAllCurrency() {
        List<Currency> currencyList = dao.findAll();
        if(currencyList!=null){
            for(Currency currency: currencyList){
                dao.deleteCurrency(currency);
            }
        }
    }

    @Override
    public void saveOrUpdateCurrency(Currency currency) {
        dao.saveOrUpdateCurrency(currency);
    }
    
}
