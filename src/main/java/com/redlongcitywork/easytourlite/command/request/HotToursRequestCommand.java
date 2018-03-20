package com.redlongcitywork.easytourlite.command.request;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import java.sql.Timestamp;
import java.util.Objects;

/**
 *
 * @author redlongcity 
 * 11/12/2017 
 * class for creating request fot Hot Tours
 */
public class HotToursRequestCommand implements RequestCommand<HotToursRequest> {

    private final HotToursRequest request;

    private int priority;

    private boolean processed;

    private Timestamp creationTime;

    public HotToursRequestCommand(HotToursRequest request, Timestamp creationTime) {
        this.request = request;
        this.creationTime = creationTime;
    }

    @Override
    public HotToursRequest getRequest() {
        return request;
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
