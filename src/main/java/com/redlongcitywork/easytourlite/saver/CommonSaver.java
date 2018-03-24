package com.redlongcitywork.easytourlite.saver;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.ResponseItem;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.util.logging.Logger;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 24/03/2018
 */
@Service
public class CommonSaver implements Saver {
    
    private static final Logger LOG = Logger.getLogger(CommonSaver.class.getName());
    
    private final HotToursSaver hotSaver;
    
    private final TourAdvancedSaver advancedSaver;
    
    private final TimeUtils utils;
    
    public CommonSaver(
            HotToursSaver hotSaver,
            TourAdvancedSaver advancedSaver,
            TimeUtils utils) {
        this.hotSaver = hotSaver;
        this.advancedSaver = advancedSaver;
        this.utils = utils;
    }
    
    @Override
    public void save(ResponseItem item) {
        if (item != null) {
            if(item.getRequest() instanceof HotToursRequest){
               hotSaver.save(item);
            }
            if(item.getRequest() instanceof SearchingRequest){
                advancedSaver.save(item);
            }
        } else {
            LOG.warning("CommonSaver item is null");
        }
    }
    
}
