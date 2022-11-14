package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.MailAddress;
import java.util.List;

/**
 *
 * @author redlongcity
 * class like facade for manipulatings with MailAddress's objects
 */
public interface MailAddressService {
    
    MailAddress findById(Integer id);
    
    void saveMailAddress(MailAddress address);
    
    void updateMailAddress(MailAddress address);
    
    void deleteMailAddress(MailAddress address);
    
    List<MailAddress> findAll();
    
    void deleteAllMailAddresses();
    
}
