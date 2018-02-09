package com.redlongcitywork.easytourlite.model;

import java.sql.Date;
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

/**
 *
 * @author redlongcity 08/02/2018 model for keeping info about searching request
 */
@Entity
@Table(name = "search_requests")
public class SearchingRequest {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", unique = true, nullable = false)
    private Integer id;

    @Column(name = "type", unique = false, nullable = true)
    private Integer type;

    @Column(name = "kind", unique = false, nullable = true)
    private Integer kind;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = false)
    private Country country;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_city_id", nullable = false)
    private From_Cities city;

    @Column(name = "reqion", unique = false, nullable = true)
    private String region;

    @Column(name = "hotel", unique = false, nullable = true)
    private String hotel;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "rating_request",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "rating_id"))
    private Set<Hotel_Rating> ratingSet = new HashSet<>();

    @Column(name = "adult_amount", unique = false, nullable = false)
    private Integer adultAmount;

    @Column(name = "child_amount", unique = false, nullable = true)
    private Integer childAmount;

    @Column(name = "child_age", unique = false, nullable = true)
    private String childAge;

    @Column(name = "night_from", unique = false, nullable = false)
    private Integer nightFrom;

    @Column(name = "night_till", unique = false, nullable = false)
    private Integer nightTill;

    @Column(name = "date_from", unique = false, nullable = false)
    private Date dateFrom;

    @Column(name = "date_till", unique = false, nullable = false)
    private Date dateTill;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_type_id", unique = false, nullable = true)
    private Meal_Type mealType;

    @Column(name = "price_from", unique = false, nullable = true)
    private Integer priceFrom;

    @Column(name = "price_till", unique = false, nullable = true)
    private Integer priceTill;

    @Column(name = "currency_id", unique = false, nullable = true)
    private Currency currency;

    @Column(name = "only_standart", unique = false, nullable = true)
    private Integer onlyStandart;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
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

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
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

    public Meal_Type getMealType() {
        return mealType;
    }

    public void setMealType(Meal_Type mealType) {
        this.mealType = mealType;
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

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.type);
        hash = 37 * hash + Objects.hashCode(this.kind);
        hash = 37 * hash + Objects.hashCode(this.country);
        hash = 37 * hash + Objects.hashCode(this.city);
        hash = 37 * hash + Objects.hashCode(this.region);
        hash = 37 * hash + Objects.hashCode(this.hotel);
        hash = 37 * hash + Objects.hashCode(this.ratingSet);
        hash = 37 * hash + Objects.hashCode(this.adultAmount);
        hash = 37 * hash + Objects.hashCode(this.childAmount);
        hash = 37 * hash + Objects.hashCode(this.childAge);
        hash = 37 * hash + Objects.hashCode(this.nightFrom);
        hash = 37 * hash + Objects.hashCode(this.nightTill);
        hash = 37 * hash + Objects.hashCode(this.dateFrom);
        hash = 37 * hash + Objects.hashCode(this.dateTill);
        hash = 37 * hash + Objects.hashCode(this.mealType);
        hash = 37 * hash + Objects.hashCode(this.priceFrom);
        hash = 37 * hash + Objects.hashCode(this.priceTill);
        hash = 37 * hash + Objects.hashCode(this.currency);
        hash = 37 * hash + Objects.hashCode(this.onlyStandart);
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
        if (!Objects.equals(this.region, other.region)) {
            return false;
        }
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
        if (!Objects.equals(this.mealType, other.mealType)) {
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
        return "SearchingRequest{" + "id=" + id + ", type=" + type + ", kind=" + kind + ", country=" + country + ", city=" + city + ", region=" + region + ", hotel=" + hotel + ", ratingSet=" + ratingSet + ", adultAmount=" + adultAmount + ", childAmount=" + childAmount + ", childAge=" + childAge + ", nightFrom=" + nightFrom + ", nightTill=" + nightTill + ", dateFrom=" + dateFrom + ", dateTill=" + dateTill + ", mealType=" + mealType + ", priceFrom=" + priceFrom + ", priceTill=" + priceTill + ", currency=" + currency + ", onlyStandart=" + onlyStandart + '}';
    }
    
}
