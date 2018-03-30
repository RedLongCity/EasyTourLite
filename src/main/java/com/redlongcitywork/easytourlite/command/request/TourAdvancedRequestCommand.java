package com.redlongcitywork.easytourlite.command.request;

import com.redlongcitywork.easytourlite.model.SearchingRequest;
import java.sql.Timestamp;

/**
 *
 * @author redlongcity 18/03/2018
 */
public class TourAdvancedRequestCommand implements RequestCommand<SearchingRequest> {

    private final SearchingRequest request;

    private int priority;

    private boolean processed;

    private Timestamp creationTime;

    public TourAdvancedRequestCommand(SearchingRequest request, Timestamp creationTime) {
        this.request = request;
        this.creationTime = creationTime;
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
    public SearchingRequest getRequest() {
        return request;
    }

}
