package com.redlongcitywork.easytourlite.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.converter.SearchConvertor;
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
public class HotelExtractor implements Extractor<List<Hotel>, SearchingRequest>, ItToursUrls {

    private static final Logger LOG = Logger.getLogger(HotelExtractor.class.getName());

    @Autowired
    private SearchConvertor converter;

    @Autowired
    private HotelArrayNodeParser parser;

    @Override
    public List<Hotel> extract(SearchingRequest request) {
        List<Hotel> result = null;
        try {
            JsonNode node = HttpUtils.getJsonNodeFromUrl(
                    api_base_url
                    + api_search_url
                    + converter.getURLByRequest(request));
            if (node != null) {
                result = parser.parseNode(node);
            }
        } catch (IOException ex) {
            Logger.getLogger(HotelExtractor.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

}
