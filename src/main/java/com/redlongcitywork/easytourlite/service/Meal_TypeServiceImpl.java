package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.Meal_TypeDao;
import com.redlongcitywork.easytourlite.model.Meal_Type;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author redlongcity
 */
@Service("meal_TypeService")
@Transactional
public class Meal_TypeServiceImpl implements Meal_TypeService {

    @Autowired
    Meal_TypeDao dao;

    @Override
    public Meal_Type findById(String id) {
        return dao.findById(id);
    }

    @Override
    public Meal_Type findByName(String name) {
        return dao.findByName(name);
    }

    @Override
    public void saveMeal_Type(Meal_Type meal_Type) {
        dao.save(meal_Type);
    }

    @Override
    public void updateMeal_Type(Meal_Type meal_Type) {
        Meal_Type entity = dao.findById(meal_Type.getId());
        if (entity != null) {
            entity.setName(meal_Type.getName());
            entity.setName_Full(meal_Type.getName_Full());
            dao.mergeMeal_Type(entity);
        }
    }

    @Override
    public void deleteMeal_Type(Meal_Type meal_Type) {
        dao.deleteMeal_Type(meal_Type);
    }

    @Override
    public List<Meal_Type> findAll() {
        return dao.findAll();
    }

    @Override
    public void deleteAllMeal_Type() {
        List<Meal_Type> meal_TypeList = dao.findAll();
        if (meal_TypeList != null) {
            for (Meal_Type meal_Type : meal_TypeList) {
                dao.deleteMeal_Type(meal_Type);
            }
        }
    }

    @Override
    public void saveOrUpdateMeal_Type(Meal_Type type) {
        dao.saveOrUpdateMeal_Type(type);
    }

}
