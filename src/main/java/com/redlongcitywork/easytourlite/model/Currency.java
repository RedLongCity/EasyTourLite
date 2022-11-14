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
@Table(name="currencys")
public class Currency {
    
    @JsonView(TourView.class)
    @Id
    @Column(name="currency_id",unique=true,nullable=false)
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
        int hash = 7;
        hash = 19 * hash + Objects.hashCode(this.id);
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
        final Currency other = (Currency) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Currency{" + "id=" + id + ", name=" + name + '}';
    }
    
    

}
