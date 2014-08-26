package pl.edu.pb.blinklink.model.beans;

import java.util.Collection;

import pl.edu.pb.blinklink.model.Link;

public interface LinkDao extends DaoInterface<Link> {
	public Collection<Link> find(String referer);
}
