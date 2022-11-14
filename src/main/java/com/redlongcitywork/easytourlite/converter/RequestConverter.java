package com.redlongcitywork.easytourlite.converter;

import java.util.List;
import org.hibernate.criterion.Criterion;

/**
 *
 * @author redlongcity
 * 09.09.2017
 * interface for handling Request
 */
public interface RequestConverter<T> {
    
    List<Criterion> getCriterionsByRequest(T request);
    
    String getURLByRequest(T request);
    
}
