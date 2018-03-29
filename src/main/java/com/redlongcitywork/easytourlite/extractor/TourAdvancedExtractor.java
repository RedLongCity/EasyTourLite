package com.redlongcitywork.easytourlite.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.convertor.SearchConvertor;
import com.redlongcitywork.easytourlite.http.HttpService;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.parsers.TourAdvancedArrayNodeParser;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 04/03/2018
 */
@Service
public class TourAdvancedExtractor implements Extractor<List<TourAdvanced>, SearchingRequest>, ItToursUrls {

    private static final Logger LOG = Logger.getLogger(TourAdvancedExtractor.class.getName());

    private final SearchConvertor convertor;

    private final TourAdvancedArrayNodeParser parser;

    private final HttpService service;

    public TourAdvancedExtractor(SearchConvertor convertor, TourAdvancedArrayNodeParser parser, HttpService service) {
        this.convertor = convertor;
        this.parser = parser;
        this.service = service;
    }

    @Override
    public List<TourAdvanced> extract(SearchingRequest request) {
        List<TourAdvanced> result = null;
        JsonNode node = service.getJsonNodeFromUrl(convertor.getURLByRequest(request));
        if (node != null) {
            result = parser.parseNode(node);
        }
        return result;
    }

}
