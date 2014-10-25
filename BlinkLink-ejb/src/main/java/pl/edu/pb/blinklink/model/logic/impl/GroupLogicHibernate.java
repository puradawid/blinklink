package pl.edu.pb.blinklink.model.logic.impl;

import java.util.Collection;
import java.util.LinkedList;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.beans.BlinkGroupDao;
import pl.edu.pb.blinklink.model.beans.BlinkUserDao;
import pl.edu.pb.blinklink.model.logic.GroupLogic;
import pl.edu.pb.blinklink.model.logic.exceptions.UserAlreadyRegisteredException;
import pl.edu.pb.blinklink.model.logic.exceptions.UserUnregisteredException;

/**
 * Logic implementation.
 * @author Dawid Pura
 */
@Stateless(name = "GroupLogicHibernate")
@Local(GroupLogic.class)
public class GroupLogicHibernate implements GroupLogic {
  
    @EJB(beanName="BlinkUserDaoHibernate")
    BlinkUserDao bud;
    
    @EJB(beanName="BlinkGroupDaoHibernate")
    BlinkGroupDao bgd;
    
    @Override
    public void signIn(BlinkUser user, BlinkGroup group) throws UserAlreadyRegisteredException {
        user.getGroups().add(group);
        group.getRegistered().add(user);
        bud.update(user);
        bgd.update(group);
    }

    @Override
    public void signOff(BlinkUser user, BlinkGroup group) throws UserUnregisteredException {
        user.getGroups().remove(group); 
        group.getRegistered().remove(user);
        bud.update(user);
        bgd.update(group);
    }
    
    private static Collection<String> convertToStrings(Collection<BlinkGroup> groups)
    {
        Collection<String> result = new LinkedList<String>();
        for(BlinkGroup group : groups)
            result.add(group.getName());
        return result;
    }
    
    @Override
    public Collection<String> getGroups() {
        Collection<BlinkGroup> groups = bgd.findAll();
        return convertToStrings(groups);
    }

    @Override
    public Collection<String> getGroups(BlinkUser user) {
        Collection<BlinkGroup> groups = bgd.findGroupsThatUserRegistered(user);
        return convertToStrings(groups);
    }

    @Override
    public BlinkGroup get(String name) {
        return bgd.findByName(name);
    }
    
}
