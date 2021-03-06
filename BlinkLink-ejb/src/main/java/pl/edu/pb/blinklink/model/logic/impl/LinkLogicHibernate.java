package pl.edu.pb.blinklink.model.logic.impl;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.NoSuchElementException;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.GroupLink;
import pl.edu.pb.blinklink.model.Link;
import pl.edu.pb.blinklink.model.Rate;
import pl.edu.pb.blinklink.model.Rating;
import pl.edu.pb.blinklink.model.UserLink;
import pl.edu.pb.blinklink.model.beans.BlinkGroupDao;
import pl.edu.pb.blinklink.model.beans.GroupLinkDao;
import pl.edu.pb.blinklink.model.beans.LinkDao;
import pl.edu.pb.blinklink.model.beans.RateDao;
import pl.edu.pb.blinklink.model.beans.UserLinkDao;
import pl.edu.pb.blinklink.model.logic.LinkLogic;
import pl.edu.pb.blinklink.model.logic.exceptions.PostingLinkException;

/**
 * Hibernated LinkLogic
 * @author dawid
 */
@Stateless(name = "LinkLogicHibernate")
@Local(LinkLogic.class)
public class LinkLogicHibernate implements LinkLogic {
    
    @EJB(beanName="LinkDaoHibernate")
    LinkDao ld;
    
    @EJB(beanName="UserLinkDaoHibernate")
    UserLinkDao uld;
    
    @EJB(beanName="GroupLinkDaoHibernate")
    GroupLinkDao gld;
    
    @EJB(beanName="BlinkGroupDaoHibernate")
    BlinkGroupDao bgd;
    
    @EJB(beanName="RateDaoHibernate")
    RateDao rateDao;
    
    public LinkLogicHibernate() {}
    
    private Collection<Link> getLink(String referer)
    {
    	return ld.find(referer);
    }
    
    private boolean isLinkExist(BlinkUser user, UserLink link)
    {
        return !uld.find(user, link.getLink().getReferer()).isEmpty();
    }
    
    private boolean isLinkExist(BlinkUser user, GroupLink link)
    {
        return !gld.findAll(link.getGroup(), link.getLink().getReferer()).isEmpty();
    }
    
    @Override
    public Collection<UserLink> getUserLinksPast(BlinkUser user, Date date) {
    	return uld.findAllSince(user, date);
    }

    @Override
    public Collection<GroupLink> getGroupLinksPast(BlinkUser user, Date date) {
    	Collection<GroupLink> result = new LinkedList<GroupLink>();
    	Collection<BlinkGroup> userGroups = bgd.findGroupsThatUserRegistered(user);
    	for(BlinkGroup group : userGroups)
    		result.addAll(gld.findAllSince(group, date));
    	return result;
    }

    @Override
    public void postLink(BlinkUser user, UserLink link) throws PostingLinkException {
        
        link.setOwner(user);
        link.setCreated(new Date());
        
        Collection<Link> rawLink = getLink(link.getLink().getReferer());
        
        if(!rawLink.isEmpty())
        {
            link.setLink(rawLink.iterator().next());
            if(isLinkExist(user, link))
            {
                throw new PostingLinkException("Already posted by this user");
            }
        }
        else
            ld.create(link.getLink());
        uld.create(link);
    }
    
    @Override
    public void postLink(BlinkUser user, GroupLink link, String group) throws PostingLinkException {
        
        link.setAuthor(user);
        link.setCreated(new Date());
        
        BlinkGroup bg = null;
        try {
            bg = bgd.findByName(group);
            if(bg == null)
            	throw new NoSuchElementException();
            link.setGroup(bg);
        } catch (Exception e) //change it!
        {
            throw new PostingLinkException("Target group not exists.");
        }
        
        List<Link> rawLink = new LinkedList<Link>(getLink(link.getLink().getReferer())); //broken glass
        //TODO: i need refactor!
        
        if(!rawLink.isEmpty())
        {
            link.setLink(rawLink.get(0));
            if(isLinkExist(user, link))
                throw new PostingLinkException("Already posted by this user");
        }
        else
            ld.create(link.getLink());
        gld.create(link);
    }

    @Override
    public Collection<Link> searchLinks(String username, String groupname, Date from, Date to, String content) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String calculateSum(BlinkUser user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

	@Override
	public void rateLink(Rate rate, long targetId) throws RequestProcessException {
		//read rate is it okay
		if(rate.getRate() < 0 || rate.getRate() > 10)
			throw new RequestProcessException();
		//load object from targetId
		GroupLink link = null;
		try {
			link = gld.findById(targetId);
		} catch (NoSuchElementException e) {
			throw new RequestProcessException();
		}
		//check poster have access to targetId object
		if(!bgd.findGroupsThatUserRegistered(rate.getRater()).contains(link.getGroup()))
			throw new RequestProcessException();
		
		rateDao.create(rate);
		link.getRating().getRates().add(rate);
		gld.update(link);
	}

	@Override
	public Rating getRating(long targetId) throws RequestProcessException {
		try {
			return gld.findById(targetId).getRating();
		} catch (NoSuchElementException e) {
			throw new RequestProcessException();
		}
	}
    
}
