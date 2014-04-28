/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.pb.blinklink.model.initialize;

import java.util.Date;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.Link;
import pl.edu.pb.blinklink.model.UserLink;
import pl.edu.pb.blinklink.model.beans.BlinkGroupFacade;
import pl.edu.pb.blinklink.model.beans.BlinkUserFacade;
import pl.edu.pb.blinklink.model.beans.LinkFacade;
import pl.edu.pb.blinklink.model.beans.UserLinkFacade;

/**
 *
 * @author dawid
 */
@Singleton
@Startup
public class DefaultDataIntializer {
    
    @EJB
    BlinkGroupFacade bgf;
    
    @EJB
    BlinkUserFacade buf;
    
    @EJB
    UserLinkFacade ulf;
    
    @EJB
    LinkFacade lf;
    
    @PostConstruct
    public void init()
    {
        BlinkUser bu = createDefaultUser();
        BlinkGroup bg = createDefaultGroup();
        Link l = createDefaultLink();
        UserLink ul = createDefaultUserLink();
        
        bg.addUser(bu);
        ul.setLink(l);
        ul.setOwner(bu);
        
        lf.create(l);
        bgf.create(bg);
        buf.create(bu);
        ulf.create(ul);
        
    }
    
    public BlinkUser createDefaultUser()
    {
        BlinkUser bu = new BlinkUser("user@gmail.com", "password");
        bu.setName("John");
        bu.setSurname("Smith");
        return bu;
    }
    
    public BlinkGroup createDefaultGroup()
    {
        BlinkGroup bg = new BlinkGroup();
        bg.setCreated(new Date());
        bg.setDescription("This is default group for all users");
        bg.setName("blinklink");
        return bg;
    }
    
    public Link createDefaultLink()
    {
        Link l = new Link("http://blinklink.herokuapp.com");
        return l;
    }
    
    public UserLink createDefaultUserLink()
    {
        UserLink ul = new UserLink();
        ul.setCategory("default");
        ul.setDeleted(false);
        ul.setDescription("Default userlink for debugging");
        ul.setCreated(new Date());
        return ul;
    }
    
}
