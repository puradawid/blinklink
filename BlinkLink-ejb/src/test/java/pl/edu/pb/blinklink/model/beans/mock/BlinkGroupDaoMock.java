package pl.edu.pb.blinklink.model.beans.mock;

import java.util.Collection;
import java.util.LinkedList;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.beans.BlinkGroupDao;

public class BlinkGroupDaoMock extends AbstractDaoMock<BlinkGroup> implements BlinkGroupDao {

	@Override
	public Collection<BlinkGroup> findGroupsThatUserRegistered(BlinkUser user) {
		Collection<BlinkGroup> result = new LinkedList<BlinkGroup>();
		for(BlinkGroup group : dataStorage)
			if(group.getRegistered().contains(user))
				result.add(group);
		return result;
	}

	@Override
	public BlinkGroup findByName(String groupName) {
		for(BlinkGroup group : dataStorage)
			if(group.getName().equals(groupName))
				return group;
		return null;
	}

}
