package com.redlongcitywork.easytourlite.saver;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.Tour;
import com.redlongcitywork.easytourlite.model.session.HotToursSession;
import com.redlongcitywork.easytourlite.responseitem.HotToursResponseItem;
import com.redlongcitywork.easytourlite.service.HotToursRequestService;
import com.redlongcitywork.easytourlite.service.HotToursSessionService;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.hibernate.HibernateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author redlongcity
 * 24/12/2017
 * class for saving Hot Tours into database
 */
public class HotToursSaver implements Saver<HotToursResponseItem> {

    private static final Logger LOG = Logger.getLogger(HotToursSaver.class.getName());

    @Autowired
    private HotToursSessionService service;

    @Autowired
    private HotToursRequestService requestService;

    @Autowired
    private HotToursSessionService sessionService;

    @Autowired
    private TimeUtils utils;

    @Override
    public void save(HotToursResponseItem item, Saver.saveCallback callback) {
        if (item == null) {
            return;
        }
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        HotToursRequest request = item.getRequest();
        if (request == null) {
            return;
        }
        HotToursRequest entity = requestService.findByFields(request);
        if (entity == null) {
            request.setRequestTime(utils.getCurrentTime());
            requestService.saveHotToursRequest(request);
            HotToursSession session = new HotToursSession();
            session.setRequest(request);
            session.getToursSet().addAll(item.getAnswer());
            try {
                service.saveOrUpdateHotToursSession(session);
                callback.onSaved();
                LOG.log(Level.INFO, "HotToursSession saved succesfully");
            } catch (HibernateException e) {
                LOG.log(Level.WARNING, e.getMessage());
                callback.onNotSaved();
            }
        } else {
            HotToursSession session = sessionService.findByRequest(entity);
            if (session == null) {
                LOG.log(Level.WARNING, "Session is null");
                callback.onNotSaved();
            }
            Set<Tour> set = session.getToursSet();
            if (set != null) {
                session.getToursSet().clear();
                session.getToursSet().addAll(item.getAnswer());
            } else {
                LOG.log(Level.WARNING, "Tour set is null!");
                callback.onNotSaved();
                return;
            }
            try {
                service.saveOrUpdateHotToursSession(session);
                entity.setRequestTime(utils.getCurrentTime());
                requestService.updateHotToursRequest(entity);
                callback.onSaved();
            } catch (HibernateException e) {
                LOG.log(Level.WARNING, e.getMessage());
                callback.onNotSaved();
            }

        }

    }

}
