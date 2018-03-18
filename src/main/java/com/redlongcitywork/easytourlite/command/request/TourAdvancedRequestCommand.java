package com.redlongcitywork.easytourlite.command.request;

import com.redlongcitywork.easytourlite.model.SearchingRequest;
import com.redlongcitywork.easytourlite.utils.HttpUtils;
import java.sql.Timestamp;

/**
 *
 * @author redlongcity 18/03/2018
 */
public class TourAdvancedRequestCommand implements RequestCommand<SearchingRequest> {
    
    private SearchingRequest request;
    
    private int priority;
    
    private boolean processed;
    
    private Timestamp creationTime;

    public TourAdvancedRequestCommand(SearchingRequest request, Timestamp creationTime) {
        this.request = request;
        this.creationTime = creationTime;
        this.priority = 0;
    }

    @Override
    public void execute(HttpUtils.GetCallBack callBack) {
        
    }

    @Override
    public int getPriority() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setPriority(int priority) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public SearchingRequest getRequest() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setRequest(SearchingRequest request) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean isProcessed() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setProcessed(boolean processed) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Timestamp getCreationTime() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void setCreationTime(Timestamp creationTime) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
