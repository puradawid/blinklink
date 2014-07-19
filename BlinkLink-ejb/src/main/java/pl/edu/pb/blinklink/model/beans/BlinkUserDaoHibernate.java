package pl.edu.pb.blinklink.model.beans;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import pl.edu.pb.blinklink.model.BlinkUser;

@Local(BlinkUserDao.class)
@Stateless(name="BlinkUserDaoHibernate")
public class BlinkUserDaoHibernate implements BlinkUserDao{
	
	@EJB
	BlinkUserFacade buf;
	
	@Override
	public void create(BlinkUser entity) {
		buf.create(entity);
	}

	@Override
	public void remove(BlinkUser entity) {
		buf.remove(entity);
	}

	@Override
	public void update(BlinkUser entity) {
		buf.edit(entity);
	}

	@Override
	public Collection<BlinkUser> findAll() {
		return buf.findAll();
	}

	@Override
	public BlinkUser login(String username, String password) {
		BlinkUser user = buf.find(username);
		return (user.getPassword().equals(password) ? user : null);
	}

}
