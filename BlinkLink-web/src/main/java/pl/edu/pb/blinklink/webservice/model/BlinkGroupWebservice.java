package pl.edu.pb.blinklink.webservice.model;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import pl.edu.pb.blinklink.model.BlinkGroup;

/**
 * 
 * @author dawid
 */
@XmlRootElement
public class BlinkGroupWebservice {
    
    private String name;
    
    private String description;
    
    public static BlinkGroupWebservice getInstance(BlinkGroup group)
    {
        BlinkGroupWebservice result = new BlinkGroupWebservice();
        result.setName(group.getName());
        result.setDescription(group.getDescription());
        return result;
    }
    
    public static BlinkGroup convert(BlinkGroupWebservice bgw)
    {
        BlinkGroup bg = new BlinkGroup();
        bg.setName(bgw.getName());
        return bg;
    }
    
    @XmlElement(name = "name")
    public String getName() { return this.name; }
    
    public void setName(String name) { this.name = name; }
    
    @XmlElement(name = "description")
    public String getDescription() { return this.description; }
    
    public void setDescription(String description) 
    { 
        this.description = description; 
    }
    
}
