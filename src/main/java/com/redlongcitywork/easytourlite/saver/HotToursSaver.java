package com.redlongcitywork.easytourlite.saver;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.ResponseItem;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.model.session.HotToursSession;
import com.redlongcitywork.easytourlite.service.HotToursRequestService;
import com.redlongcitywork.easytourlite.service.HotToursSessionService;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 24/12/2017 class for saving Hot Tours into database
 */
@Service
public class HotToursSaver implements Saver {

    private static final Logger LOG = Logger.getLogger(HotToursSaver.class.getName());

    private final HotToursSessionService service;

    private final HotToursRequestService requestService;

    private final HotToursSessionService sessionService;

    private final TimeUtils utils;

    public HotToursSaver(HotToursSessionService service, HotToursRequestService requestService, HotToursSessionService sessionService, TimeUtils utils) {
        this.service = service;
        this.requestService = requestService;
        this.sessionService = sessionService;
        this.utils = utils;
    }

    @Override
    public void save(ResponseItem item) {
        if (item != null) {
            HotToursRequest request = (HotToursRequest) item.getRequest();
            HotToursSession session = null;
            if (request == null) {
                request.setRequestTime(utils.getCurrentTime());
                requestService.saveHotToursRequest(request);
                session = new HotToursSession();
                session.setRequest(request);
                session.getToursSet().addAll((List<Tour>) item.getAnswer());
                service.saveOrUpdateHotToursSession(session);
            } else {
                session = sessionService.findByRequest(request);
                if (session != null) {
                    session.getToursSet().clear();
                    session.getToursSet().addAll((List<Tour>) item.getAnswer());
                    request.setRequestTime(utils.getCurrentTime());
                    requestService.updateHotToursRequest(request);

                }
            }
        }
    }

}
