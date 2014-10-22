package pl.edu.pb.blinklink.model.beans.mock;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import java.util.NoSuchElementException;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.GroupLink;
import pl.edu.pb.blinklink.model.beans.GroupLinkDao;

public class GroupLinkDaoMock extends AbstractDaoMock<GroupLink> implements GroupLinkDao
{

	@Override
	public Collection<GroupLink> findAll(BlinkGroup group) {
		Collection<GroupLink> result = new LinkedList<GroupLink>();
		for(GroupLink link : dataStorage)
			if(link.getGroup().getName().equals(group.getName()))
				result.add(link);
		return result;
	}

	@Override
	public Collection<GroupLink> findAllSince(BlinkGroup group, Date since) {
		Collection<GroupLink> result = new LinkedList<GroupLink>();
		Collection<GroupLink> links = findAll(group);
		
		for(GroupLink link : links)
			if(link.getCreated().after(since))
				result.add(link);
		return result;
	}

	@Override
	public Collection<GroupLink> findAll(BlinkGroup group, String referer) {
		Collection<GroupLink> result = new LinkedList<GroupLink>();
		Collection<GroupLink> links = findAll(group);
		
		for(GroupLink link : links)
			if(link.getLink().getReferer().equals(referer))
				result.add(link);
		return result;
	}

	@Override
	public GroupLink findById(long id) throws NoSuchElementException{
		for(GroupLink link : dataStorage)
			if(link.getId() == id)
				return link;
		throw new NoSuchElementException("No link with id = " + Long.toString(id));
	}
}
