package com.redlongcitywork.easytourlite.saver;

import com.redlongcitywork.easytourlite.model.ResponseItem;

/**
 *
 * @author redlongcity 24/12/2017 
 * interface like fundament for next savers of
 * tours
 */
public interface Saver {

    void save(ResponseItem item);
}
