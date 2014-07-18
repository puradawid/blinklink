package pl.edu.pb.blinklink.model.beans;

import java.util.Collection;

import javax.ejb.Local;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;

@Local
public interface BlinkGroupDao extends DaoInterface<BlinkGroup> {
	public Collection<BlinkGroup> findGroupsThatUserRegistered(BlinkUser user);
	public BlinkGroup findByName(String groupName);
}
