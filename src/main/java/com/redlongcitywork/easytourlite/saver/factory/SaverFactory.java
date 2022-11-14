package com.redlongcitywork.easytourlite.saver.factory;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.responseitem.ResponseItem;
import com.redlongcitywork.easytourlite.saver.HotToursSaver;
import com.redlongcitywork.easytourlite.saver.Saver;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 24/12/2017
 * class for creating concrete implementation of Saver
 */
@Service
public class SaverFactory {

    public Saver getSaver(ResponseItem item) {
        Saver saver = null;

        if (item.getRequest() instanceof HotToursRequest) {
            saver = new HotToursSaver();
        }

        return saver;
    }

}
