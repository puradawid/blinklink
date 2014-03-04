package pl.edu.pb.blinklink.model.logic.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.GroupLink;
import pl.edu.pb.blinklink.model.Link;
import pl.edu.pb.blinklink.model.UserLink;
import pl.edu.pb.blinklink.model.beans.GroupLinkFacade;
import pl.edu.pb.blinklink.model.beans.LinkFacade;
import pl.edu.pb.blinklink.model.beans.UserLinkFacade;
import pl.edu.pb.blinklink.model.logic.LinkLogic;
import pl.edu.pb.blinklink.model.logic.exceptions.PostingLinkException;

/**
 * Hibernated LinkLogic
 * @author dawid
 */
@EJB(name = "LinkLogicHibernate", beanInterface = LinkLogic.class)
public class LinkLogicHibernate implements LinkLogic {
    
    @EJB
    LinkFacade lf;
    
    @EJB
    UserLinkFacade ulf;
    
    @EJB
    GroupLinkFacade glf;
    
    public LinkLogicHibernate()
    {
        
    }
    
    /**
     * Constructor to avoid beans injection (for testing for example).
     * @param lf - modified LinkFacade
     * @param ulf - modified UserLinkFacade
     * @param glf - modified GroupLinkFacade
     */
    LinkLogicHibernate(LinkFacade lf, UserLinkFacade ulf, GroupLinkFacade glf)
    {
        this.lf = lf;
        this.ulf = ulf;
        this.glf = glf;
    }
    
    private List<Link> getLink(String referer)
    {
        Map<String, Object> params = new HashMap<String,Object>();
        params.put("referer", referer);
        
        return lf.select(
                "SELECT l FROM Link l  WHERE l.referer = :referer", params);
    }
    
    private boolean isLinkExist(BlinkUser user, UserLink link)
    {
        Map<String, Object> params = new HashMap<String,Object>();
        
        params.put("user", user);
        params.put("referer", link.getLink());
        
        return !lf.select("SELECT l FROM UserLink l "
                + "WHERE l.user = :user AND l.link = :referer", params).isEmpty();
    }
    
    @Override
    public Collection<UserLink> getUserLinksPast(BlinkUser user, Date date) {
        Map<String, Object> params = new HashMap<String,Object> ();
        params.put("email", user.getEmail());
        params.put("date", date);
        return ulf.select(
                "SELECT l FROM userlink WHERE l.owner.email = :email "
                        + "AND l.created > :date", params);
    }

    @Override
    public Collection<GroupLink> getGroupLinksPast(BlinkUser user, Date date) {
        Map<String, Object> params = new HashMap<String,Object> ();
        params.put("email", user.getEmail());
        params.put("date", date);
        return glf.select(
                "SELECT l FROM grouplink l WHERE l.created > :date "
                        + "AND EXISTS "
                        + "(SELECT g FROM blinkgroup g JOIN blinkuser u "
                        + "WHERE g = l.group AND u.registered.email = :email)",
                params);
    }

    @Override
    public void postLink(BlinkUser user, UserLink link) throws PostingLinkException {
        
        link.setOwner(user);
        link.setCreated(new Date());
        
        List<Link> rawLink = getLink(link.getLink().getReferer());
        
        if(!rawLink.isEmpty())
        {
            link.setLink(rawLink.get(0));
            if(isLinkExist(user, link))
            {
                throw new PostingLinkException("Already posted by this user");
            }
        }
        else
            lf.create(link.getLink());
    }

    @Override
    public Collection<Link> searchLinks(String username, String groupname, Date from, Date to, String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String calculateSum(BlinkUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
