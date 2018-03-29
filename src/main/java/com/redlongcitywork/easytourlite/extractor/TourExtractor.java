package com.redlongcitywork.easytourlite.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.http.HttpService;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.parsers.TourNodeParser;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import static com.redlongcitywork.easytourlite.utils.ItToursUrls.api_base_url;
import static com.redlongcitywork.easytourlite.utils.ItToursUrls.api_tour_info;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 03/01/2017 class for extracting tour data by tour_key
 */
@Service
public class TourExtractor implements Extractor<Tour, String>, ItToursUrls {

    private static final Logger LOG = Logger.getLogger(TourExtractor.class.getName());

    private final TourNodeParser parser;

    private final HttpService service;

    public TourExtractor(TourNodeParser parser, HttpService service) {
        this.parser = parser;
        this.service = service;
    }

    @Override
    public Tour extract(String key) {
        Tour result = null;
        JsonNode node = service.getJsonNodeFromUrl(api_base_url
                + api_tour_info
                + key);
        if (node != null) {
            result = parser.parseNode(node);
        }
        return result;
    }
}
