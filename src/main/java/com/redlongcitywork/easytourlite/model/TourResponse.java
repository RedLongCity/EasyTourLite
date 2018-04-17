package com.redlongcitywork.easytourlite.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.redlongcitywork.easytourlite.json.view.TourView;
import java.util.Objects;
import java.util.Set;

/**
 *
 * @author redlongcity 14/09/2017 class for incapsulating ItTours Hot Search
 * response to client
 */
public class TourResponse {

    @JsonView(TourView.class)
    private long comeBackDelay;

    @JsonView(TourView.class)
    private Set<Tour> tours;

    @JsonView(TourView.class)
    private Object request;

    public TourResponse() {
    }

    public TourResponse(long comeBackDelay,
            Set<Tour> tours,
            Object request) {
        this.comeBackDelay = comeBackDelay;
        this.tours = tours;
        this.request = request;
    }

    public Long getComeBackDelay() {
        return comeBackDelay;
    }

    public void setComeBackDelay(long comeBackDelay) {
        this.comeBackDelay = comeBackDelay;
    }

    public Set<Tour> getTours() {
        return tours;
    }

    public void setTours(Set<Tour> tours) {
        this.tours = tours;
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
        hash = 59 * hash + Objects.hashCode(this.comeBackDelay);
        hash = 59 * hash + Objects.hashCode(this.tours);
        hash = 59 * hash + Objects.hashCode(this.request);
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
        final TourResponse other = (TourResponse) obj;
        if (!Objects.equals(this.comeBackDelay, other.comeBackDelay)) {
            return false;
        }
        if (!Objects.equals(this.tours, other.tours)) {
            return false;
        }
        if (!Objects.equals(this.request, other.request)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Response{" + "comeBackDelay=" + comeBackDelay + ", tourList=" + tours + ", request=" + request + '}';
    }

}
