package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.From_Cities;
import java.util.List;
/**
 *
 * @author redlongcity
 */

public interface From_CitiesService {
    
    From_Cities findById(String id);
    
    void saveFrom_Cities(From_Cities from_Cities);
    
    void updateFrom_Cities(From_Cities from_Cities);
    
    void deleteFrom_Cities(From_Cities from_Cities);
    
    List<From_Cities> findAll();
    
    void deleteAllFrom_Cities();
    
    void saveOrUpdateFrom_Cities(From_Cities city);
    
}
