package pl.edu.pb.blinklink.model.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
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
    
    public BlinkGroup getGroupByName(String name)
    {
        Query q = em.createQuery("SELECT grp FROM BlinkGroup grp WHERE name =  '" + name + "'");
        return (BlinkGroup)q.getSingleResult();
    }
    
}
