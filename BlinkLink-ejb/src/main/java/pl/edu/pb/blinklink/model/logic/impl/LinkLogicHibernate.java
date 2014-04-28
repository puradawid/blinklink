package pl.edu.pb.blinklink.model.logic.impl;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;
import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.GroupLink;
import pl.edu.pb.blinklink.model.Link;
import pl.edu.pb.blinklink.model.UserLink;
import pl.edu.pb.blinklink.model.beans.BlinkGroupFacade;
import pl.edu.pb.blinklink.model.beans.GroupLinkFacade;
import pl.edu.pb.blinklink.model.beans.LinkFacade;
import pl.edu.pb.blinklink.model.beans.UserLinkFacade;
import pl.edu.pb.blinklink.model.logic.LinkLogic;
import pl.edu.pb.blinklink.model.logic.exceptions.PostingLinkException;

/**
 * Hibernated LinkLogic
 * @author dawid
 */
@Stateless(name = "LinkLogicHibernate")
@Local(LinkLogic.class)
public class LinkLogicHibernate implements LinkLogic {
    
    @EJB
    LinkFacade lf;
    
    @EJB
    UserLinkFacade ulf;
    
    @EJB
    GroupLinkFacade glf;
    
    @EJB
    BlinkGroupFacade bgf;
    
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
                + "WHERE l.owner = :user AND l.link = :referer", params).isEmpty();
    }
    
    private boolean isLinkExist(BlinkUser user, GroupLink link)
    {
        Map<String, Object> params = new HashMap<String,Object>();
        
        params.put("group", link.getGroup());
        params.put("referer", link.getLink());
        
        return !lf.select("SELECT l FROM GroupLink l "
                + "WHERE l.group = :group AND l.link = :referer", params).isEmpty();
    }
    
    @Override
    public Collection<UserLink> getUserLinksPast(BlinkUser user, Date date) {
        Map<String, Object> params = new HashMap<String,Object> ();
        params.put("email", user.getEmail());
        params.put("date", date);
        return ulf.select(
                "SELECT l FROM UserLink l WHERE l.owner.email = :email "
                        + "AND l.created > :date", params);
    }

    @Override
    public Collection<GroupLink> getGroupLinksPast(BlinkUser user, Date date) {
        Map<String, Object> params = new HashMap<String,Object> ();
        params.put("email", user.getEmail());
        
        Collection<GroupLink> result = new LinkedList<GroupLink>();
        
        Collection<BlinkGroup> groups = bgf.select(
                "SELECT usr.groups FROM BlinkUser usr WHERE usr.email = :email",
                params);
        params.remove("email");
        params.put("date", date);
        for(BlinkGroup g : groups)
        {
            params.put("group", g);
            List<GroupLink> glst = glf.select("SELECT grplnk FROM GroupLink grplnk WHERE grplnk.group = :group AND grplnk.created > :date", params);
            for(GroupLink gl : glst)
                result.add(gl);
        }
        
        return result;
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
            ulf.create(link);
        }
        else
            lf.create(link.getLink());
    }
    
    @Override
    public void postLink(BlinkUser user, GroupLink link, String group) throws PostingLinkException {
        
        link.setAuthor(user);
        link.setCreated(new Date());
        
        BlinkGroup bg = null;
        try {
            bg = bgf.getGroupByName(group);
            link.setGroup(bg);
        } catch (Exception e) //change it!
        {
            throw new PostingLinkException("Target group not exists.");
        }
        
        List<Link> rawLink = getLink(link.getLink().getReferer());
        
        if(!rawLink.isEmpty())
        {
            link.setLink(rawLink.get(0));
            if(isLinkExist(user, link))
            {
                throw new PostingLinkException("Already posted by this user");
            }
            glf.create(link);
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
