package com.redlongcitywork.easytourlite.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.convertor.SearchConvertor;
import com.redlongcitywork.easytourlite.http.HttpService;
import com.redlongcitywork.easytourlite.model.Hotel;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.parsers.HotelArrayNodeParser;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 02/03/2018
 */
@Service
public class HotelExtractor implements Extractor<List<Hotel>, SearchingRequest>, ItToursUrls {

    private static final Logger LOG = Logger.getLogger(HotelExtractor.class.getName());

    private final SearchConvertor converter;

    private final HotelArrayNodeParser parser;

    private final HttpService service;

    public HotelExtractor(SearchConvertor converter, HotelArrayNodeParser parser, HttpService service) {
        this.converter = converter;
        this.parser = parser;
        this.service = service;
    }

    @Override
    public List<Hotel> extract(SearchingRequest request) {
        List<Hotel> result = null;
        JsonNode node = service.getJsonNodeFromUrl(
                api_base_url
                + api_search_url
                + converter.getURLByRequest(request));
        if (node != null) {
            result = parser.parseNode(node);
        }
        return result;
    }

}
