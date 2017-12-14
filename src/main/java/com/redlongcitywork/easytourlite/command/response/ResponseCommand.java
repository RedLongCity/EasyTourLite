package com.redlongcitywork.easytourlite.command.response;

import com.redlongcitywork.easytourlite.model.Answer;

/**
 *
 * @author redlongcity
 * 14/12/2017
 * command for creating response to client
 */

public interface ResponseCommand<T> {
    
    T getRequest();
    
    void setRequest(T request);
    
    Answer execute();
}
