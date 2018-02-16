package com.redlongcitywork.easytourlite.model;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author redlongcity
 * 16/02/2018
 */
@Entity
@Table(name = "hotels")
public class Hotel {
    
    @Id
    @NotNull
    @Column(name = "hotel_id", unique = true)
    private String id;
    
    @NotNull
    @Column(name = "hotel_name", unique = false)
    private String name;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "hotel_rating_id")
    private Hotel_Rating rating;
    
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "region_id")
    private Region region;
    
    @NotNull
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "hotel_type",
            joinColumns = @JoinColumn(name = "hotel_id"),
            inverseJoinColumns = @JoinColumn(name = "type_id"))
    private Set<Type> typeSet = new HashSet<>();

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Hotel_Rating getRating() {
        return rating;
    }

    public void setRating(Hotel_Rating rating) {
        this.rating = rating;
    }

    public Region getRegion() {
        return region;
    }

    public void setRegion(Region region) {
        this.region = region;
    }

    public Set<Type> getTypeSet() {
        return typeSet;
    }

    public void setTypeSet(Set<Type> typeSet) {
        this.typeSet = typeSet;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
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
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hotel{" + "id=" + id + ", name=" + name + ", rating=" + rating + ", region=" + region + ", typeSet=" + typeSet + '}';
    }
    
    
}
