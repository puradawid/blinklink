package pl.edu.pb.blinklink.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Group which can be a adressed for spreading.
 *
 * @author dawid
 */
@Entity
public class BlinkGroup implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;

    protected String name;

    protected String description;

    @ManyToMany
    protected Collection<BlinkUser> registered;

    @ManyToOne
    protected BlinkUser owner;

    @Temporal(TemporalType.TIMESTAMP)
    protected Date created;

    //getters and setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Collection<BlinkUser> getUsers() {
        return registered;
    }

    public void removeUser(BlinkUser u) {
        registered.remove(u);
    }

    public void addUser(BlinkUser u) {
        registered.add(u);
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Collection<BlinkUser> getRegistered() {
        return registered;
    }

    public void setRegistered(Collection<BlinkUser> registered) {
        this.registered = registered;
    }

    public BlinkUser getOwner() {
        return owner;
    }

    public void setOwner(BlinkUser owner) {
        this.owner = owner;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public BlinkGroup()
    {
        this.registered = new LinkedList<BlinkUser>();
    }
}
