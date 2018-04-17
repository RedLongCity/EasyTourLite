package com.redlongcitywork.easytourlite.model;

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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

/**
 *
 * @author redlongcity 23/03/2018
 */
@Entity
@Table(name = "advanced_tours_sessions")
public class TourAdvancedSession {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id")
    private Integer id;

    @NotNull
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id")
    private SearchingRequest request;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "sessions_tours",
            joinColumns = {
                @JoinColumn(name = "session_id")},
            inverseJoinColumns = {
                @JoinColumn(name = "tour_key")})
    private Set<TourAdvanced> tours = new HashSet<>();

    public TourAdvancedSession() {
    }

    public TourAdvancedSession(SearchingRequest request, Set<TourAdvanced> tours) {
        this.request = request;
        this.tours = tours;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public SearchingRequest getRequest() {
        return request;
    }

    public void setRequest(SearchingRequest request) {
        this.request = request;
    }

    public Set<TourAdvanced> getTours() {
        return tours;
    }

    public void setTours(Set<TourAdvanced> tours) {
        this.tours = tours;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.id);
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
        final TourAdvancedSession other = (TourAdvancedSession) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "TourAdvancedSession{" + "id=" + id + ", request=" + request + ", tours=" + tours + '}';
    }

}
