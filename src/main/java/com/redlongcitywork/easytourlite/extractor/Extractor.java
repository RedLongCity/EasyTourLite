package com.redlongcitywork.easytourlite.extractor;

import com.redlongcitywork.easytourlite.utils.HttpUtils;

/**
 *
 * @author redlongcity
 * 14/12/2017
 * interaface for extracting data from outside resources
 */
public interface Extractor {
    
    void extract(HttpUtils.GetCallBack callBack);
}
