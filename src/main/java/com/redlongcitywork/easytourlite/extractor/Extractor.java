package com.redlongcitywork.easytourlite.extractor;

/**
 *
 * @author redlongcity
 * 14/12/2017
 * interface for extracting data from outside resources
 */
public interface Extractor<T,Request> {
    
    T extract(Request request);
}
