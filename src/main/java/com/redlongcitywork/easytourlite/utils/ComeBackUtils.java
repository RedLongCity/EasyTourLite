package com.redlongcitywork.easytourlite.utils;

import com.redlongcitywork.easytourlite.singletons.ProjectConsantsSingletone;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 14/09/2017
 * class for calculating callback delay
 */
@Service
public class ComeBackUtils {

    private static final Logger LOG = Logger.getLogger(ComeBackUtils.class.getName());

    @Autowired
    ProjectConsantsSingletone constants;

//    public long calculate(RequestCommand requestCommand){
//        if(requestCommand.getDone()){
//            return 0;
//        }
//        
//        if(requestCommand.isProcessed()){
//            return 1500;
//        }
//        
//        return 5000;
//        
//    }
}
