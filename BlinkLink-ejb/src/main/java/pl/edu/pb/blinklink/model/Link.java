package pl.edu.pb.blinklink.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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
    
    
}
