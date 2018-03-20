package com.redlongcitywork.easytourlite.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.redlongcitywork.easytourlite.json.view.TourView;
import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import java.util.logging.Logger;
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
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author redlongcity 11/12/2017 model for containing request parameters
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
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "hot_request_rating",
            joinColumns = @JoinColumn(name = "request_id"),
            inverseJoinColumns = @JoinColumn(name = "rating_id"))
    private Set<Hotel_Rating> ratings = new HashSet<>();

    @JsonView(TourView.class)
    @NotNull
    @Column(name = "night_from", unique = false)
    private Integer night_From;

    @JsonView(TourView.class)
    @NotNull
    @Column(name = "night_till", unique = false)
    private Integer night_Till;

    @JsonView(TourView.class)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "meal_type_id", unique = false, nullable = true)
    private Meal_Type meal_Type;

    @JsonView(TourView.class)
    @NotNull
    @Column(name = "request_time", unique = false)
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

    public Set<Hotel_Rating> getRatings() {
        return ratings;
    }

    public void setRatings(Set<Hotel_Rating> ratings) {
        this.ratings = ratings;
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
        int hash = 7;
        hash = 43 * hash + Objects.hashCode(this.country);
        hash = 43 * hash + Objects.hashCode(this.from_Cities);
        hash = 43 * hash + Objects.hashCode(this.ratings);
        hash = 43 * hash + Objects.hashCode(this.night_From);
        hash = 43 * hash + Objects.hashCode(this.night_Till);
        hash = 43 * hash + Objects.hashCode(this.meal_Type);
        hash = 43 * hash + Objects.hashCode(this.requestTime);
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
        if (!Objects.equals(this.country, other.country)) {
            return false;
        }
        if (!Objects.equals(this.from_Cities, other.from_Cities)) {
            return false;
        }
        if (!Objects.equals(this.ratings, other.ratings)) {
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
        if (!Objects.equals(this.requestTime, other.requestTime)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HotToursRequest{" + "id=" + id + ", country=" + country + ", from_Cities=" + from_Cities + ", ratings=" + ratings + ", night_From=" + night_From + ", night_Till=" + night_Till + ", meal_Type=" + meal_Type + ", requestTime=" + requestTime + '}';
    }
}
