package com.redlongcitywork.easytourlite.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.redlongcitywork.easytourlite.json.view.HotToursRequestView;
import com.redlongcitywork.easytourlite.json.view.TourView;
import java.sql.Timestamp;
import java.util.Objects;
import java.util.logging.Logger;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author redlongcity
 * 11/12/2017
 * model for containing request parameters
 */
@Entity
@Table(name = "hot_tours_requests")
public class HotToursRequest {

    private static final Logger LOG = Logger.getLogger(HotToursRequest.class.getName());

    @JsonView(TourView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "request_id", unique = true, nullable = false)
    private Integer id;

    @JsonView(TourView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "country_id", nullable = true)
    private Country country;

    @JsonView(TourView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "from_city_id", nullable = true)
    private From_Cities from_Cities;

    @JsonView(TourView.class)
    @NotEmpty
    @Column(name = "hotel_rating", unique = false, nullable = false)
    private String hotel_Rating;

    @JsonView(TourView.class)
    @Column(name = "night_from", unique = false, nullable = false)
    private Integer night_From;

    @JsonView(TourView.class)
    @Column(name = "night_till", unique = false, nullable = false)
    private Integer night_Till;

    @JsonView(TourView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_type_id", unique = false, nullable = true)
    private Meal_Type meal_Type;
    
    @JsonView(TourView.class)
    @Column(name = "request_time")
    private Timestamp requestTime;

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

    public From_Cities getFrom_Cities() {
        return from_Cities;
    }

    public void setFrom_Cities(From_Cities from_Cities) {
        this.from_Cities = from_Cities;
    }

    public String getHotel_Rating() {
        return hotel_Rating;
    }

    public void setHotel_Rating(String hotel_Rating) {
        this.hotel_Rating = hotel_Rating;
    }

    public Integer getNight_From() {
        return night_From;
    }

    public void setNight_From(Integer night_From) {
        this.night_From = night_From;
    }

    public Integer getNight_Till() {
        return night_Till;
    }

    public void setNight_Till(Integer night_Till) {
        this.night_Till = night_Till;
    }

    public Meal_Type getMeal_Type() {
        return meal_Type;
    }

    public void setMeal_Type(Meal_Type meal_Type) {
        this.meal_Type = meal_Type;
    }

    public Timestamp getRequestTime() {
        return requestTime;
    }

    public void setRequestTime(Timestamp requestTime) {
        this.requestTime = requestTime;
    }
    
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.country);
        hash = 41 * hash + Objects.hashCode(this.from_Cities);
        hash = 41 * hash + Objects.hashCode(this.hotel_Rating);
        hash = 41 * hash + Objects.hashCode(this.night_From);
        hash = 41 * hash + Objects.hashCode(this.night_Till);
        hash = 41 * hash + Objects.hashCode(this.meal_Type);
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
        final HotToursRequest other = (HotToursRequest) obj;
        if (!Objects.equals(this.hotel_Rating, other.hotel_Rating)) {
            return false;
        }
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.from_Cities, other.from_Cities)) {
            return false;
        }
        if (!Objects.equals(this.night_From, other.night_From)) {
            return false;
        }
        if (!Objects.equals(this.night_Till, other.night_Till)) {
            return false;
        }
        if (!Objects.equals(this.meal_Type, other.meal_Type)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HotToursRequest{" + "id=" + id + ", country=" + country + ", from_Cities=" + from_Cities + ", hotel_Rating=" + hotel_Rating + ", night_From=" + night_From + ", night_Till=" + night_Till + ", meal_Type=" + meal_Type + '}';
    }

}
