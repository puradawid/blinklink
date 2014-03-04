package pl.edu.pb.blinklink.model.logic.impl;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.EJB;
import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.beans.BlinkGroupFacade;
import pl.edu.pb.blinklink.model.beans.BlinkUserFacade;
import pl.edu.pb.blinklink.model.logic.GroupLogic;
import pl.edu.pb.blinklink.model.logic.exceptions.UserAlreadyRegisteredException;
import pl.edu.pb.blinklink.model.logic.exceptions.UserUnregisteredException;

/**
 * 
 * @author dawid
 */
@EJB
public class GroupLogicHibernate implements GroupLogic {

    @EJB
    BlinkUserFacade buf;
    
    @EJB
    BlinkGroupFacade bgf;
    
    @Override
    public void signIn(BlinkUser user, BlinkGroup group) throws UserAlreadyRegisteredException {
        user.getGroups().add(group); //really naive
        buf.edit(user);
    }

    @Override
    public void signOff(BlinkUser user, BlinkGroup group) throws UserUnregisteredException {
        user.getGroups().remove(group); //really naive
        buf.edit(user);
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
        Collection<BlinkGroup> groups = bgf.findAll();
        return convertToStrings(groups);
    }

    @Override
    public Collection<String> getGroups(BlinkUser user) {
        Collection<BlinkGroup> groups = bgf.select(null, null);
        return convertToStrings(groups);
    }

    static String getQuery = 
            "SELECT group FROM Group group WHERE group.name = :name";
    
    @Override
    public BlinkGroup get(String name) {
        Map<String, Object> params = new TreeMap<String, Object>();
        List<BlinkGroup> result = bgf.select(getQuery, params);
        return (result.size() == 1 ? result.get(0) : null );
    }
    
    
}
