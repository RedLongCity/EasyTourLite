package com.redlongcitywork.easytourlite.model;

import com.fasterxml.jackson.databind.node.ArrayNode;
import com.redlongcitywork.easytourlite.requestcommand.RequestCommand;
import java.util.Objects;

/**
 *
 * @author redlongcity
 * 11/12/2017
 * model keeps response elements
 */
public class ResponseItem {
    
    private ArrayNode node;
    
    private RequestCommand command;

    public ArrayNode getNode() {
        return node;
    }

    public void setNode(ArrayNode node) {
        this.node = node;
    }

    public RequestCommand getCommand() {
        return command;
    }

    public void setCommand(RequestCommand command) {
        this.command = command;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.node);
        hash = 37 * hash + Objects.hashCode(this.command);
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
        final ResponseItem other = (ResponseItem) obj;
        if (!Objects.equals(this.node, other.node)) {
            return false;
        }
        if (!Objects.equals(this.command, other.command)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ResponseItem{" + "node=" + node + ", command=" + command + '}';
    }
    
    
    
}
