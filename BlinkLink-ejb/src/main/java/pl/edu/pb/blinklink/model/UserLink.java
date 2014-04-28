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
 * Link which belongs to user.
 * @author dawid
 */
@Entity
public class UserLink implements Serializable {
    
    //basic information
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    protected BlinkUser owner;
    
    @ManyToOne
    protected BlinkUser sender;
    
    @ManyToOne
    protected Link link;
    
    //additional information
    
    boolean deleted;
    
    String category;
    
    String description;
    
    @Temporal(TemporalType.TIMESTAMP)
    Date created;
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BlinkUser getOwner() {
        return owner;
    }

    public void setOwner(BlinkUser owner) {
        this.owner = owner;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public boolean isDeleted() {
        return deleted;
    }

    public void setDeleted(boolean deleted) {
        this.deleted = deleted;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public BlinkUser getSender() {
        return sender;
    }

    public void setSender(BlinkUser sender) {
        this.sender = sender;
    }

    //default constructor
    public UserLink() {}
    
    //constructor for unmanaged
    public UserLink(BlinkUser user, Link link)
    {
        this.owner = user;
        this.link = link;
        this.created = new Date();
    }
    
    public UserLink(BlinkUser user, BlinkUser sender, Link link)
    {
        this.owner = user;
        this.sender = sender;
        this.link = link;
        this.created = new Date();
    }
}
