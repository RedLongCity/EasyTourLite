package com.redlongcitywork.easytourlite.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.redlongcitywork.easytourlite.json.view.TourView;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.CascadeType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import org.hibernate.validator.constraints.NotEmpty;
/**
 *
 * @author redlongcity
 */
@Entity
@Table(name="meal_types")
public class Meal_Type {
    
    @JsonView(TourView.class)
    @Id
    @Column(name="meal_type_id",unique=true,nullable=false)
    private String id;
    
    @JsonView(TourView.class)
    @NotEmpty
    @Column(name="name",unique=false,nullable=false)
    private String name;
    
    @JsonView(TourView.class)
    @NotEmpty
    @Column(name="name_full",unique=false,nullable=false)
    private String name_Full;
    
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

    public String getName_Full() {
        return name_Full;
    }

    public void setName_Full(String name_full) {
        this.name_Full = name_full;
    }

    @Override
    public int hashCode() {
        int hash = 7;
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
        final Meal_Type other = (Meal_Type) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Meal_Type{" + "id=" + id + ", name=" + name + ", name_full=" + name_Full + '}';
    }
    
    
}
