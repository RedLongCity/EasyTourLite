package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.Type;
import java.util.List;

/**
 *
 * @author redlongcity 14/02/2018
 */
public interface TypeDao {

    List<Type> findAll();

    Type findById(String id);

    void save(Type type);

    void mergeType(Type type);

    void deleteType(Type type);

    void saveOrUpdateType(Type type);

}
