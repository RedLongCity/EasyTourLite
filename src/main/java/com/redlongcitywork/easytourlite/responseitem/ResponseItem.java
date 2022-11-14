package com.redlongcitywork.easytourlite.responseitem;

import java.sql.Timestamp;

/**
 *
 * @author redlongcity
 * 13/12/2017
 * interface like foundation for items of response information
 */
public interface ResponseItem<Request, Answer> {
    
    Request getRequest();
    
    void setRequest(Request request);
    
    Answer getAnswer();
    
    void setAnswer(Answer node);
    
    Timestamp getFreezeeTime();
    
    void setFreezeeTime(Timestamp freezeeTime);
    
    boolean isImmune();
    
    void setImmune(boolean immune);
    
    int getPriority();
    
    void setPriority(int priority);
    
    void setRevelance(Timestamp revelance);
    
    Timestamp getRevelance();
    
}
