package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.Currency;
import java.util.List;
/**
 *
 * @author redlongcity
 */

public interface CurrencyDao {
    
    List<Currency> findAll();
    
    Currency findById(String id);
    
    void save(Currency currency);
    
    void mergeCurrency(Currency currency);
    
    void deleteCurrency(Currency currency);
    
    void saveOrUpdateCurrency(Currency currency);
    
}
