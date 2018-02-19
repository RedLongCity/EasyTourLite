package com.redlongcitywork.easytourlite.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author redlongcity 18/02/2018
 */
@Entity
@Table(name = "hotels")
public class Hotel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;

    @NotNull
    @Column(name = "hotel_name")
    private String hotelName;

    @Column(name = "hotel_reciew_rate")
    private String hotelReviewRate;

    @Column(name = "hotel_review_count")
    private String hotelReviewCount;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "hotel")
    private Set<Facility> facilities = new HashSet<>();

    @Column(name = "lat")
    private String lat;

    @Column(name = "lng")
    private String lng;

    @Column(name = "wifi_free")
    private Boolean wifiFree;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "hotel_id_fn")
    private Set<Hotel_Image> images = new HashSet<>();

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "rating_id")
    private Hotel_Rating rating;

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
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "hotel", cascade = CascadeType.ALL)
    private Set<TourCasual> tours = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public String getHotelName() {
        return hotelName;
    }

    public void setHotelName(String hotelName) {
        this.hotelName = hotelName;
    }

    public String getHotelReviewRate() {
        return hotelReviewRate;
    }

    public void setHotelReviewRate(String hotelReviewRate) {
        this.hotelReviewRate = hotelReviewRate;
    }

    public String getHotelReviewCount() {
        return hotelReviewCount;
    }

    public void setHotelReviewCount(String hotelReviewCount) {
        this.hotelReviewCount = hotelReviewCount;
    }

    public Set<Facility> getFacilities() {
        return facilities;
    }

    public void setFacilities(Set<Facility> facilities) {
        this.facilities = facilities;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public Boolean getWifiFree() {
        return wifiFree;
    }

    public void setWifiFree(Boolean wifiFree) {
        this.wifiFree = wifiFree;
    }

    public Set<Hotel_Image> getImages() {
        return images;
    }

    public void setImages(Set<Hotel_Image> images) {
        this.images = images;
    }

    public Hotel_Rating getRating() {
        return rating;
    }

    public void setRating(Hotel_Rating rating) {
        this.rating = rating;
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

    public Set<TourCasual> getTours() {
        return tours;
    }

    public void setTours(Set<TourCasual> tours) {
        this.tours = tours;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.hotelName);
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
        final Hotel other = (Hotel) obj;
        if (!Objects.equals(this.hotelName, other.hotelName)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", country=" + country + ", region=" + region + ", hotelName=" + hotelName + ", hotelReviewRate=" + hotelReviewRate + ", hotelReviewCount=" + hotelReviewCount + ", facilities=" + facilities + ", lat=" + lat + ", lng=" + lng + ", wifiFree=" + wifiFree + ", images=" + images + ", rating=" + rating + ", adultAmount=" + adultAmount + ", childAmount=" + childAmount + ", accomodation=" + accomodation + ", tours=" + tours + '}';
    }

}
