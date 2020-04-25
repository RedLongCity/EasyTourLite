package com.redlongcitywork.easytourlite.handler.request;

import com.redlongcitywork.easytourlite.command.request.HotToursRequestCommand;
import com.redlongcitywork.easytourlite.convertor.HotSearchConvertor;
import com.redlongcitywork.easytourlite.extractor.ToursExtractor;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.HotToursSession;
import com.redlongcitywork.easytourlite.model.ResponseItem;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.model.TourResponse;
import com.redlongcitywork.easytourlite.pull.request.RequestPull;
import com.redlongcitywork.easytourlite.pull.response.ResponsePull;
import com.redlongcitywork.easytourlite.service.HotToursRequestService;
import com.redlongcitywork.easytourlite.service.HotToursSessionService;
import com.redlongcitywork.easytourlite.service.TourService;
import com.redlongcitywork.easytourlite.utils.ComeBackUtils;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 14/12/2017 handler for Hot Tours Requests
 */
@Service
public class HotToursRequestHandler implements RequestHandler<HotToursRequest, TourResponse> {

    private final ToursExtractor extractor;

    private final TimeUtils utils;

    private final ResponsePull responsePull;

    private final RequestPull requestPull;

    private final ComeBackUtils comeBackUtils;

    private final HotToursRequestService requestService;

    private final TourService tourService;

    private final HotSearchConvertor convertor;

    private final HotToursSessionService sessionService;

    public HotToursRequestHandler(
            ToursExtractor extractor,
            TimeUtils utils,
            ResponsePull responsePull,
            RequestPull requestPull,
            ComeBackUtils comeBackUtils,
            HotToursRequestService requestService,
            TourService tourService,
            HotSearchConvertor convertor,
            HotToursSessionService sessionService) {
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
    public TourResponse execute(HotToursRequest request) {
        TourResponse result = null;
        if (request != null) {
            Set<Tour> tours = extractor.extract(request);
            if (tours != null) {
                result = new TourResponse(0, tours, request);
//                HotToursRequest entity = (HotToursRequest) requestService todo only for test
//                        .findByFields((request));
//                if (entity != null) {
//                    HotToursSession session = sessionService.findByRequest(entity);
//                    session.setToursSet(tours);
//                    sessionService.updateHotToursSession(session);
//                    request.setRequestTime(utils.getCurrentTime());
//                    requestService.updateHotToursRequest(request);
//
//                } else {
//                    request.setRequestTime(utils.getCurrentTime());
//                    HotToursSession session = new HotToursSession(request, tours);
//                    requestService.saveOrUpdateHotToursRequest(request);
//                    sessionService.saveOrUpdateHotToursSession(session);
//                } todo only for test
            }
        }
        return result;
    }

    @Override
    public TourResponse handle(HotToursRequest request) {
        ResponseItem item = responsePull.getResponse(request);
        if (item != null
//                && item.isImmune() todo only for test
        ) {
            return new TourResponse(0, ((TourResponse) item.getAnswer()).getTours(), request);
        }
        HotToursRequest entity = null;
//        HotToursRequest entity = (HotToursRequest) requestService todo only for test
//                .findByFields(request);
        if (entity != null) {
            if (entity.getRequestTime().after(utils.getRevelanceTime())) {
                List<Tour> list = tourService.findByCriterions(
                        convertor.getCriterionsByRequest(request)
                );
                return new TourResponse(0, new HashSet(list), request);
            }
        }
        HotToursRequestCommand command = new HotToursRequestCommand(request, utils.getCurrentTime());
        requestPull.handleCommand(command);
        return new TourResponse(comeBackUtils.calculate(command), null, null);
    }
}
