package com.redlongcitywork.easytourlite.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.convertor.HotSearchConvertor;
import com.redlongcitywork.easytourlite.http.HttpService;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.parsers.TourArrayNodeParser;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 20/03/2018
 */
@Service
public class ToursExtractor implements Extractor<List<Tour>, HotToursRequest>, ItToursUrls {

    private static final Logger LOG = Logger.getLogger(ToursExtractor.class.getName());

    private final HotSearchConvertor convertor;

    private final TourArrayNodeParser parser;

    private final HttpService service;

    public ToursExtractor(HotSearchConvertor convertor, TourArrayNodeParser parser, HttpService service) {
        this.convertor = convertor;
        this.parser = parser;
        this.service = service;
    }

    @Override
    public List<Tour> extract(HotToursRequest request) {
        List<Tour> result = null;
        JsonNode node = service.getJsonNodeFromUrl(convertor.getURLByRequest(request));
        if (node != null) {
            result = parser.parseNode(node);
        }
        return result;
    }

}
