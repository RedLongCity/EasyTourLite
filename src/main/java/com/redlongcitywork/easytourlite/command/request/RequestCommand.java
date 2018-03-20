package com.redlongcitywork.easytourlite.command.request;

import java.sql.Timestamp;

/**
 *
 * @author redlongcity 11/12/2017 
 * interface for unification Requests Command
 */
public interface RequestCommand<Request> {

    int getPriority();

    void setPriority(int priority);

    Request getRequest();

    boolean isProcessed();

    void setProcessed(boolean processed);

    Timestamp getCreationTime();

    void setCreationTime(Timestamp creationTime);

}
