package com.redlongcitywork.easytourlite.responseitem;

import com.fasterxml.jackson.databind.JsonNode;
import java.sql.Timestamp;

/**
 *
 * @author redlongcity
 * 13/12/2017
 * interface like foundation for items of response information
 */
public interface ResponseItem<T> {
    
    T getRequest();
    
    void setRequest(T request);
    
    JsonNode getNode();
    
    void setNode(JsonNode node);
    
    Timestamp getFreezeeTime();
    
    void setFreezeeTime(Timestamp freezeeTime);
    
    boolean isImmune();
    
    void setImmune(boolean immune);
    
    int getPriority();
    
    void setPriority(int priority);
    
    void setRevelance(Timestamp revelance);
    
    Timestamp getRevelance();
    
}
