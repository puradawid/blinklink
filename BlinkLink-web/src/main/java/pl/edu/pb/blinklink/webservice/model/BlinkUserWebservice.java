package pl.edu.pb.blinklink.webservice.model;

import java.io.Serializable;
import java.util.Collection;
import java.util.LinkedList;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;

/**
 *
 * @author dawid
 */
@XmlRootElement
public class BlinkUserWebservice implements Serializable {
    
    public static BlinkUserWebservice getInstance(BlinkUser user)
    {
        BlinkUserWebservice buw = new BlinkUserWebservice();
        buw.email = user.getEmail();
        buw.name = user.getName();
        buw.surname = user.getSurname();
        buw.groups = getGroups(user);
        //more fields needed
        return buw;
    }
    
    private String email;
    private String name, surname;
    private Collection<String> groups;
    
    private BlinkUserWebservice() {};
    
    @XmlElement
    public String getEmail()
    {
        return email;
    }
    
    @XmlElement
    public String getName()
    {
        return name;
    }
    
    private static Collection<String> getGroups(BlinkUser user)
    {
        Collection<String> groups = new LinkedList<String>();
        for(BlinkGroup group : user.getGroups())
        {
            groups.add(group.getName());
        }
        return groups;
    }
}
