package pl.edu.pb.blinklink.model.beans.mock;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;

import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.UserLink;
import pl.edu.pb.blinklink.model.beans.UserLinkDao;

public class UserLinkDaoMock extends AbstractDaoMock<UserLink> implements UserLinkDao {

	@Override
	public Collection<UserLink> findAll(BlinkUser group) {
		Collection<UserLink> result = new LinkedList<UserLink>();
		for(UserLink link : dataStorage)
			if(link.getOwner().equals(group))
				result.add(link);
		return result;
	}

	@Override
	public Collection<UserLink> findAllSince(BlinkUser group, Date since) {
		Collection<UserLink> result = new LinkedList<UserLink>();
		Collection<UserLink> links = findAll(group);
		for(UserLink link : links)
			if(link.getCreated().after(since))
				result.add(link);
		return result;
	}

	@Override
	public Collection<UserLink> find(BlinkUser user, String referer) {
		Collection<UserLink> result = new LinkedList<UserLink>();
		for(UserLink link : dataStorage)
			if(link.getLink().getReferer().equals(referer))
				result.add(link);
		return result;
	}
	
}
