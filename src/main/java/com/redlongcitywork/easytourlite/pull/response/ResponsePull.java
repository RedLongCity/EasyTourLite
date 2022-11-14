package com.redlongcitywork.easytourlite.pull.response;

import com.redlongcitywork.easytourlite.responseitem.ResponseItem;
import com.redlongcitywork.easytourlite.responseitem.factory.ResponseItemFactory;
import com.redlongcitywork.easytourlite.constants.AppConstants;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.util.Iterator;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 
 * 14/12/2017 
 * class for storaging response elements
 */
@Service
public class ResponsePull {

    private static final Logger LOG = Logger.getLogger(ResponsePull.class.getName());

    private CopyOnWriteArrayList<ResponseItem> pull;

    @Autowired
    private TimeUtils timeUtils;

    @Autowired
    private AppConstants constants;

    public ResponseItem getResponse(Object request) {
        if (pull == null) {
            return null;
        }

        Iterator<ResponseItem> it = pull.iterator();
        while (it.hasNext()) {
            ResponseItem item = it.next();
            if (item.getRequest().equals(request)) {
                return item;
            }
        }
        return null;
    }

    public ResponseItem getEmptyResponseItem(Object request) {
        if (pull == null) {
            pull = new CopyOnWriteArrayList<ResponseItem>();
        }

        ResponseItem item = null;

        if (pull.size() < constants.getResponsePullSize()) {
            item = new ResponseItemFactory().getResponseItem(request);
            item.setRequest(request);
            item.setImmune(true);
            pull.add(item);
            return item;
        } else {
            Iterator<ResponseItem> it = pull.iterator();
            while (it.hasNext()) {
                ResponseItem inside = it.next();

                if (inside.isImmune()) {//if inside command is immune to changes
                    continue;
                }

                if (inside.getFreezeeTime().after(timeUtils.getCurrentTime())) {//if inside command is still freezzee
                    continue;
                }

                if (item == null) {
                    item = inside;
                    continue;
                }

                if (item.getPriority() > inside.getPriority()) {
                    item = inside;
                    continue;
                }
            }

            if (item != null) {
                pull.remove(item);
                item = new ResponseItemFactory().getResponseItem(request);
                item.setRequest(request);
                item.setImmune(true);
                pull.add(item);
            }

        }

        return item;
    }

    public ResponseItem getItemForSave() {
        if (pull == null) {
            return null;
        }
        ResponseItem item = null;
        Iterator<ResponseItem> it = pull.iterator();
        while (it.hasNext()) {
            ResponseItem inside = it.next();

            if (item == null) {
                item = inside;
            }

            if (inside.getPriority() > item.getPriority()) {
                item = inside;
            }
        }
        return item;
    }

    public void deleteResponseItem(ResponseItem item) {
        pull.remove(item);
    }

}
