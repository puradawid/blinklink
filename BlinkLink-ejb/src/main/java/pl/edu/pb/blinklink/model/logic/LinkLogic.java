package pl.edu.pb.blinklink.model.logic;

import java.util.Collection;
import java.util.Date;

import javax.ejb.Local;

import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.GroupLink;
import pl.edu.pb.blinklink.model.Link;
import pl.edu.pb.blinklink.model.UserLink;
import pl.edu.pb.blinklink.model.logic.exceptions.PostingLinkException;

/**
 * Concrete methods to managing links from unknown source.
 * This functions assumes <b>stateless</b> communication, which means there
 * is no context of invokes, no session and other.
 * @author dawid
 */
@Local
public interface LinkLogic {
    
    /**
     * Gets users links past some date from specific user.
     * @param user User who requesting for links.
     * @param date
     * @return 
     */
    public Collection<UserLink> getUserLinksPast(BlinkUser user, Date date);
    
    /**
     * Gets groups links past some date for specific user.
     * @param user
     * @param date
     * @return 
     */
    public Collection<GroupLink> getGroupLinksPast(BlinkUser user, Date date);
    
    /**
     * Posts link into users.
     * @param user User who posting link.
     * @param link Address for link.
     * @throws PostingLinkException If posting link is wrong, throw this exception.
     */
    public void postLink(BlinkUser user, UserLink link) throws PostingLinkException;
    
    /**
     * Posts group link into users.
     * @param user User who posting link.
     * @param link Address for link.
     * @throws PostingLinkException If posting link is wrong, throw this exception.
     */
    public void postLink(BlinkUser user, GroupLink link, String group) throws PostingLinkException;
    
    /**
     * Search links with concrete values.
     * <b>IMPORTANT</b>: Each of criteria is optional, but when all are nulled then it 
     * has to return 0 elements!
     * @param username
     * @param groupname
     * @param from from when it has to be searched (default = from 0BC)
     * @param to to when it has to be searched (default = now)
     * @param content description or referer link
     * @return Collection of links sorted by post date.
     */
    public Collection<Link> searchLinks(String username, String groupname,
                                        Date from, Date to, String content);
    
    /**
     * Calculates MD5 sum for concrete client.
     * @param user
     * @return MD5 sum of all stored links
     */
    public String calculateSum(BlinkUser user);
}
