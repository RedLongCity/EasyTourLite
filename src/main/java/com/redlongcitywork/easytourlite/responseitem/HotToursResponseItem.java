package com.redlongcitywork.easytourlite.responseitem;

import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.Tour;
import java.sql.Timestamp;
import java.util.List;

/**
 *
 * @author redlongcity
 * 13/12/2017
 * item for keeping information about Hot Tours Response
 */
public class HotToursResponseItem implements 
        ResponseItem<HotToursRequest, List<Tour>> {
    
    private HotToursRequest request;
    
    private List<Tour> answer;
    
    private Timestamp freezeeTime;
    
    private boolean immune;
    
    private int priority;
    
    private Timestamp revelance;

    public HotToursResponseItem(HotToursRequest request, List<Tour> answer) {
        this.request = request;
        this.answer = answer;
        this.priority = 0;
    }

    public HotToursResponseItem() {

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
    public List<Tour> getAnswer() {
        return answer;
    }

    @Override
    public void setAnswer(List<Tour> answer) {
        this.answer = answer;
    }
    
    @Override
    public Timestamp getFreezeeTime() {
        return freezeeTime;
    }

    @Override
    public void setFreezeeTime(Timestamp freezeeTime) {
        this.freezeeTime = freezeeTime;
    }
    

    @Override
    public boolean isImmune() {
        return immune;
    }

    @Override
    public void setImmune(boolean immune) {
        this.immune = immune;
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
    public void setRevelance(Timestamp revelance) {
        this.revelance = revelance;
    }

    @Override
    public Timestamp getRevelance() {
        return this.revelance;
    }

    @Override
    public String toString() {
        return "HotToursResponseItem{" + "request=" + request + ", answer=" + answer + ", freezeeTime=" + freezeeTime + ", immune=" + immune + ", priority=" + priority + ", revelance=" + revelance + '}';
    }
    
}
