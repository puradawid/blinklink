package pl.edu.pb.blinklink.model;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Link class represents one link in system.
 * 
 * @author Dawid Pura
 */
@Entity
public class Link implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected long id;
    
    /** Describes actual link, where it will be moved **/
    protected String referer;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date checked;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;
    
    protected boolean deleted;
    
    protected Rating rating;
    
    protected boolean active;
    
    public Long getId()
    {
        return id;
    }
    
    public void setId(Long id)
    {
        this.id = id;
    }

    public String getReferer() {
        return referer;
    }

    public void setReferer(String referer) {
        this.referer = referer;
    }

    public Date getChecked() {
        return checked;
    }

    public void setChecked(Date checked) {
        this.checked = checked;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public Rating getRating() {
        return rating;
    }

    public void setRating(Rating rating) {
        this.rating = rating;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }
    
    //constructor
    public Link() {}
    
    //constructor for unmanaged
    public Link(String referer)
    {
        this.referer = referer;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (int) (this.id ^ (this.id >>> 32));
        hash = 67 * hash + (this.referer != null ? this.referer.hashCode() : 0);
        hash = 67 * hash + (this.checked != null ? this.checked.hashCode() : 0);
        hash = 67 * hash + (this.created != null ? this.created.hashCode() : 0);
        hash = 67 * hash + (this.deleted ? 1 : 0);
        hash = 67 * hash + (this.rating != null ? this.rating.hashCode() : 0);
        hash = 67 * hash + (this.active ? 1 : 0);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Link other = (Link) obj;
        if (this.id != other.id) {
            return false;
        }
        if ((this.referer == null) ? (other.referer != null) : !this.referer.equals(other.referer)) {
            return false;
        }
        if (this.checked != other.checked && (this.checked == null || !this.checked.equals(other.checked))) {
            return false;
        }
        if (this.created != other.created && (this.created == null || !this.created.equals(other.created))) {
            return false;
        }
        if (this.deleted != other.deleted) {
            return false;
        }
        if (this.rating != other.rating && (this.rating == null || !this.rating.equals(other.rating))) {
            return false;
        }
        if (this.active != other.active) {
            return false;
        }
        return true;
    }
    
    
}
