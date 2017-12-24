package com.redlongcitywork.easytourlite.model.session;

import com.fasterxml.jackson.annotation.JsonView;
import com.redlongcitywork.easytourlite.json.view.HotToursSessionView;
import com.redlongcitywork.easytourlite.model.HotToursRequest;
import com.redlongcitywork.easytourlite.model.Tour;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 *
 * @author redlongcity 
 * 24/12/2017 
 * model for keeping info about unit of request
 * from client
 */
@Entity
@Table(name = "hot_tours_sessions")
public class HotToursSession {

    @JsonView(HotToursSessionView.class)
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "session_id", unique = true, nullable = false)
    private Integer id;

    @JsonView(HotToursSessionView.class)
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "request_id", nullable = false)
    private HotToursRequest request;

    @JsonView(HotToursSessionView.class)
    @OneToMany(fetch = FetchType.LAZY, mappedBy = "session",
            cascade = CascadeType.ALL)
    private Set<Tour> toursSet = new HashSet<Tour>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public HotToursRequest getRequest() {
        return request;
    }

    public void setRequest(HotToursRequest request) {
        this.request = request;
    }

    public Set<Tour> getToursSet() {
        return toursSet;
    }

    public void setToursSet(Set<Tour> toursSet) {
        this.toursSet = toursSet;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.id);
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
        final HotToursSession other = (HotToursSession) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "HotToursSession{" + "id=" + id + ", request=" + request + ", toursSet=" + toursSet + '}';
    }

}
