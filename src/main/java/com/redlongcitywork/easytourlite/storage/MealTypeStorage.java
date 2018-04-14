package com.redlongcitywork.easytourlite.storage;

import com.redlongcitywork.easytourlite.model.Meal_Type;
import com.redlongcitywork.easytourlite.service.Meal_TypeService;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 27/03/2018
 */
@Service
public class MealTypeStorage implements Storage<List<Meal_Type>, Meal_Type> {

    private final Meal_TypeService service;

    private List<Meal_Type> content;

    public MealTypeStorage(Meal_TypeService service) {
        this.service = service;
    }

    @Override
    public List<Meal_Type> getContent() {
        if (content == null) {
            updateStorage();
        }
        return content;
    }

    @Override
    public void updateStorage() {
        content = service.findAll();
    }

    @Override
    public Meal_Type findById(String id) {
        Meal_Type result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getName()
                    .equalsIgnoreCase(id))
                    .findFirst()
                    .get();
        }
        return result;
    }

    @Override
    public Meal_Type findByName(String name) {
        Meal_Type result = null;
        if (content != null) {
            result = content.stream().filter(obj -> obj.getName()
                    .equalsIgnoreCase(name))
                    .findFirst()
                    .get();
        }
        return result;
    }

}
