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
@Table(name="hotel_ratings")
public class Hotel_Rating {
    
    @JsonView(TourView.class)
    @Id
    @Column(name="hotel_rating_id",unique=true,nullable=false)
    private String id;
    
    @JsonView(TourView.class)
    @NotEmpty
    @Column(name="name",unique=false,nullable=false)
    private String name;
    
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

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 41 * hash + Objects.hashCode(this.id);
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
        final Hotel_Rating other = (Hotel_Rating) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Hotel_Rating{" + "id=" + id + ", name=" + name + '}';
    }
    
}
