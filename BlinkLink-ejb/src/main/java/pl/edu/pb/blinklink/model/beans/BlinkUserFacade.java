package pl.edu.pb.blinklink.model.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
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
    
}
