package com.redlongcitywork.easytourlite.command.request;

import com.redlongcitywork.easytourlite.responseitem.ResponseItem;
import java.sql.Timestamp;

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
    
    boolean isProcessed();
    
    void setProcessed(boolean processed);
    
    Timestamp getCreationTime();
    
    void setCreationTime(Timestamp creationTime);
    
}
