package com.redlongcitywork.easytourlite.model;

import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author redlongcity
 * 14/02/2018
 */
@Entity
@Table(name = "regions")
public class Region {
    
    @Id
    @NotNull
    @Column(name = "region_id", unique = true)
    private String Id;
    
    @NotNull
    @Column(name = "region_name", unique = false)
    private String name;

    public Region() {
    }

    public Region(String Id, String name) {
        this.Id = Id;
        this.name = name;
    }
    
    public String getId() {
        return Id;
    }

    public void setId(String Id) {
        this.Id = Id;
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
        hash = 23 * hash + Objects.hashCode(this.Id);
        hash = 23 * hash + Objects.hashCode(this.name);
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
        final Region other = (Region) obj;
        if (!Objects.equals(this.name, other.name)) {
            return false;
        }
        if (!Objects.equals(this.Id, other.Id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Region{" + "Id=" + Id + ", name=" + name + '}';
    }
    
    
    
}
