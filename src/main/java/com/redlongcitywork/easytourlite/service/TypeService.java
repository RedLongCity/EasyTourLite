package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.model.Type;
import java.util.List;

/**
 *
 * @author redlocity 14/02/2018
 */
public interface TypeService {

    Type findById(String id);

    void saveType(Type type);

    void updateType(Type type);

    void deleteType(Type type);

    List<Type> findAll();

    void deleteAllCountries();

    void saveOrUpdateType(Type type);

}
