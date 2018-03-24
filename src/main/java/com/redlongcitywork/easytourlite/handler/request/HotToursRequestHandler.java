package com.redlongcitywork.easytourlite.handler.request;

import com.redlongcitywork.easytourlite.command.request.HotToursRequestCommand;
import com.redlongcitywork.easytourlite.command.request.RequestCommand;
import com.redlongcitywork.easytourlite.convertor.HotSearchConvertor;
import com.redlongcitywork.easytourlite.extractor.ToursExtractor;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.ResponseItem;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.model.TourResponse;
import com.redlongcitywork.easytourlite.pull.request.RequestPull;
import com.redlongcitywork.easytourlite.pull.response.ResponsePull;
import com.redlongcitywork.easytourlite.service.HotToursRequestService;
import com.redlongcitywork.easytourlite.service.TourService;
import com.redlongcitywork.easytourlite.utils.ComeBackUtils;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.util.List;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 14/12/2017 handler for Hot Tours Requests
 */
@Service
public class HotToursRequestHandler implements RequestHandler<TourResponse, HotToursRequest> {

    private final ToursExtractor extractor;

    private final TimeUtils utils;

    private final ResponsePull responsePull;

    private final RequestPull requestPull;

    private final ComeBackUtils comeBackUtils;

    private final HotToursRequestService requestService;

    private final TourService tourService;

    private final HotSearchConvertor convertor;

    public HotToursRequestHandler(ToursExtractor extractor, TimeUtils utils, ResponsePull responsePull, RequestPull requestPull, ComeBackUtils comeBackUtils, HotToursRequestService requestService, TourService tourService, HotSearchConvertor convertor) {
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
    public TourResponse execute(HotToursRequest request) {
        ResponseItem item = responsePull.getResponse(request);
        if (item != null && !item.isImmune()) {
            return new TourResponse(0, (List<Tour>) item.getAnswer(), request);
        }
        HotToursRequest entity = requestService.findByFields(request);
        if (entity != null) {
            if (entity.getRequestTime().after(utils.getRevelanceTime())) {
                List<Tour> list = tourService.findByCriterions(
                        convertor.getCriterionsByRequest(entity));
                return new TourResponse(0, list, request);
            }
        }
        RequestCommand command = new HotToursRequestCommand(request,
                utils.getCurrentTime());
        requestPull.handleCommand(command);
        return new TourResponse(comeBackUtils.calculate(command), null, null);
    }

}
