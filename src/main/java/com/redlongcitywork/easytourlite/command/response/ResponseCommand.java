package com.redlongcitywork.easytourlite.command.response;


/**
 *
 * @author redlongcity
 * 14/12/2017
 * command for creating response to client
 */

public interface ResponseCommand<Request, Answer> {
    
    Request getRequest();
    
    void setRequest(Request request);
    
    Answer execute();
}
