package pl.edu.pb.blinklink.model.beans;

import java.util.Collection;
import java.util.Date;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.GroupLink;

public interface GroupLinkDao extends DaoInterface<GroupLink> {
	Collection<GroupLink> findAll(BlinkGroup group);
	Collection<GroupLink> findAllSince(BlinkGroup group, Date since);
	Collection<GroupLink> findAll(BlinkGroup group, String referer);
}
