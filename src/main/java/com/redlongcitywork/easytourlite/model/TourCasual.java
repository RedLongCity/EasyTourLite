package com.redlongcitywork.easytourlite.model;

import java.sql.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author redlongcity 16/02/2018
 */
@Entity
@Table(name = "tours_casual")
public class TourCasual {

    @Id
    @NotNull
    @Column(name = "tour_key", unique = true)
    private String key;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_type_id")
    private Meal_Type mealType;

    @NotNull
    @Column(name = "room_type", unique = false)
    private String roomType;

    @NotNull
    @Column(name = "duration", unique = false)
    private Integer duration;

    @NotNull
    @Column(name = "date_from", unique = false)
    private Date dateFrom;

    @NotNull
    @Column(name = "is_combined", unique = false)
    private Boolean combined;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id")
    private Currency currency;

    @NotNull
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_key_fn")
    private Set<Price> prices = new HashSet<>();

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_city_id")
    private From_Cities city;

    @Column(name = "transport_type", unique = false, nullable = true)
    private String transportType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_id")
    private Hotel hotel;

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Meal_Type getMealType() {
        return mealType;
    }

    public void setMealType(Meal_Type mealType) {
        this.mealType = mealType;
    }

    public String getRoomType() {
        return roomType;
    }

    public void setRoomType(String roomType) {
        this.roomType = roomType;
    }

    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Boolean getCombined() {
        return combined;
    }

    public void setCombined(Boolean combined) {
        this.combined = combined;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Set<Price> getPrices() {
        return prices;
    }

    public void setPrices(Set<Price> prices) {
        this.prices = prices;
    }

    public From_Cities getCity() {
        return city;
    }

    public void setCity(From_Cities city) {
        this.city = city;
    }

    public String getTransportType() {
        return transportType;
    }

    public void setTransportType(String transportType) {
        this.transportType = transportType;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + Objects.hashCode(this.key);
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
        final TourCasual other = (TourCasual) obj;
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TourCasual{" + "key=" + key + ", mealType=" + mealType + ", roomType=" + roomType + ", duration=" + duration + ", dateFrom=" + dateFrom + ", combined=" + combined + ", currency=" + currency + ", prices=" + prices + ", city=" + city + ", transportType=" + transportType + ", hotel=" + hotel + '}';
    }

}
