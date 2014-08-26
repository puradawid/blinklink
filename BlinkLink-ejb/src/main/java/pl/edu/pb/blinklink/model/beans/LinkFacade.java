/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.pb.blinklink.model.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.edu.pb.blinklink.model.Link;

/**
 *
 * @author dawid
 */
@Stateless
public class LinkFacade extends AbstractFacade<Link> {
    @PersistenceContext(unitName = "BlinkLinkServer-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LinkFacade() {
        super(Link.class);
    }
    
}
