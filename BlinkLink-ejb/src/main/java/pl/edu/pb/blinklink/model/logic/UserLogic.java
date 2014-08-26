package pl.edu.pb.blinklink.model.logic;

import javax.ejb.Local;

import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.logic.exceptions.UserAlreadyExistException;
import pl.edu.pb.blinklink.model.logic.exceptions.UserNotExistsException;

/**
 * Methods for manage user.
 * @author dawid
 */
@Local
public interface UserLogic {
    
    /**
     * Registers BlinkUser into system.
     * @param user User data for registration.
     * @throws UserAlreadyExistException 
     */
    void registerUser(BlinkUser user) throws UserAlreadyExistException;
    
    /**
     * Deletes user permanently (truncates all stored data).
     * @param user User which will be deleted.
     * @throws UserNotExistsException
     */
    void permDelete(BlinkUser user) throws UserNotExistsException;
    
    
    /**
     * @TODO!
     * @param username
     * @param password
     * @return 
     */
    BlinkUser login(String username, String password);
}
