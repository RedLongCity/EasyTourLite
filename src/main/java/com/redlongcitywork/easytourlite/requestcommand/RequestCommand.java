package com.redlongcitywork.easytourlite.requestcommand;

/**
 *
 * @author redlongcity
 * 11/12/2017
 * interface for unification Requests Command
 */
public interface RequestCommand<T> {
    
    void execute(T request);
    
}
