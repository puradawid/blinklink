package pl.edu.pb.blinklink.model.beans;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import pl.edu.pb.blinklink.model.Link;

@Local(LinkDao.class)
@Stateless(name="LinkDaoHibernate")
public class LinkDaoHibernate implements LinkDao {

	@EJB
	LinkFacade lf;
	
	@Override
	public void create(Link entity) {
		lf.create(entity);
	}

	@Override
	public void remove(Link entity) {
		lf.remove(entity);
	}

	@Override
	public void update(Link entity) {
		lf.remove(entity);
	}

	@Override
	public Collection<Link> findAll() {
		return lf.findAll();
	}

	
	private static String findLink = "SELECT l FROM Link l  WHERE l.referer = :referer";
	@Override
	public Collection<Link> find(String referer) {
		Map<String, Object> keys = new HashMap<String, Object>();
		keys.put("referer", referer);
		return lf.select(findLink, keys);
	}

}
