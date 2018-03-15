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
 * @author redlongcity 03/03/2018
 */
@Entity
@Table(name = "advanced_tours")
public class TourAdvanced {

    @Id
    @NotNull
    @Column(name = "tour_key", unique = true)
    private String key;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @NotNull
    @Column(name = "hotel_id")
    private Integer hotelId;

    @NotNull
    @Column(name = "hotel_name")
    private String hotelName;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating_id")
    private Hotel_Rating rating;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_type_id")
    private Meal_Type mealType;

    @NotNull
    @Column(name = "adult_amount")
    private Integer adultAmount;

    @NotNull
    @Column(name = "child_amount")
    private Integer childAmount;

    @NotNull
    @Column(name = "accomodation")
    private String accomodation;

    @NotNull
    @Column(name = "room_type")
    private String roomType;

    @NotNull
    @Column(name = "duration")
    private Integer duration;

    @NotNull
    @Column(name = "date_from")
    private Date dateFrom;

    @NotNull
    @Column(name = "is_combined")
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
    @JoinColumn(name = "city_id")
    private From_Cities city;

    @Column(name = "transport")
    private String transportType;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_key_fn")
    private Set<Hotel_Image> images = new HashSet<>();

    @Column(name = "hotel_review_rate")
    private String rate;

    @Column(name = "hotel_review_count")
    private String reviewCount;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_key_fn")
    private Set<Facility> facilities = new HashSet<>();

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Region getRegion() {
        return region;
    }

    public Hotel_Rating getRating() {
        return rating;
    }

    public void setRating(Hotel_Rating rating) {
        this.rating = rating;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Integer getHotelId() {
        return hotelId;
    }

    public void setHotelId(Integer hotelId) {
        this.hotelId = hotelId;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public Meal_Type getMealType() {
        return mealType;
    }

    public void setMealType(Meal_Type mealType) {
        this.mealType = mealType;
    }

    public Integer getAdultAmount() {
        return adultAmount;
    }

    public void setAdultAmount(Integer adultAmount) {
        this.adultAmount = adultAmount;
    }

    public Integer getChildAmount() {
        return childAmount;
    }

    public void setChildAmount(Integer childAmount) {
        this.childAmount = childAmount;
    }

    public String getAccomodation() {
        return accomodation;
    }

    public void setAccomodation(String accomodation) {
        this.accomodation = accomodation;
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

    public Set<Hotel_Image> getImages() {
        return images;
    }

    public void setImages(Set<Hotel_Image> images) {
        this.images = images;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    public String getReviewCount() {
        return reviewCount;
    }

    public void setReviewCount(String reviewCount) {
        this.reviewCount = reviewCount;
    }

    public Set<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(Set<Facility> facilities) {
        this.facilities = facilities;
    }

    @Override
    public int hashCode() {
        int hash = 5;
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
        final TourAdvanced other = (TourAdvanced) obj;
        if (!Objects.equals(this.key, other.key)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TourAdvanced{" + "key=" + key + ", country=" + country + ", type=" + type + ", region=" + region + ", hotelId=" + hotelId + ", hotelName=" + hotelName + ", rating=" + rating + ", mealType=" + mealType + ", adultAmount=" + adultAmount + ", childAmount=" + childAmount + ", accomodation=" + accomodation + ", roomType=" + roomType + ", duration=" + duration + ", dateFrom=" + dateFrom + ", combined=" + combined + ", currency=" + currency + ", prices=" + prices + ", city=" + city + ", transportType=" + transportType + ", images=" + images + ", rate=" + rate + ", reviewCount=" + reviewCount + ", facilities=" + facilities + '}';
    }
}
