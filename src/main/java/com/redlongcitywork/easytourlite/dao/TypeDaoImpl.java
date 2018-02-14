package com.redlongcitywork.easytourlite.dao;

import com.redlongcitywork.easytourlite.model.Type;
import java.util.List;
import org.hibernate.Criteria;
import org.hibernate.criterion.Order;
import org.springframework.stereotype.Repository;

/**
 *
 * @author redlongcity 14/02/2018
 */
@Repository("typeDao")
public class TypeDaoImpl extends AbstractDao<String, Type> implements TypeDao {

    @Override
    public List<Type> findAll() {
        Criteria crit = createCriteria();
        crit.addOrder(Order.asc("name"));
        return (List<Type>) crit.list();
    }

    @Override
    public Type findById(String id) {
        return getByKey(id);
    }

    @Override
    public void save(Type type) {
        persist(type);
    }

    @Override
    public void mergeType(Type type) {
        merge(type);
    }

    @Override
    public void deleteType(Type type) {
        delete(type);
    }

    @Override
    public void saveOrUpdateType(Type type) {
        saveOrUpdate(type);
    }

}
