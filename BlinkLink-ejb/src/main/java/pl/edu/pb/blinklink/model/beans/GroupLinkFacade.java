/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.pb.blinklink.model.beans;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import pl.edu.pb.blinklink.model.GroupLink;

/**
 *
 * @author dawid
 */
@Stateless
public class GroupLinkFacade extends AbstractFacade<GroupLink> {
    @PersistenceContext(unitName = "BlinkLinkServer-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public GroupLinkFacade() {
        super(GroupLink.class);
    }
    
}
