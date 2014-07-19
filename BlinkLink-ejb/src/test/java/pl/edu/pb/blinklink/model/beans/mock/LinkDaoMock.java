package pl.edu.pb.blinklink.model.beans.mock;

import java.util.Collection;
import java.util.LinkedList;

import pl.edu.pb.blinklink.model.Link;
import pl.edu.pb.blinklink.model.beans.LinkDao;

public class LinkDaoMock extends AbstractDaoMock<Link> implements LinkDao {
	
	@Override
	public Collection<Link> find(String referer) {
		Collection<Link> result = new LinkedList<Link>();
		for(Link link : dataStorage)
			if(link.getReferer().equals(referer))
				result.add(link);
		return result;
	}

}
