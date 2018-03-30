package com.redlongcitywork.easytourlite.http;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 29/03/2018
 */
@Service
@Profile("test")
public class TestHttpService implements HttpService {

    private final static String[] searchParams = {"module", "params"};
    private final static String[] searchFilters = {"module", "params", "entity"};
    private final static String[] searchHotels = {"module", "search?"};
    private final static String[] searchTours = {"module", "search-list?"};
    private final static String[] hotFilters = {"showcase", "hot-offers"};
    private final static String[] hotTours = {"showcase", "hot-offers", "search?"};

    @Override
    public JsonNode getJsonNodeFromUrl(String url) {
        int length = 0;
        JsonNode result = null;

        if (contains(url, searchParams) && length < searchParams.length) {
            result = getJsonFromFile("json/miner_search_params.json");
            length = searchParams.length;
        }
        if (contains(url, searchFilters) && length < searchFilters.length) {
            result = getJsonFromFile("json/miner_search_filters.json");
            length = searchFilters.length;
        }
        if (contains(url, searchHotels) && length < searchHotels.length) {
            result = getJsonFromFile("json/extractor_hotels.json");
            length = searchHotels.length;
        }
        if (contains(url, searchTours) && length < searchTours.length) {
            result = getJsonFromFile("json/extractor_advanced.json");
            length = searchTours.length;
        }
        if (contains(url, hotFilters) && length < hotFilters.length) {
            result = getJsonFromFile("json/miner_hot_filters.json");
            length = hotFilters.length;
        }
        if (contains(url, hotTours) && length < hotTours.length) {
            result = getJsonFromFile("json/extractor_hot_tours.json");
            length = hotTours.length;
        }
        return result;
    }

    private JsonNode getJsonFromFile(String path) {
        JsonNode result = null;
        File file = new File(getClass().getClassLoader().getResource(path).getFile());
        ObjectMapper mapper = new ObjectMapper();
        try {
            result = mapper.readTree(file);
        } catch (IOException ex) {
            Logger.getLogger(TestHttpService.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    private boolean contains(String url, String... args) {
        for (String string : args) {
            if (!url.contains(string)) {
                return false;
            }
        }
        return true;
    }

}
