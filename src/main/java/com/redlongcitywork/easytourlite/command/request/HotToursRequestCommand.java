package com.redlongcitywork.easytourlite.command.request;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.converter.HotSearchConverter;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;

/**
 *
 * @author redlongcity 
 * 11/12/2017 
 * class for creating request fot Hot Tours
 */
public class HotToursRequestCommand implements RequestCommand<HotToursRequest> {

    private HotToursRequest request;

    private int priority;

    private boolean processed;

    private Timestamp creationTime;

    @Autowired
    private HotSearchConverter converter;

    public HotToursRequestCommand(HotToursRequest request, Timestamp creationTime) {
        this.request = request;
        this.creationTime = creationTime;
        this.priority = 0;
    }

    @Override
    public void execute(HttpUtils.GetCallBack callBack) {
        if (request == null) {
            return;
        }
        SpringBeanAutowiringSupport.processInjectionBasedOnCurrentContext(this);
        try {
            HttpUtils.getJsonNodeFromUrl(converter.getURLByRequest(request),
                    new HttpUtils.GetCallBack() {
                @Override
                public void onDataReceived(JsonNode node) {
                    callBack.onDataReceived(node);
                }

                @Override
                public void onDataNotAwailable() {
                    callBack.onDataNotAwailable();
                }
            });
        } catch (IOException ex) {
            Logger.getLogger(HotToursRequestCommand.class.getName()).log(Level.SEVERE, null, ex);
        }
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
    public int getPriority() {
        return priority;
    }

    @Override
    public void setPriority(int priority) {
        this.priority = priority;
    }

    @Override
    public boolean isProcessed() {
        return processed;
    }

    @Override
    public void setProcessed(boolean processed) {
        this.processed = processed;
    }

    @Override
    public Timestamp getCreationTime() {
        return creationTime;
    }

    @Override
    public void setCreationTime(Timestamp creationTime) {
        this.creationTime = creationTime;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.request);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final HotToursRequestCommand other = (HotToursRequestCommand) obj;
        if (!Objects.equals(this.request, other.request)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HotToursRequestCommand{" + "request=" + request + ", priority=" + priority + ", processed=" + processed + '}';
    }

}
