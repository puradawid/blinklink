package pl.edu.pb.blinklink.model.beans;

import java.util.Collection;
import java.util.Date;

import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.UserLink;

public interface UserLinkDao extends DaoInterface<UserLink> {
	Collection<UserLink> findAll(BlinkUser group);
	Collection<UserLink> findAllSince(BlinkUser group, Date since);
	Collection<UserLink> find(BlinkUser user, String referer);
}
