package pl.edu.pb.blinklink.model.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import pl.edu.pb.blinklink.model.BlinkGroup;

/**
 *
 * @author dawid
 */
@Stateless
public class BlinkGroupFacade extends AbstractFacade<BlinkGroup> {
    @PersistenceContext(unitName = "BlinkLinkServer-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BlinkGroupFacade() {
        super(BlinkGroup.class);
    }
    
}
