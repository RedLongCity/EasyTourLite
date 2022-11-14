package com.redlongcitywork.easytourlite.handler.request;

import com.redlongcitywork.easytourlite.command.response.HotToursResponseCommand;
import com.redlongcitywork.easytourlite.command.response.ResponseCommand;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 14/12/2017
 * handler for Hot Tours Requests
 */
@Service
public class HotToursRequestHandler implements RequestHandler<HotToursRequest> {

    @Override
    public ResponseCommand handle(HotToursRequest request) {
        return new HotToursResponseCommand(request);
    }
    
}
