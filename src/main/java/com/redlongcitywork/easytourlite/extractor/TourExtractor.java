package com.redlongcitywork.easytourlite.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.parsers.TourNodeParser;
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import static com.redlongcitywork.easytourlite.utils.ItToursUrls.api_base_url;
import static com.redlongcitywork.easytourlite.utils.ItToursUrls.api_tour_info;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 03/01/2017 
 * class for extracting tour data by tour_key
 */
@Service
public class TourExtractor implements Extractor<Tour, String>, ItToursUrls {

    private static final Logger LOG = Logger.getLogger(TourExtractor.class.getName());

    @Autowired
    private TourNodeParser parser;

    @Override
    public Tour extract(String key) {
        Tour result = null;
        try {
            JsonNode node = HttpUtils.getJsonNodeFromUrl(api_base_url
                    + api_tour_info
                    + key);
            if (node != null) {
                result = parser.parseNode(node);
            }
        } catch (IOException ex) {
            Logger.getLogger(TourExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }
}
