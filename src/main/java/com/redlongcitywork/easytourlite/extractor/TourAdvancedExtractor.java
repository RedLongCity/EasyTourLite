package com.redlongcitywork.easytourlite.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.converter.SearchConverter;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.parsers.TourAdvancedArrayNodeParser;
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 04/03/2018
 */
@Service
public class TourAdvancedExtractor implements ItToursUrls {

    private static final Logger LOG = Logger.getLogger(TourAdvancedExtractor.class.getName());

    private final SearchConverter converter;

    private final TourAdvancedArrayNodeParser parser;

    public TourAdvancedExtractor(SearchConverter converter, TourAdvancedArrayNodeParser parser) {
        this.converter = converter;
        this.parser = parser;
    }

    public void extract(SearchingRequest request, HttpUtils.GetCallBack<List<TourAdvanced>> callBack) {
        try {
            HttpUtils.getJsonNodeFromUrl(converter.getURLByRequest(request),
                    new HttpUtils.GetCallBack<JsonNode>() {
                @Override
                public void onDataReceived(JsonNode node) {
                    callBack.onDataReceived(parser.parseNode(node));
                }

                @Override
                public void onDataNotAwailable() {
                    callBack.onDataNotAwailable();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(HotelExtractor.class.getName()).log(Level.SEVERE, null, ex);

        }
    }

}
