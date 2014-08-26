package pl.edu.pb.blinklink.model.beans;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.GroupLink;

@Local(GroupLinkDao.class)
@Stateless(name="GroupLinkDaoHibernate")
public class GroupLinkDaoHibernate implements GroupLinkDao {

	@EJB
	GroupLinkFacade glf;
	
	@Override
	public void create(GroupLink entity) {
		glf.create(entity);
	}

	@Override
	public void remove(GroupLink entity) {
		glf.remove(entity);
	}

	@Override
	public void update(GroupLink entity) {
		glf.edit(entity);
	}

	@Override
	public Collection<GroupLink> findAll() {
		return glf.findAll();
	}

	private static String selectGroupLinksQuery = "SELECT grplnk FROM GroupLink grplnk WHERE grplnk.group = :group";
	@Override
	public Collection<GroupLink> findAll(BlinkGroup group) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("group", group);
		return glf.select(selectGroupLinksQuery, params);
	}

	private static String selectGroupLinksSinceQuery = "SELECT grplnk FROM GroupLink grplnk WHERE grplnk.group = :group AND grplnk.created > :date";
	@Override
	public Collection<GroupLink> findAllSince(BlinkGroup group, Date since) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("date", since);
		params.put("group", group);
        return glf.select(selectGroupLinksSinceQuery, params);
	}

	private static String selectGroupLinkThatsGroupOwnQuery = "SELECT grplnk FROM GroupLink grplnk WHERE grplnk.group = :group AND grplnk.link.referer = :referer";
	@Override
	public Collection<GroupLink> findAll(BlinkGroup group, String referer) {
		Map<String, Object> params = new HashMap<String, Object>();
		params.put("group", group);
		params.put("referer", referer);
		
		return glf.select(selectGroupLinkThatsGroupOwnQuery, params);
	}

}
