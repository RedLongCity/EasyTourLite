package com.redlongcitywork.easytourlite.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 03/01/2017
 * class for extracting tour data by tour_key
 */
@Service
public class TourExtractor implements ItToursUrls {

    private static final Logger LOG = Logger.getLogger(TourExtractor.class.getName());

    public void extract(String key, HttpUtils.GetCallBack callback) {
        try{
        HttpUtils.getJsonNodeFromUrl(api_base_url
                + api_tour_info
                + key,
                new HttpUtils.GetCallBack() {
            @Override
            public void onDataReceived(final JsonNode node) {
                callback.onDataReceived(node);
                return;
            }

            @Override
            public void onDataNotAwailable() {
                callback.onDataNotAwailable();
                return;
            }
        });
        }catch (IOException ex) {
            Logger.getLogger(HotFiltersExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
