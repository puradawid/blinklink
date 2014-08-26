package pl.edu.pb.blinklink.webservice.model;

import java.util.Date;

import javax.xml.bind.annotation.XmlElement;

import pl.edu.pb.blinklink.model.GroupLink;
import pl.edu.pb.blinklink.model.UserLink;

/**
 *
 * @author dawid
 */
public class UserLinkWebservice {
    
    private String category;
    
    private String description;
    
    private Date created;
    
    private String referer;
    
    private String sender;
    
    public UserLinkWebservice() {}
    
    public UserLinkWebservice(UserLink ul)
    {
        this.category = ul.getCategory();
        this.description = ul.getDescription();
        this.created = (Date)ul.getCreated().clone();
        this.sender = ul.getOwner().getEmail();
        this.referer = ul.getLink().getReferer();
    }
    
    public UserLinkWebservice(GroupLink ul)
    {
        this.description = ul.getDescription();
        this.created = (Date)ul.getCreated().clone();
        this.sender = ul.getGroup().getName();
        this.referer = ul.getLink().getReferer();
    }
    
    @XmlElement(name="category")
    public String getCategory() {
        return category;
    }
    
    @XmlElement(name="description")
    public String getDescription() {
        return description;
    }
    
    @XmlElement(name="created")
    public Date getCreated() {
        return created;
    }
    
    @XmlElement(name="referer")
    public String getReferer() {
        return referer;
    }

    @XmlElement(name="sender")
    public String getSender() {
        return sender;
    }
}
