package com.redlongcitywork.easytourlite.command.request;

import com.redlongcitywork.easytourlite.extractor.TourAdvancedExtractor;
import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.model.TourAdvanced;
import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.stereotype.Component;

/**
 *
 * @author redlongcity 18/03/2018
 */
@Component
public class TourAdvancedRequestCommand implements RequestCommand<SearchingRequest> {

    private final SearchingRequest request;

    private int priority;

    private boolean processed;

    private Timestamp creationTime;

    public TourAdvancedRequestCommand(SearchingRequest request, TourAdvancedExtractor extractor, TimeUtils utils) {
        this.request = request;
        this.extractor = extractor;
        this.utils = utils;
        this.priority = 0;
        this.creationTime = utils.getCurrentTime();
    }

    @Override
    public List<TourAdvanced> execute() {
        List<TourAdvanced> result = null;
        if (request != null) {
            result = extractor.extract(request);
        }
        return result;
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
