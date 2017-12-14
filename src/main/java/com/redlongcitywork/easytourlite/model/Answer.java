package com.redlongcitywork.easytourlite.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.fasterxml.jackson.databind.JsonNode;
import com.redlongcitywork.easytourlite.json.view.TourView;
import java.util.Objects;

/**
 *
 * @author redlongcity
 * 14/12/2017
 * model like entity of answer
 */
public class Answer {

    @JsonView(TourView.class)
    private long comeBackDelay;

    @JsonView(TourView.class)
    private JsonNode node;

    public Answer(long comeBackDelay, JsonNode node) {
        this.comeBackDelay = comeBackDelay;
        this.node = node;
    }
    
    public long getComeBackDelay() {
        return comeBackDelay;
    }

    public void setComeBackDelay(long comeBackDelay) {
        this.comeBackDelay = comeBackDelay;
    }

    public JsonNode getNode() {
        return node;
    }

    public void setNode(JsonNode node) {
        this.node = node;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (this.comeBackDelay ^ (this.comeBackDelay >>> 32));
        hash = 59 * hash + Objects.hashCode(this.node);
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
        final Answer other = (Answer) obj;
        if (this.comeBackDelay != other.comeBackDelay) {
            return false;
        }
        if (!Objects.equals(this.node, other.node)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Answer{" + "comeBackDelay=" + comeBackDelay + ", node=" + node + '}';
    }

    
}
