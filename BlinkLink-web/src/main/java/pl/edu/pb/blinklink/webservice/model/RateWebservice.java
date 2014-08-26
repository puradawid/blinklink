package pl.edu.pb.blinklink.webservice.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.edu.pb.blinklink.model.Rate;

/**
 * Class that wraps pl.edu.pb.blinklink.model.Comment object to JAX-B instance.
 * @author dawid
 */
@XmlRootElement
public class RateWebservice {
    
    private String comment;
    
    private String rater;
    
    private Date time;
    
    private int rate;
    
    
    public RateWebservice()
    {
        comment = null;
        rater = null;
        time = new Date();
        rate = 0;
    }
    
    public RateWebservice(Rate rate)
    {
        this.comment = rate.getComment();
        this.rater = rate.getRater().getName();
        this.time = (Date)rate.getTime().clone();
        this.rate = rate.getRate();
    }
    
    @XmlElement(name="comment")
    public String getComment() {
        return comment;
    }
    
    @XmlElement(name="rater")
    public String getRater() {
        return rater;
    }
    
    @XmlElement(name="time")
    public Date getTime() {
        return time;
    }
    
    @XmlElement(name="rate")
    public int getRate() {
        return rate;
    }
    
    
}
