package com.redlongcitywork.easytourlite.requestcommand;

import com.redlongcitywork.easytourlite.model.ResponseItem;

/**
 *
 * @author redlongcity
 * 11/12/2017
 * interface for unification Requests Command
 */
public interface RequestCommand<T> {
    
    ResponseItem execute(T request);
    
    int getPriority();
    
    void setPriority(int priority);
    
    T getRequest();
    
    void setRequest(T request);
    
}
