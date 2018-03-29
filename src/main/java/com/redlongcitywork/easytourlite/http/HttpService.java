package com.redlongcitywork.easytourlite.http;

import com.fasterxml.jackson.databind.JsonNode;

/**
 *
 * @author redlongcity 29/03/2018
 */
public interface HttpService {
    
    JsonNode getJsonNodeFromUrl(String url);

}
