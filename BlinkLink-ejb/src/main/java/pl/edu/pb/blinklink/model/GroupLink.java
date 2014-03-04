package pl.edu.pb.blinklink.model;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Entity;
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

}
