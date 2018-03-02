package com.redlongcitywork.easytourlite.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.converter.SearchConverter;
import com.redlongcitywork.easytourlite.model.Hotel;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.parsers.HotelArrayNodeParser;
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 02/03/2018
 */
@Service
public class HotelExtractor implements ItToursUrls {

    private static final Logger LOG = Logger.getLogger(HotelExtractor.class.getName());

    @Autowired
    private SearchConverter converter;
    
    @Autowired
    private HotelArrayNodeParser parser;

    public void extract(SearchingRequest request, HttpUtils.GetCallBack<List<Hotel>> callBack) {
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
