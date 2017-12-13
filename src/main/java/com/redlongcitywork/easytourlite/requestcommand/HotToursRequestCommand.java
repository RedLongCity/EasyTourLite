package com.redlongcitywork.easytourlite.requestcommand;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.responseitem.ResponseItem;
import java.util.Objects;

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

    public HotToursRequestCommand(HotToursRequest request, int priority) {
        this.request = request;
        this.priority = priority;
    }

    @Override
    public ResponseItem execute(HotToursRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
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
