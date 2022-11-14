package com.redlongcitywork.easytourlite.responseitem.factory;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.responseitem.HotToursResponseItem;
import com.redlongcitywork.easytourlite.responseitem.ResponseItem;

/**
 *
 * @author redlongcity
 * 14/12/2017
 * factory for creating concrete ResponseItem
 */
public class ResponseItemFactory {

    public ResponseItem getResponseItem(Object request) {
        ResponseItem item = null;

        if (request instanceof HotToursRequest) {
            item = new HotToursResponseItem();
        }

        return item;
    }
}
