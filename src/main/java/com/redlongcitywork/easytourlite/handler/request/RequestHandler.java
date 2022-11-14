package com.redlongcitywork.easytourlite.handler.request;

import com.redlongcitywork.easytourlite.command.response.ResponseCommand;


/**
 *
 * @author redlongcity
 * 14/12/2017
 * class for primary processing requests 
 */
public interface RequestHandler<T> {
    
    ResponseCommand handle(T request);
        
}
