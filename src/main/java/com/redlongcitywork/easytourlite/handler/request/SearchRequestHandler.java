package com.redlongcitywork.easytourlite.handler.request;

import com.redlongcitywork.easytourlite.command.request.TourAdvancedRequestCommand;
import com.redlongcitywork.easytourlite.convertor.SearchConvertor;
import com.redlongcitywork.easytourlite.extractor.TourAdvancedExtractor;
import com.redlongcitywork.easytourlite.model.ResponseItem;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.model.TourAdvancedResponse;
import com.redlongcitywork.easytourlite.model.TourAdvancedSession;
import com.redlongcitywork.easytourlite.pull.request.RequestPull;
import com.redlongcitywork.easytourlite.pull.response.ResponsePull;
import com.redlongcitywork.easytourlite.service.SearchingRequestService;
import com.redlongcitywork.easytourlite.service.TourAdvancedService;
import com.redlongcitywork.easytourlite.service.TourAdvancedSessionService;
import com.redlongcitywork.easytourlite.utils.ComeBackUtils;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 20/03/2018
 */
@Service
public class SearchRequestHandler implements RequestHandler<SearchingRequest, TourAdvancedResponse> {

    private final TourAdvancedExtractor extractor;

    private final TimeUtils utils;

    private final ResponsePull responsePull;

    private final RequestPull requestPull;

    private final ComeBackUtils comeBackUtils;

    private final SearchingRequestService requestService;

    private final TourAdvancedService tourService;

    private final SearchConvertor convertor;

    private final TourAdvancedSessionService sessionService;

    public SearchRequestHandler(
            TourAdvancedExtractor extractor,
            TimeUtils utils,
            ResponsePull responsePull,
            RequestPull requestPull,
            ComeBackUtils comeBackUtils,
            SearchingRequestService requestService,
            TourAdvancedService tourService,
            SearchConvertor convertor,
            TourAdvancedSessionService sessionService) {
        this.extractor = extractor;
        this.utils = utils;
        this.responsePull = responsePull;
        this.requestPull = requestPull;
        this.comeBackUtils = comeBackUtils;
        this.requestService = requestService;
        this.tourService = tourService;
        this.convertor = convertor;
        this.sessionService = sessionService;
    }

    @Override
    public TourAdvancedResponse execute(SearchingRequest request) {
        TourAdvancedResponse result = null;
        if (request != null) {
            Set<TourAdvanced> tours = extractor.extract(request);
            if (tours != null) {
                result = new TourAdvancedResponse(0, tours, request);
                SearchingRequest entity = (SearchingRequest) requestService
                        .findByCriterions(convertor.getRequestCriterions(request));
                if (entity != null) {
                    TourAdvancedSession session = sessionService.findByRequest(entity);
                    session.setTours(tours);
                    sessionService.updateTourAdvancedSession(session);
                    request.setRequestTime(utils.getCurrentTime());
                    requestService.updateSearchingRequest(request);

                } else {
                    request.setRequestTime(utils.getCurrentTime());
                    TourAdvancedSession session = new TourAdvancedSession(request, tours);
                    requestService.saveOrUpdateSearchingRequest(request);
                    sessionService.saveOrUpdateTourAdvancedSession(session);
                }
            }
        }
        return result;
    }

    @Override
    public TourAdvancedResponse handle(SearchingRequest request) {
        ResponseItem item = responsePull.getResponse(request);
        if (item != null && item.isImmune()) {
            return new TourAdvancedResponse(0, (Set<TourAdvanced>) item.getAnswer(), request);
        }
        SearchingRequest entity = (SearchingRequest) requestService
                .findByCriterions(convertor.getRequestCriterions(request));
        if (entity != null) {
            if (entity.getRequestTime().after(utils.getRevelanceTime())) {
                List<TourAdvanced> list = tourService.findByCriterions(
                        convertor.getCriterionsByRequest(request)
                );
                return new TourAdvancedResponse(0, new HashSet(list), request);
            }
        }
        TourAdvancedRequestCommand command = new TourAdvancedRequestCommand(request, utils.getCurrentTime());
        requestPull.handleCommand(command);
        return new TourAdvancedResponse(comeBackUtils.calculate(command), null, null);
    }

}
