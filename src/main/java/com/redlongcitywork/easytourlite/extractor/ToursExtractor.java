package com.redlongcitywork.easytourlite.extractor;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.convertor.HotSearchConvertor;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.parsers.TourArrayNodeParser;
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import com.redlongcitywork.easytourlite.utils.ItToursUrls;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
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

    public ToursExtractor(HotSearchConvertor convertor, TourArrayNodeParser parser) {
        this.convertor = convertor;
        this.parser = parser;
    }

    @Override
    public List<Tour> extract(HotToursRequest request) {
        List<Tour> result = null;
        try {
            JsonNode node = HttpUtils.getJsonNodeFromUrl(convertor.getURLByRequest(request));
            if (node != null) {
                result = parser.parseNode(node);
            }
        } catch (IOException ex) {
            Logger.getLogger(ToursExtractor.class.getName()).log(Level.SEVERE, null, ex);

        }
        return result;
    }

}
