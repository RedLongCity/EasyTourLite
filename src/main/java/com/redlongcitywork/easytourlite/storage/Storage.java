package com.redlongcitywork.easytourlite.storage;

/**
 *
 * @author redlongcity 27/03/2018
 */
public interface Storage<Collection, Element> {

    Collection getContent();

    void updateStorage();

    Element findById(String id);

    Element findByName(String name);
}
