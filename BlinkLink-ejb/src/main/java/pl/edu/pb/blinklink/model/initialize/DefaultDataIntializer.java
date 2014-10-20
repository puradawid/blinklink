package pl.edu.pb.blinklink.model.initialize;

import java.util.Date;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.ejb.Singleton;
import javax.ejb.Startup;
import javax.enterprise.context.ApplicationScoped;
import javax.transaction.UserTransaction;

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
@ApplicationScoped
public class DefaultDataIntializer {
    
    @EJB
    BlinkGroupFacade bgf;
    
    @EJB
    BlinkUserFacade buf;
    
    @EJB
    UserLinkFacade ulf;
    
    @EJB
    LinkFacade lf;
    
    private static String IS_USER_EXISTS = "SELECT blinkuser FROM BlinkUser blinkuser WHERE blinkuser.email = :username"; 
    
    @PostConstruct
    public void init()
    {
                Logger.getAnonymousLogger().warning("Data will be initialized!");
                BlinkUser bu = createDefaultUser();
                BlinkGroup bg = createDefaultGroup();
                Link l = createDefaultLink();
                UserLink ul = createDefaultUserLink();
                
                if(buf.find(bu.getEmail()) != null)
                    return;

                bg.addUser(bu);
                ul.setLink(l);
                ul.setOwner(bu);
                
                try {
                    buf.create(bu);
                    lf.create(l);
                    bgf.create(bg);
                    ulf.create(ul);
                } catch (Exception e)
                {
                    Logger.getAnonymousLogger().warning(e.getMessage());
                }
        
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
