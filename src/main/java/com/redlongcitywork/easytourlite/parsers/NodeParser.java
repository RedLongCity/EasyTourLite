package com.redlongcitywork.easytourlite.parsers;

import com.fasterxml.jackson.databind.node.ArrayNode;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * interface for series of parsers node
 */
public interface NodeParser {
    
    Boolean parseNode(ArrayNode arrayNode);
    
}
