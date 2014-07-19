package pl.edu.pb.blinklink.model.logic.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.beans.BlinkGroupDao;
import pl.edu.pb.blinklink.model.beans.BlinkGroupFacade;
import pl.edu.pb.blinklink.model.beans.BlinkUserDao;
import pl.edu.pb.blinklink.model.beans.BlinkUserFacade;
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
        user.getGroups().add(group); //really naive
        bud.update(user);
    }

    @Override
    public void signOff(BlinkUser user, BlinkGroup group) throws UserUnregisteredException {
        user.getGroups().remove(group); //really naive
        bud.update(user);
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
