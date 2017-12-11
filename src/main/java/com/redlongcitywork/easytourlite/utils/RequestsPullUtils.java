package com.redlongcitywork.easytourlite.utils;

import com.redlongcitywork.easytourlite.model.Request;
import com.redlongcitywork.easytourlite.command.request.RequestCommand;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * fundamental interface for manipulations with requestsPull
 */
public interface RequestsPullUtils {
    
    RequestCommand getNextCommand();
    
    RequestCommand getCommandByRequest(Request request);
    
    void addRequestCommandToPull(RequestCommand command);
    
    void clearRequestsPull();
    
    boolean isRequestInPull(Request request);
    
}
