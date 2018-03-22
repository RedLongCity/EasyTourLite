package com.redlongcitywork.easytourlite.model;

import java.sql.Timestamp;

/**
 *
 * @author redlongcity 22/03/2018
 */
public class ResponseItem {

    private Object request;

    private Object answer;

    private Timestamp time;

    private Timestamp freezeTime;

    private Timestamp revelance;

    private boolean immune;

    private int priority;

    public ResponseItem(Object request, Object answer, Timestamp time, Timestamp freezeTime, Timestamp revelance, boolean immune, int priority) {
        this.request = request;
        this.answer = answer;
        this.time = time;
        this.freezeTime = freezeTime;
        this.revelance = revelance;
        this.immune = immune;
        this.priority = priority;
    }

    public ResponseItem() {
    }
    
    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    public Object getAnswer() {
        return answer;
    }

    public void setAnswer(Object answer) {
        this.answer = answer;
    }

    public Timestamp getTime() {
        return time;
    }

    public void setTime(Timestamp time) {
        this.time = time;
    }

    public Timestamp getFreezeTime() {
        return freezeTime;
    }

    public void setFreezeTime(Timestamp freezeTime) {
        this.freezeTime = freezeTime;
    }

    public boolean isImmune() {
        return immune;
    }

    public void setImmune(boolean immune) {
        this.immune = immune;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public Timestamp getRevelance() {
        return revelance;
    }

    public void setRevelance(Timestamp revelance) {
        this.revelance = revelance;
    }

    @Override
    public String toString() {
        return "ResponseItem{" + "request=" + request + ", answer=" + answer + ", time=" + time + ", freezeTime=" + freezeTime + ", immune=" + immune + ", priority=" + priority + ", revelance=" + revelance + '}';
    }

}
