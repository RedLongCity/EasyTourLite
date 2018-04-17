package com.redlongcitywork.easytourlite.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.redlongcitywork.easytourlite.json.view.TourView;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author redlongcity 20/03/2018
 */
public class TourAdvancedResponse {

    @JsonView(TourView.class)
    private long comeBackDelay;

    @JsonView(TourView.class)
    private Set<TourAdvanced> tourList;

    @JsonView(TourView.class)
    private Object request;

    public TourAdvancedResponse() {
    }

    public TourAdvancedResponse(long comeBackDelay, Set<TourAdvanced> tourList, Object request) {
        this.comeBackDelay = comeBackDelay;
        this.tourList = tourList;
        this.request = request;
    }

    public long getComeBackDelay() {
        return comeBackDelay;
    }

    public void setComeBackDelay(long comeBackDelay) {
        this.comeBackDelay = comeBackDelay;
    }

    public Set<TourAdvanced> getTourList() {
        return tourList;
    }

    public void setTourList(Set<TourAdvanced> tourList) {
        this.tourList = tourList;
    }

    public Object getRequest() {
        return request;
    }

    public void setRequest(Object request) {
        this.request = request;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + (int) (this.comeBackDelay ^ (this.comeBackDelay >>> 32));
        hash = 37 * hash + Objects.hashCode(this.tourList);
        hash = 37 * hash + Objects.hashCode(this.request);
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
        final TourAdvancedResponse other = (TourAdvancedResponse) obj;
        if (this.comeBackDelay != other.comeBackDelay) {
            return false;
        }
        if (!Objects.equals(this.tourList, other.tourList)) {
            return false;
        }
        if (!Objects.equals(this.request, other.request)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TourAdvancedResponse{" + "comeBackDelay=" + comeBackDelay + ", tourList=" + tourList + ", request=" + request + '}';
    }

}
