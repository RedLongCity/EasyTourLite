package com.redlongcitywork.easytourlite.handler.request;

import com.redlongcitywork.easytourlite.command.request.HotToursRequestCommand;
import com.redlongcitywork.easytourlite.command.request.RequestCommand;
import com.redlongcitywork.easytourlite.command.request.TourAdvancedRequestCommand;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.ResponseItem;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvancedResponse;
import com.redlongcitywork.easytourlite.model.TourResponse;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity 22/03/2018
 */
@Service
public class CommonRequestHandler implements RequestHandler<ResponseItem, RequestCommand> {

    private final SearchRequestHandler searchHandler;

    private final HotToursRequestHandler hotHandler;

    private final TimeUtils utils;

    public CommonRequestHandler(SearchRequestHandler searchHandler, HotToursRequestHandler hotHandler, TimeUtils utils) {
        this.searchHandler = searchHandler;
        this.hotHandler = hotHandler;
        this.utils = utils;
    }

    @Override
    public ResponseItem execute(RequestCommand command) {
        ResponseItem result = null;
        Object answer = null;
        if (command instanceof HotToursRequestCommand) {
            TourResponse response
                    = hotHandler.execute((HotToursRequest) command.getRequest());
            if (response != null && response.getComeBackDelay() == 0) {
                answer = response;
            }
        }

        if (command instanceof TourAdvancedRequestCommand) {
            TourAdvancedResponse response
                    = searchHandler.execute((SearchingRequest) command.getRequest());
            if (response != null && response.getComeBackDelay() == 0) {
                answer = response;
            }
        }

        if (answer != null) {
            result = new ResponseItem(
                    command.getRequest(),
                    answer,
                    utils.getCurrentTime(),
                    utils.getFreezeeTime(),
                    utils.getActualTime(),
                    false,
                    0
            );
        }
        return result;
    }

}
