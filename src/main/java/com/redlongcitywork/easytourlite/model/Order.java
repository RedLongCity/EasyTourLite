package com.redlongcitywork.easytourlite.model;

import java.util.Objects;

/**
 * Created by redlongcity on 14.10.2017.
 * class for storing data about order
 */

public class Order {

    private UserData data;

    private String key;

    public Order() {
    }
    
    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    
    public boolean isEmpty(){
        if (data != null) {
            return data.isEmpty();
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.data);
        hash = 29 * hash + Objects.hashCode(this.key);
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
        final Order other = (Order) obj;
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "data=" + data + ", key=" + key + '}';
    }


}
