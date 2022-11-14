package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.MailAddress;
import java.util.List;

/**
 *
 * @author redlongcity
 * interface like fundament for Dao classes
 */

public interface MailAddressDao {
    
    List<MailAddress> findAll();
    
    MailAddress findById(Integer id);
    
    void saveMailAddress(MailAddress address);
    
    void mergeMailAddress(MailAddress address);
    
    void deleteMailAddress(MailAddress address);
    
}
