package com.redlongcitywork.easytourlite.saver;

import com.redlongcitywork.easytourlite.convertor.SearchConvertor;
import com.redlongcitywork.easytourlite.model.ResponseItem;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.model.TourAdvancedSession;
import com.redlongcitywork.easytourlite.service.SearchingRequestService;
import com.redlongcitywork.easytourlite.service.TourAdvancedSessionService;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.util.List;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 22/03/2018
 */
@Service
public class TourAdvancedSaver implements Saver {

    private static final Logger LOG = Logger.getLogger(TourAdvancedSaver.class.getName());

    private final TourAdvancedSessionService sessionService;

    private final SearchingRequestService requestService;

    private final TimeUtils utils;

    public TourAdvancedSaver(
            TourAdvancedSessionService sessionService,
            SearchingRequestService requestService,
            TimeUtils utils) {
        this.sessionService = sessionService;
        this.requestService = requestService;
        this.utils = utils;
    }

    @Override
    public void save(ResponseItem item) {
        if (item != null) {
            SearchingRequest request = (SearchingRequest) item.getRequest();
            SearchingRequest entity = requestService.
                    findByCriterions(SearchConvertor.getRequestCriterions(request));
            TourAdvancedSession session = null;
            if (entity == null) {
                request.setRequestTime(utils.getCurrentTime());
                requestService.saveSearchingRequest(request);
                session = new TourAdvancedSession();
                session.setRequest(request);
                session.getTours().addAll((List<TourAdvanced>) item.getAnswer());
                sessionService.saveTourAdvancedSession(session);
            } else {
                session = sessionService.findByRequest(entity);
                if (session != null) {
                    session.getTours().clear();
                    session.getTours().addAll((List<TourAdvanced>) item.getAnswer());
                    sessionService.updateTourAdvancedSession(session);
                    entity.setRequestTime(utils.getCurrentTime());
                    requestService.updateSearchingRequest(entity);
                }
            }
        }
    }

}
