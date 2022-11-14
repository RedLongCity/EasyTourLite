package com.redlongcitywork.easytourlite.utils;

import com.redlongcitywork.easytourlite.command.request.RequestCommand;
import com.redlongcitywork.easytourlite.pull.request.RequestPull;
import com.redlongcitywork.easytourlite.constants.AppConstants;
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
    private AppConstants constants;
    
    @Autowired
    private RequestPull pull;

    public long calculate(Object request){
        
        RequestCommand command = pull.getCommandByRequest(request);
        
        if(command == null){
            return constants.getShortUpdatingDelay();
        }
        
        if(command.isProcessed()){
            return 1500;
        }
        
        return constants.getShortUpdatingDelay();
    }
}
