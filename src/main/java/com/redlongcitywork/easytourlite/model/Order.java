package com.redlongcitywork.easytourlite.model;

import java.util.Objects;

/**
 * Created by redlongcity on 14.10.2017.
 * class for storing data about order
 */

public class Order {

    private UserData data;

    private Tour tour;

    public Order() {
    }
    
    public UserData getData() {
        return data;
    }

    public void setData(UserData data) {
        this.data = data;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
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
        hash = 89 * hash + Objects.hashCode(this.data);
        hash = 89 * hash + Objects.hashCode(this.tour);
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
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        if (!Objects.equals(this.tour, other.tour)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Order{" + "data=" + data + ", tour=" + tour + '}';
    }
    
    

}
