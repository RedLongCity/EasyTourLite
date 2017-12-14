package com.redlongcitywork.easytourlite.pull.response;

import com.redlongcitywork.easytourlite.requestcommand.RequestCommand;
import com.redlongcitywork.easytourlite.responseitem.ResponseItem;
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
    
    public ResponseItem getResponse(RequestCommand command) {
        if (pull == null) {
            return null;
        }
        
        Iterator<ResponseItem> it = pull.iterator();
        while (it.hasNext()) {
            ResponseItem item = it.next();
            if (item.getRequest().equals(command.getRequest())) {
                return item;
            }
        }
        return null;
    }
    
    public ResponseItem getEmptyResponseItem() {
        if (pull == null) {
            return null;
        }
        
        ResponseItem item = null;
        
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
            item.setImmune(true);
            item.setNode(null);
        }
        
        return item;
    }
    
}
