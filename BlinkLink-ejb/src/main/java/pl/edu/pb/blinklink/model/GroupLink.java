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
 * Link which belongs to group.
 *
 * @author dawid
 */
@Entity
public class GroupLink implements Serializable {

    //basic infomation
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    protected Link link;

    @ManyToOne
    protected BlinkGroup group;

    //additional infomation
    @Temporal(TemporalType.TIMESTAMP)
    Date created;

    boolean deleted;

    String description;
    
    @ManyToOne
    protected BlinkUser author;

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Link getLink() {
        return link;
    }

    public void setLink(Link link) {
        this.link = link;
    }

    public BlinkGroup getGroup() {
        return group;
    }

    public void setGroup(BlinkGroup group) {
        this.group = group;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public BlinkUser getAuthor() {
        return author;
    }

    public void setAuthor(BlinkUser author) {
        this.author = author;
    }

    public GroupLink() {}
    
    public GroupLink(BlinkUser author, Link l, String description)
    {
        this.author = author;
        this.link = l;
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 67 * hash + (this.link != null ? this.link.hashCode() : 0);
        hash = 67 * hash + (this.group != null ? this.group.hashCode() : 0);
        hash = 67 * hash + (this.created != null ? this.created.hashCode() : 0);
        hash = 67 * hash + (this.deleted ? 1 : 0);
        hash = 67 * hash + (this.description != null ? this.description.hashCode() : 0);
        hash = 67 * hash + (this.author != null ? this.author.hashCode() : 0);
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
        final GroupLink other = (GroupLink) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.link != other.link && (this.link == null || !this.link.equals(other.link))) {
            return false;
        }
        if (this.group != other.group && (this.group == null || !this.group.equals(other.group))) {
            return false;
        }
        if (this.created != other.created && (this.created == null || !this.created.equals(other.created))) {
            return false;
        }
        if (this.deleted != other.deleted) {
            return false;
        }
        if ((this.description == null) ? (other.description != null) : !this.description.equals(other.description)) {
            return false;
        }
        if (this.author != other.author && (this.author == null || !this.author.equals(other.author))) {
            return false;
        }
        return true;
    }
    
}
