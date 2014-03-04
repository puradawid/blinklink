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
    
    public Rate(BlinkUser rater, int rate)
    {
        this.rater = rater;
        this.rate = rate;
    }
    
    public Rate()
    {
        this.rater = null;
        this.rate = 0;
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
}
