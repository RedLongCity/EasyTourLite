package com.redlongcitywork.easytourlite.model;

import java.sql.Date;
import java.sql.Timestamp;
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
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author redlongcity 08/02/2018 model for keeping info about searching request
 */
@Entity
@Table(name = "search_requests")
public class SearchingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", unique = true)
    private Integer id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private Type type;

    @Column(name = "kind", unique = false, nullable = true)
    private Integer kind;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id")
    private Country country;

    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_city_id")
    private From_Cities city;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "searching_request_region",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "region_id"))
    private Set<Region> regions = new HashSet<>();

    @Column(name = "hotel", unique = false, nullable = true)
    private String hotel;

    @NotNull
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rating_request",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "rating_id"))
    private Set<Hotel_Rating> ratingSet = new HashSet<>();

    @NotNull
    @Column(name = "adult_amount", unique = false)
    private Integer adultAmount;

    @Column(name = "child_amount", unique = false, nullable = true)
    private Integer childAmount;

    @Column(name = "child_age", unique = false, nullable = true)
    private String childAge;

    @NotNull
    @Column(name = "night_from", unique = false)
    private Integer nightFrom;

    @NotNull
    @Column(name = "night_till", unique = false)
    private Integer nightTill;

    @NotNull
    @Column(name = "date_from", unique = false)
    private Date dateFrom;

    @NotNull
    @Column(name = "date_till", unique = false)
    private Date dateTill;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "meal_type_request",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "meal_type_id"))
    private Set<Meal_Type> mealTypes = new HashSet<>();

    @Column(name = "price_from", unique = false, nullable = true)
    private Integer priceFrom;

    @Column(name = "price_till", unique = false, nullable = true)
    private Integer priceTill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "currency_id", unique = false, nullable = true)
    private Currency currency;

    @Column(name = "only_standart", unique = false, nullable = true)
    private Integer onlyStandart;

    @NotNull
    @Column(name = "request_time")
    private Timestamp requestTime;//TODO Добавить

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Integer getKind() {
        return kind;
    }

    public void setKind(Integer kind) {
        this.kind = kind;
    }

    public Country getCountry() {
        return country;
    }

    public void setCountry(Country country) {
        this.country = country;
    }

    public From_Cities getCity() {
        return city;
    }

    public void setCity(From_Cities city) {
        this.city = city;
    }

    public Set<Region> getRegions() {
        return regions;
    }

    public void setRegions(Set<Region> regions) {
        this.regions = regions;
    }

    public String getHotel() {
        return hotel;
    }

    public void setHotel(String hotel) {
        this.hotel = hotel;
    }

    public Set<Hotel_Rating> getRatingSet() {
        return ratingSet;
    }

    public void setRatingSet(Set<Hotel_Rating> ratingSet) {
        this.ratingSet = ratingSet;
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

    public String getChildAge() {
        return childAge;
    }

    public void setChildAge(String childAge) {
        this.childAge = childAge;
    }

    public Integer getNightFrom() {
        return nightFrom;
    }

    public void setNightFrom(Integer nightFrom) {
        this.nightFrom = nightFrom;
    }

    public Integer getNightTill() {
        return nightTill;
    }

    public void setNightTill(Integer nightTill) {
        this.nightTill = nightTill;
    }

    public Date getDateFrom() {
        return dateFrom;
    }

    public void setDateFrom(Date dateFrom) {
        this.dateFrom = dateFrom;
    }

    public Date getDateTill() {
        return dateTill;
    }

    public void setDateTill(Date dateTill) {
        this.dateTill = dateTill;
    }

    public Set<Meal_Type> getMealTypes() {
        return mealTypes;
    }

    public void setMealTypes(Set<Meal_Type> mealTypes) {
        this.mealTypes = mealTypes;
    }

    public Integer getPriceFrom() {
        return priceFrom;
    }

    public void setPriceFrom(Integer priceFrom) {
        this.priceFrom = priceFrom;
    }

    public Integer getPriceTill() {
        return priceTill;
    }

    public void setPriceTill(Integer priceTill) {
        this.priceTill = priceTill;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public Integer getOnlyStandart() {
        return onlyStandart;
    }

    public void setOnlyStandart(Integer onlyStandart) {
        this.onlyStandart = onlyStandart;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 97 * hash + Objects.hashCode(this.type);
        hash = 97 * hash + Objects.hashCode(this.kind);
        hash = 97 * hash + Objects.hashCode(this.country);
        hash = 97 * hash + Objects.hashCode(this.city);
        hash = 97 * hash + Objects.hashCode(this.regions);
        hash = 97 * hash + Objects.hashCode(this.hotel);
        hash = 97 * hash + Objects.hashCode(this.ratingSet);
        hash = 97 * hash + Objects.hashCode(this.adultAmount);
        hash = 97 * hash + Objects.hashCode(this.childAmount);
        hash = 97 * hash + Objects.hashCode(this.childAge);
        hash = 97 * hash + Objects.hashCode(this.nightFrom);
        hash = 97 * hash + Objects.hashCode(this.nightTill);
        hash = 97 * hash + Objects.hashCode(this.dateFrom);
        hash = 97 * hash + Objects.hashCode(this.dateTill);
        hash = 97 * hash + Objects.hashCode(this.mealTypes);
        hash = 97 * hash + Objects.hashCode(this.priceFrom);
        hash = 97 * hash + Objects.hashCode(this.priceTill);
        hash = 97 * hash + Objects.hashCode(this.currency);
        hash = 97 * hash + Objects.hashCode(this.onlyStandart);
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
        final SearchingRequest other = (SearchingRequest) obj;
        if (!Objects.equals(this.hotel, other.hotel)) {
            return false;
        }
        if (!Objects.equals(this.childAge, other.childAge)) {
            return false;
        }
        if (!Objects.equals(this.type, other.type)) {
            return false;
        }
        if (!Objects.equals(this.kind, other.kind)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.city, other.city)) {
            return false;
        }
        if (!Objects.equals(this.regions, other.regions)) {
            return false;
        }
        if (!Objects.equals(this.ratingSet, other.ratingSet)) {
            return false;
        }
        if (!Objects.equals(this.adultAmount, other.adultAmount)) {
            return false;
        }
        if (!Objects.equals(this.childAmount, other.childAmount)) {
            return false;
        }
        if (!Objects.equals(this.nightFrom, other.nightFrom)) {
            return false;
        }
        if (!Objects.equals(this.nightTill, other.nightTill)) {
            return false;
        }
        if (!Objects.equals(this.dateFrom, other.dateFrom)) {
            return false;
        }
        if (!Objects.equals(this.dateTill, other.dateTill)) {
            return false;
        }
        if (!Objects.equals(this.mealTypes, other.mealTypes)) {
            return false;
        }
        if (!Objects.equals(this.priceFrom, other.priceFrom)) {
            return false;
        }
        if (!Objects.equals(this.priceTill, other.priceTill)) {
            return false;
        }
        if (!Objects.equals(this.currency, other.currency)) {
            return false;
        }
        if (!Objects.equals(this.onlyStandart, other.onlyStandart)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "SearchingRequest{" + "id=" + id + ", type=" + type + ", kind=" + kind + ", country=" + country + ", city=" + city + ", regions=" + regions + ", hotel=" + hotel + ", ratingSet=" + ratingSet + ", adultAmount=" + adultAmount + ", childAmount=" + childAmount + ", childAge=" + childAge + ", nightFrom=" + nightFrom + ", nightTill=" + nightTill + ", dateFrom=" + dateFrom + ", dateTill=" + dateTill + ", mealTypes=" + mealTypes + ", priceFrom=" + priceFrom + ", priceTill=" + priceTill + ", currency=" + currency + ", onlyStandart=" + onlyStandart + ", requestTime=" + requestTime + '}';
    }

}
