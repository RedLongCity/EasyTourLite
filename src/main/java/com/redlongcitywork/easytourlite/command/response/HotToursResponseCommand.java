package com.redlongcitywork.easytourlite.command.response;

import com.redlongcitywork.easytourlite.command.request.HotToursRequestCommand;
import com.redlongcitywork.easytourlite.command.request.RequestCommand;
import com.redlongcitywork.easytourlite.model.Answer;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.pull.request.RequestPull;
import com.redlongcitywork.easytourlite.pull.response.ResponsePull;
import com.redlongcitywork.easytourlite.responseitem.ResponseItem;
import com.redlongcitywork.easytourlite.utils.ComeBackUtils;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author redlongcity
 * 14/12/2017
 * class for getting answer for Hot Tours Requests
 */
public class HotToursResponseCommand implements ResponseCommand<HotToursRequest> {

    private static final Logger LOG = Logger.getLogger(HotToursResponseCommand.class.getName());

    private HotToursRequest request;

    @Autowired
    private ResponsePull responsePull;

    @Autowired
    private RequestPull requestPull;

    @Autowired
    private TimeUtils timeUtils;

    @Autowired
    private ComeBackUtils comeBackUtils;

    public HotToursResponseCommand(HotToursRequest request) {
        this.request = request;
    }

    @Override
    public HotToursRequest getRequest() {
        return request;
    }

    @Override
    public void setRequest(HotToursRequest request) {
        this.request = request;
    }

    @Override
    public Answer execute() {
        if (request == null) {
            LOG.log(Level.WARNING, "Response Command: request null!");
            return null;
        }
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);

        ResponseItem item = responsePull.getResponse(request);

        if (item != null) {
            return new Answer(0, item.getNode());
        }

        RequestCommand command = new HotToursRequestCommand(request,
                timeUtils.getCurrentTime());

        requestPull.handleCommand(command);

        return new Answer(comeBackUtils.calculate(command), null);
    }

}
