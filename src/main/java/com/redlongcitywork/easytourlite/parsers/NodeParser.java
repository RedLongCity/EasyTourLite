package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * interface for series of parsers node
 */
public interface NodeParser<T> {
    
    T parseNode(JsonNode arrayNode);
    
}
