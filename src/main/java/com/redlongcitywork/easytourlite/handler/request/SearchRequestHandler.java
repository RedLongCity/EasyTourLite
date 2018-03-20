package com.redlongcitywork.easytourlite.handler.request;

import com.redlongcitywork.easytourlite.convertor.SearchConvertor;
import com.redlongcitywork.easytourlite.extractor.TourAdvancedExtractor;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.model.TourAdvancedResponse;
import com.redlongcitywork.easytourlite.pull.request.RequestPull;
import com.redlongcitywork.easytourlite.pull.response.ResponsePull;
import com.redlongcitywork.easytourlite.responseitem.ResponseItem;
import com.redlongcitywork.easytourlite.service.SearchingRequestService;
import com.redlongcitywork.easytourlite.service.TourAdvancedService;
import com.redlongcitywork.easytourlite.utils.ComeBackUtils;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 20/03/2018
 */
@Service
public class SearchRequestHandler implements RequestHandler<TourAdvancedResponse, SearchingRequest> {

    private final TourAdvancedExtractor extractor;

    private final TimeUtils utils;

    private final ResponsePull responsePull;

    private final RequestPull requestPull;

    private final ComeBackUtils comeBackUtils;

    private final SearchingRequestService requestService;

    private final TourAdvancedService tourService;

    private final SearchConvertor convertor;

    public SearchRequestHandler(TourAdvancedExtractor extractor, TimeUtils utils, ResponsePull responsePull, RequestPull requestPull, ComeBackUtils comeBackUtils, SearchingRequestService requestService, TourAdvancedService tourService, SearchConvertor convertor) {
        this.extractor = extractor;
        this.utils = utils;
        this.responsePull = responsePull;
        this.requestPull = requestPull;
        this.comeBackUtils = comeBackUtils;
        this.requestService = requestService;
        this.tourService = tourService;
        this.convertor = convertor;
    }

    @Override
    public TourAdvancedResponse execute(SearchingRequest request) {
        ResponseItem item = responsePull.getResponse(request);
        if (item != null && item.isImmune()) {
            return new TourAdvancedResponse(0, (List<TourAdvanced>) item.getAnswer(), request);
        }
        SearchingRequest entity = requestService.findByCriterions(convertor.getRequestCriterions());
    }

}
