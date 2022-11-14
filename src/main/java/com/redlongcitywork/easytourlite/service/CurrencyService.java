package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.Currency;
import java.util.List;
/**
 *
 * @author redlongcity
 */

public interface CurrencyService {
    
    Currency findById(String id);
    
    void saveCurrency(Currency currency);
    
    void updateCurrency(Currency currency);
    
    void deleteCurrency(Currency currency);
    
    List<Currency> findAll();
    
    void deleteAllCurrency();
    
    void saveOrUpdateCurrency(Currency currency);
    
}
