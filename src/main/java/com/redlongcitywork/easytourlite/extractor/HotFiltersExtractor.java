package com.redlongcitywork.easytourlite.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import static com.redlongcitywork.easytourlite.utils.ItToursUrls.api_base_url;
import static com.redlongcitywork.easytourlite.utils.ItToursUrls.api_showcases;
import static com.redlongcitywork.easytourlite.utils.ItToursUrls.api_showcases_filters;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 15/12/2017
 * class for extracting Hot Tours Filters from outside database
 */
@Service
public class HotFiltersExtractor implements Extractor, ItToursUrls {

    @Override
    public void extract(HttpUtils.GetCallBack callBack) {
        try {
            HttpUtils.getJsonNodeFromUrl(api_base_url
                    + api_showcases
                    + api_showcases_filters,
                    new HttpUtils.GetCallBack() {
                @Override
                public void onDataReceived(JsonNode node) {
                    callBack.onDataReceived(node);
                }

                @Override
                public void onDataNotAwailable() {
                    callBack.onDataNotAwailable();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(HotFiltersExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
