package com.redlongcitywork.easytourlite.saver;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.session.HotToursSession;
import com.redlongcitywork.easytourlite.responseitem.HotToursResponseItem;
import com.redlongcitywork.easytourlite.service.HotToursRequestService;
import com.redlongcitywork.easytourlite.service.HotToursSessionService;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
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
    private TimeUtils utils;

    @Override
    public void save(HotToursResponseItem item, Saver.saveCallback callback) {
        if (item == null) {
            return;
        }
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        
        HotToursRequest request = item.getRequest();
        if(request == null){
            return;
        }
        request.setRequestTime(utils.getCurrentTime());
        requestService.saveOrUpdateHotToursRequest(request);
        HotToursSession session = new HotToursSession();
        session.setRequest(item.getRequest());
        session.getToursSet().addAll(item.getAnswer());
        session.setTime(utils.getCurrentTime());
        try {
            service.saveHotToursSession(session);
            callback.onSaved();
            LOG.log(Level.INFO, "HotToursSession saved succesfully");
        } catch (HibernateException e) {
            LOG.log(Level.WARNING, e.getMessage());
            callback.onNotSaved();
        }
    }

}
