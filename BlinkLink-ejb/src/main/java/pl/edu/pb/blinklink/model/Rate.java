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
 * Describes rate abouy one object.
 * @author dawid
 */

@Entity
public class Rate implements Serializable {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    protected Long id;
    
    protected BlinkUser rater;
    
    @Temporal(TemporalType.TIMESTAMP)
    protected Date time;
    
    protected int rate;
    
    protected String comment;
    
    //constructors
    
    public Rate(BlinkUser rater, int rate, String comment) {
    	this(rater, rate);
    	this.comment = comment;
    }
    
    public Rate(BlinkUser rater, int rate)
    {
    	this();
        this.rater = rater;
        this.rate = rate;
    }
    
    public Rate()
    {
        this.rater = null;
        this.rate = 0;
        this.time = new Date();
    }
    
    //getters and setters
    
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public BlinkUser getRater() {
        return rater;
    }

    public void setRater(BlinkUser rater) {
        this.rater = rater;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public int getRate() {
        return rate;
    }

    public void setRate(int rate) {
        this.rate = rate;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 61 * hash + (this.id != null ? this.id.hashCode() : 0);
        hash = 61 * hash + (this.rater != null ? this.rater.hashCode() : 0);
        hash = 61 * hash + (this.time != null ? this.time.hashCode() : 0);
        hash = 61 * hash + this.rate;
        hash = 61 * hash + (this.comment != null ? this.comment.hashCode() : 0);
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
        final Rate other = (Rate) obj;
        if (this.id != other.id && (this.id == null || !this.id.equals(other.id))) {
            return false;
        }
        if (this.rater != other.rater && (this.rater == null || !this.rater.equals(other.rater))) {
            return false;
        }
        if (this.time != other.time && (this.time == null || !this.time.equals(other.time))) {
            return false;
        }
        if (this.rate != other.rate) {
            return false;
        }
        if ((this.comment == null) ? (other.comment != null) : !this.comment.equals(other.comment)) {
            return false;
        }
        return true;
    }
    
    
}
