package com.redlongcitywork.easytourlite.service;

import com.redlongcitywork.easytourlite.dao.TypeDao;
import com.redlongcitywork.easytourlite.model.Type;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author redlongcity 14/02/2018
 */
@Service("typeService")
@Transactional
public class TypeServiceImpl implements TypeService {
    
    @Autowired
    private TypeDao dao;
    
    @Override
    public Type findById(String id) {
        return dao.findById(id);
    }
    
    @Override
    public void saveType(Type type) {
        dao.save(type);
    }
    
    @Override
    public void updateType(Type type) {
        Type entity = dao.findById(type.getId());
        if (entity != null) {
            entity.setName(type.getName());
            dao.mergeType(type);
        }
    }
    
    @Override
    public void deleteType(Type type) {
        dao.deleteType(type);
    }
    
    @Override
    public List<Type> findAll() {
        return dao.findAll();
    }
    
    @Override
    public void deleteAllCountries() {
        List<Type> list = dao.findAll();
        if (list != null) {
            for (Type type : list) {
                dao.deleteType(type);
            }
        }
    }
    
    @Override
    public void saveOrUpdateType(Type type) {
        dao.saveOrUpdateType(type);
    }
    
}
