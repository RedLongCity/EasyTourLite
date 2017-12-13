package com.redlongcitywork.easytourlite.pull.request;

import com.redlongcitywork.easytourlite.requestcommand.RequestCommand;
import java.util.concurrent.CopyOnWriteArrayList;
import org.springframework.stereotype.Service;

/**
 * @author redlongcity
 * 13/12/2017
 * class for keeping and handling request elements
 */

@Service
public class RequestPull {
    
    
    
    private CopyOnWriteArrayList<RequestCommand> pull;
    
}
