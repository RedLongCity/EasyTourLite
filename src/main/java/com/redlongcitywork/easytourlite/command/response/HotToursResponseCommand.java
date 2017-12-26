package com.redlongcitywork.easytourlite.command.response;

import com.fasterxml.jackson.annotation.JsonView;
import com.redlongcitywork.easytourlite.command.request.HotToursRequestCommand;
import com.redlongcitywork.easytourlite.command.request.RequestCommand;
import com.redlongcitywork.easytourlite.json.view.TourView;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.model.TourResponse;
import com.redlongcitywork.easytourlite.model.session.HotToursSession;
import com.redlongcitywork.easytourlite.pull.request.RequestPull;
import com.redlongcitywork.easytourlite.pull.response.ResponsePull;
import com.redlongcitywork.easytourlite.responseitem.ResponseItem;
import com.redlongcitywork.easytourlite.service.HotToursRequestService;
import com.redlongcitywork.easytourlite.service.HotToursSessionService;
import com.redlongcitywork.easytourlite.utils.ComeBackUtils;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author redlongcity
 * 14/12/2017
 * class for getting answer for Hot Tours Requests
 */
public class HotToursResponseCommand implements
        ResponseCommand<HotToursRequest, TourResponse> {

    private static final Logger LOG = Logger.getLogger(HotToursResponseCommand.class.getName());

    private HotToursRequest request;

    @Autowired
    private ResponsePull responsePull;

    @Autowired
    private RequestPull requestPull;

    @Autowired
    private TimeUtils timeUtils;

    @Autowired
    private ComeBackUtils comeBackUtils;

    @Autowired
    private HotToursSessionService sessionService;

    @Autowired
    private HotToursRequestService requestService;

    public HotToursResponseCommand(HotToursRequest request) {
        this.request = request;
    }

    @Override
    public HotToursRequest getRequest() {
        return request;
    }

    @Override
    public void setRequest(HotToursRequest request) {
        this.request = request;
    }

    @Override
    public TourResponse execute() {
        if (request == null) {
            LOG.log(Level.WARNING, "Response Command: request null!");
            return null;
        }
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        HotToursRequest entity = requestService.findByFields(request);

        if (entity == null) {
            request.setRequestTime(timeUtils.getCurrentTime());
            requestService.saveHotToursRequest(request);
        } else {
            HotToursSession session = sessionService.findByRequest(entity);
            if (session != null) {
                if (session.getTime().after(timeUtils.getRevelanceTime())) {
                    List<Tour> list = new ArrayList<Tour>();
                    list.addAll(session.getToursSet());
                    return new TourResponse(0, list, request);
                }
            }
        }
        ResponseItem item = responsePull.getResponse(request);

        if (item != null && !item.isImmune()) {
            return new TourResponse(0, (List<Tour>) item.getAnswer(), request);
        }

        RequestCommand command = new HotToursRequestCommand(request,
                timeUtils.getCurrentTime());

        requestPull.handleCommand(command);

        return new TourResponse(comeBackUtils.calculate(command), null, null);
    }

}
