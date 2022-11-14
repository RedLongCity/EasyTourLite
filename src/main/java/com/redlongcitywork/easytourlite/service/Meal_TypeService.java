package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.Meal_Type;
import java.util.List;
/**
 *
 * @author redlongcity
 */
public interface Meal_TypeService {
    
    Meal_Type findById(String id);
    
    Meal_Type findByName(String name);
    
    void saveMeal_Type(Meal_Type meal_Type);
    
    void updateMeal_Type(Meal_Type meal_Type);
    
    void deleteMeal_Type(Meal_Type meal_Type);
    
    List<Meal_Type> findAll();
    
    void deleteAllMeal_Type();
    
    void saveOrUpdateMeal_Type(Meal_Type type);
    
}
