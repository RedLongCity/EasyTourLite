package com.redlongcitywork.easytourlite.model;

import com.fasterxml.jackson.annotation.JsonView;
import com.redlongcitywork.easytourlite.json.view.TourView;
import java.util.Objects;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Column;
import javax.persistence.Entity;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author redlongcity
 */
@Entity
@Table(name = "meal_types")
public class Meal_Type {

    @JsonView(TourView.class)
    @Id
    @Column(name = "meal_type_id")
    private String id;

    @JsonView(TourView.class)
    @NotEmpty
    @Column(name = "name")
    private String name;

    @JsonView(TourView.class)
    @Column(name = "name_full")
    private String name_Full;

    public Meal_Type() {
    }

    public Meal_Type(String id) {
        this.id = id;
    }
    
    public Meal_Type(String id, String name, String name_Full) {
        this.id = id;
        this.name = name;
        this.name_Full = name_Full;
    }
    
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
