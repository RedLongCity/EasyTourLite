package com.redlongcitywork.easytourlite.pull.response;

import com.redlongcitywork.easytourlite.constants.AppConstants;
import com.redlongcitywork.easytourlite.model.ResponseItem;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 14/12/2017 class for storaging response elements
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
        if (pull != null) {
            Iterator<ResponseItem> it = pull.iterator();
            while (it.hasNext()) {
                ResponseItem item = it.next();
                if (item.getRequest().equals(request)) {
                    return item;
                }
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
            item = new ResponseItem();
        } else {
            Iterator<ResponseItem> it = pull.iterator();
            while (it.hasNext()) {
                ResponseItem inside = it.next();

                if (inside.isImmune() && inside.getFreezeTime().after(timeUtils.getCurrentTime())) {//if inside command is immune to changes
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
        }
        if (item != null) {
            pull.remove(item);
            item.setRequest(request);
            item.setImmune(true);
            pull.add(item);
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

    public List<ResponseItem> getItemsForSave() {
        List<ResponseItem> result = null;
        if (pull != null && !pull.isEmpty()) {
            result = new LinkedList<>(pull);
            Collections.sort(result, new Comparator<ResponseItem>() {
                @Override
                public int compare(ResponseItem o1, ResponseItem o2) {
                    if (o1.getPriority() > o2.getPriority()) {
                        return -1;
                    }
                    if (o1.getPriority() == o2.getPriority()) {
                        return 0;
                    } else {
                        return 1;
                    }
                }
            });
        }
        return result;
    }

    public void deleteResponseItem(ResponseItem item) {
        pull.remove(item);
    }

}
