package pl.edu.pb.blinklink.model.beans;

import java.util.Collection;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;

@Local(BlinkGroupDao.class)
@Stateless(name = "BlinkGroupDaoHibernate")
public class BlinkGroupDaoHibernate implements BlinkGroupDao {
	
	static String getGroupByNameQuery = 
            "SELECT group FROM BlinkGroup group WHERE group.name = :name";
	
	@EJB
	BlinkGroupFacade bgf;
	
	@Override
	public void create(BlinkGroup entity) {
		bgf.create(entity);
	}

	@Override
	public void remove(BlinkGroup entity) {
		bgf.remove(entity);
	}

	@Override
	public void update(BlinkGroup entity) {
		bgf.edit(entity);
	}

	@Override
	public Collection<BlinkGroup> findAll() {
		return bgf.findAll();
	}

	private static String selectGroupsByUserQuery = "SELECT usr.groups FROM BlinkUser usr WHERE usr.email = :email";
	@Override
	public Collection<BlinkGroup> findGroupsThatUserRegistered(BlinkUser user) {
		if(user == null) return new LinkedList<BlinkGroup>();
		Map<String, Object> params = new HashMap<String,Object> ();
        params.put("email", user.getEmail());
        Collection<BlinkGroup> groups = bgf.select(
                selectGroupsByUserQuery,
                params);
		return groups;
	}

	@Override
	public BlinkGroup findByName(String groupName) {
		Map<String, Object> keys = new HashMap<String,Object>();
		keys.put("name", groupName);
		List<BlinkGroup> result = bgf.select(getGroupByNameQuery, keys);
		return (result.size() == 0 ? result.get(0) : null);
	}

}
