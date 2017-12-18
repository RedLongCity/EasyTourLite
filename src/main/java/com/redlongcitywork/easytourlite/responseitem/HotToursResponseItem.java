package com.redlongcitywork.easytourlite.responseitem;

import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import java.sql.Timestamp;

/**
 *
 * @author redlongcity
 * 13/12/2017
 * item for keeping information about Hot Tours Response
 */
public class HotToursResponseItem implements ResponseItem<HotToursRequest> {
    
    private HotToursRequest request;
    
    private JsonNode node;
    
    private Timestamp freezeeTime;
    
    private boolean immune;
    
    private int priority;
    
    private Timestamp revelance;

    public HotToursResponseItem(HotToursRequest request, JsonNode node) {
        this.request = request;
        this.node = node;
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
    public JsonNode getNode() {
        return node;
    }

    @Override
    public void setNode(JsonNode node) {
        this.node = node;
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
        return "HotToursResponseItem{" + "request=" + request + ", node=" + node + ", freezeeTime=" + freezeeTime + ", immune=" + immune + ", priority=" + priority + ", revelance=" + revelance + '}';
    }
    
}
