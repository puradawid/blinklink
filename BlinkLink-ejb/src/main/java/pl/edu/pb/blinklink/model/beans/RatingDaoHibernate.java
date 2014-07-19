package pl.edu.pb.blinklink.model.beans;

import java.util.Collection;

import javax.ejb.EJB;
import javax.ejb.Stateless;

import pl.edu.pb.blinklink.model.Rating;

@Stateless
public class RatingDaoHibernate implements RatingDao{

	@EJB
	RatingFacade rf;
	
	@Override
	public void create(Rating entity) {
		rf.create(entity);
	}

	@Override
	public void remove(Rating entity) {
		rf.remove(entity);
	}

	@Override
	public void update(Rating entity) {
		rf.edit(entity);
	}

	@Override
	public Collection<Rating> findAll() {
		return rf.findAll();
	}

}
