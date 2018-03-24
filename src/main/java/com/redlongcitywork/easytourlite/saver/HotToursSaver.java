package com.redlongcitywork.easytourlite.saver;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.ResponseItem;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.model.HotToursSession;
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

    private final HotToursRequestService requestService;

    private final HotToursSessionService sessionService;

    private final TimeUtils utils;

    public HotToursSaver(HotToursRequestService requestService,
            HotToursSessionService sessionService,
            TimeUtils utils) {
        this.requestService = requestService;
        this.sessionService = sessionService;
        this.utils = utils;
    }

    @Override
    public void save(ResponseItem item) {
        if (item != null) {
            HotToursRequest request = ((HotToursRequest) item.getRequest());
            HotToursRequest entity = requestService.findByFields(request);
            HotToursSession session = null;
            if (entity == null) {
                request.setRequestTime(utils.getCurrentTime());
                requestService.saveHotToursRequest(request);
                session = new HotToursSession();
                session.setRequest(request);
                session.getToursSet().addAll((List<Tour>) item.getAnswer());
                sessionService.saveHotToursSession(session);
            } else {
                session = sessionService.findByRequest(entity);
                if (session != null) {
                    session.getToursSet().clear();
                    session.getToursSet().addAll((List<Tour>) item.getAnswer());
                    sessionService.updateHotToursSession(session);
                    entity.setRequestTime(utils.getCurrentTime());
                    requestService.updateHotToursRequest(entity);

                }
            }
        }
    }

}
