package com.redlongcitywork.easytourlite.constants;

import com.redlongcitywork.easytourlite.utils.TimeUtils;
import java.util.logging.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author redlongcity
 * 08.09.2017
 * class for synchronization dao processes and responses to clients
 */
@Service
public class AppConstants {

    private static final Logger LOG = Logger.getLogger(AppConstants.class.getName());

    private boolean globalDelay = false;//for regulation access to out database

    private boolean filtersUpdate = false;//consist Country, From_Cities updating etc.

    private long shortUpdatingDelay = 1000;//Previous or Current update time

    private boolean shortRun = true;//indicator for short job running

    private boolean shortSuspended = false;//indicator pause job by itself

//    private String globalUpdatingDelay = "0 0 0/2 * * ?";//delay between global updating
    private String globalUpdatingDelay = "0 0/1 * * * ?";//delay between global updating

    private boolean globalRun = true;//indicator for global job running

    private boolean globalSuspended = false;//indicator of pausing job by itself

    private int requestPullSize = 2;//size of RequestCommand pull

    private int responsePullSize = 2;//size of ResponseItem pull

    private long freezzeeTimeDelay = 5000;//time for untouchability of ResponseItem in pull

    private int revelance = 24;//time of revelance response items in hours
    
    private boolean saving = false;//indicator of saving process in database

    @Autowired
    TimeUtils timeUtils;

    public boolean isFiltersUpdate() {
        return filtersUpdate;
    }

    public void setFiltersUpdate(boolean filtersUpdate) {
        this.filtersUpdate = filtersUpdate;
    }

    public long getShortUpdatingDelay() {
        return shortUpdatingDelay;
    }

    public void setShortUpdatingDelay(long shortUpdatingDelay) {
        this.shortUpdatingDelay = shortUpdatingDelay;
    }

    public String getGlobalUpdatingDelay() {
        return globalUpdatingDelay;
    }

    public void setGlobalUpdatingDelay(String globalUpdatingDelay) {
        this.globalUpdatingDelay = globalUpdatingDelay;
    }

    public boolean isGlobalDelay() {
        return globalDelay;
    }

    public void setGlobalDelay(boolean globalDelay) {
        this.globalDelay = globalDelay;
    }

    public boolean isShortRun() {
        return shortRun;
    }

    public void setShortRun(boolean isShorRun) {
        this.shortRun = isShorRun;
    }

    public boolean isGlobalRun() {
        return globalRun;
    }

    public void setGlobalRun(boolean isGlobalRunning) {
        this.globalRun = isGlobalRunning;
    }

    public boolean isShortSuspended() {
        return shortSuspended;
    }

    public void setShortSuspended(boolean shortSuspended) {
        this.shortSuspended = shortSuspended;
    }

    public boolean isGlobalSuspended() {
        return globalSuspended;
    }

    public void setGlobalSuspended(boolean globalSuspended) {
        this.globalSuspended = globalSuspended;
    }

    public int getRequestPullSize() {
        return requestPullSize;
    }

    public void setRequestPullSize(int requestPullSize) {
        this.requestPullSize = requestPullSize;
    }

    public int getResponsePullSize() {
        return responsePullSize;
    }

    public void setResponsePullSize(int responsePullSize) {
        this.responsePullSize = responsePullSize;
    }

    public long getFreezzeeTimeDelay() {
        return freezzeeTimeDelay;
    }

    public void setFreezzeeTimeDelay(long freezzeeTime) {
        this.freezzeeTimeDelay = freezzeeTime;
    }

    public int getRevelance() {
        return revelance;
    }

    public void setRevelance(int revelance) {
        this.revelance = revelance;
    }

    public boolean isSaving() {
        return saving;
    }

    public void setSaving(boolean saving) {
        this.saving = saving;
    }
    
}
