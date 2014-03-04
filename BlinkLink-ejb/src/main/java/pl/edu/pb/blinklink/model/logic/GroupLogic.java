package pl.edu.pb.blinklink.model.logic;

import java.util.Collection;
import javax.ejb.Local;
import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.logic.exceptions.UserAlreadyRegisteredException;
import pl.edu.pb.blinklink.model.logic.exceptions.UserUnregisteredException;

/**
 * Methods for managing groups.
 * @author dawid
 */
@Local
public interface GroupLogic {
    
    /**
     * Sign in user into group.
     * @param user
     * @param group
     * @throws UserAlreadyRegisteredException 
     */
    void signIn(BlinkUser user, BlinkGroup group) 
            throws UserAlreadyRegisteredException;
    /**
     * Sign off user from group.
     * @param user
     * @param group
     * @throws UserUnregisteredException
     */
    void signOff(BlinkUser user, BlinkGroup group) 
            throws UserUnregisteredException;
    
    /**
     * Return available groups.
     * @return Collection of all groups in system
     */
    Collection<String> getGroups();
    
    /**
     * Return user groups.
     * @param user concrete user
     * @return Collection of groups that user is registered.
     */
    Collection<String> getGroups(BlinkUser user);
    
    /**
     * Gets group named name.
     * @param name
     * @return 
     */
    BlinkGroup get(String name);
}
