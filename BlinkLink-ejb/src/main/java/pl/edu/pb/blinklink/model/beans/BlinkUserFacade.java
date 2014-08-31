package pl.edu.pb.blinklink.model.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import pl.edu.pb.blinklink.model.BlinkUser;

/**
 *
 * @author dawid
 */
@Stateless
public class BlinkUserFacade extends AbstractFacade<BlinkUser> {
    @PersistenceContext(unitName = "BlinkLinkServer-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BlinkUserFacade() {
        super(BlinkUser.class);
    }
    
    public static class UserDoesntExists extends Exception {
		private static final long serialVersionUID = 1L;
    }
    
    public BlinkUser getUserByEmail(String email) throws UserDoesntExists
    {
        Query q = em.createQuery("SELECT buser FROM BlinkUser buser WHERE email = '" + email + "'");
        try {
        return (BlinkUser)q.getSingleResult();
        } catch (Exception e) {
        	throw new UserDoesntExists();
        }
    }
    
}
