package pl.edu.pb.blinklink.model.beans;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.GroupLink;
import pl.edu.pb.blinklink.model.UserLink;

@Local(UserLinkDao.class)
@Stateless(name="UserLinkDaoHibernate")
public class UserLinkDaoHibernate implements UserLinkDao{

	@EJB
	UserLinkFacade ulf;
	
	@Override
	public void create(UserLink entity) {
		ulf.create(entity);
	}

	@Override
	public void remove(UserLink entity) {
		ulf.remove(entity);
	}

	@Override
	public void update(UserLink entity) {
		ulf.edit(entity);
	}

	@Override
	public Collection<UserLink> findAll() {
		return ulf.findAll();
	}

	@Override
	public Collection<UserLink> findAll(BlinkUser user) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		
		return ulf.select("SELECT l FROM UserLink l WHERE l.owner = :user", params);
	}

	@Override
	public Collection<UserLink> findAllSince(BlinkUser user, Date since) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("user", user);
		params.put("date", since);
		
		return ulf.select("SELECT l FROM UserLink l WHERE l.owner = :user "
                + "AND l.created > :date", params);
	}

	@Override
	public Collection<UserLink> find(BlinkUser user, String referer) {
		Map<String, Object> params = new HashMap<String,Object>();
        
        params.put("user", user);
        params.put("referer", referer);
        
        return ulf.select("SELECT l FROM UserLink l "
                + "WHERE l.owner = :user AND l.link.referer = :referer", params);
	}

}
