package pl.edu.pb.blinklink.model.logic.exceptions;

/**
 * Throws when operated user not exists.
 * @author dawid
 */
public class UserNotExistsException extends Exception {
    UserNotExistsException(String msg)
    {
        super(msg);
    }
}
