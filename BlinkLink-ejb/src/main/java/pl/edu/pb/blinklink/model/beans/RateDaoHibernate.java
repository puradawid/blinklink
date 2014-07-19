package pl.edu.pb.blinklink.model.beans;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import pl.edu.pb.blinklink.model.Rate;

@Local(RateDao.class)
@Stateless(name="RateDaoHibernate")
public class RateDaoHibernate implements RateDao{
	
	@EJB
	RateFacade rf;
	
	@Override
	public void create(Rate entity) {
		rf.create(entity);
	}

	@Override
	public void remove(Rate entity) {
		rf.remove(entity);
	}

	@Override
	public void update(Rate entity) {
		rf.edit(entity);
	}

	@Override
	public Collection<Rate> findAll() {
		return rf.findAll();
	}

}
