package pl.edu.pb.blinklink.webservice;

import java.util.Collection;
import java.util.Date;
import java.util.LinkedList;
import javax.annotation.Resource;
import javax.ejb.EJB;
import javax.jws.WebService;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.xml.ws.WebServiceContext;
import pl.edu.pb.blinklink.model.beans.BlinkUserFacade;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.UserLink;
import pl.edu.pb.blinklink.model.logic.GroupLogic;
import pl.edu.pb.blinklink.model.logic.LinkLogic;
import pl.edu.pb.blinklink.model.logic.exceptions.UserAlreadyRegisteredException;
import pl.edu.pb.blinklink.webservice.model.BlinkUserWebservice;

/**
 *
 * @author dawid
 */
@WebService(serviceName = "BlinkLinkService")
public class BlinkLinkService {
    
    @Resource
    WebServiceContext wsctx;
    
    @EJB
    BlinkUserFacade buf;
    
    @EJB(beanName = "LinkLogicHibernate")
    LinkLogic ll;
    
    @EJB(beanName = "GroupLogicHibernate")
    GroupLogic gl;
    
    @WebMethod(operationName = "postLink")
    public String postLink( @WebParam(name = "referer") String referer,
                            @WebParam(name = "targets") Collection<String> targets)
    {
        if (checkCredencials())
            return "OK!" + referer;
        return "You are not logged in!";
    }
    
    @WebMethod(operationName = "getLinksSince")
    public Collection<UserLink> getLinksSince(Date since)
    {
        LinkedList lst = new LinkedList<UserLink>();
        lst.add(new UserLink());
        return new LinkedList<UserLink>();
    }
    
    @WebMethod(exclude = true)
    public boolean checkCredencials()
    {
        return getLogin() == null;
    }
    
    @WebMethod(exclude = true)
    public BlinkUser logIn(String username, String password)
    {
        return new BlinkUser(username, password);
    }
    
    @WebMethod(exclude = true)
    public BlinkUser getLogin()
    {
        String username = (String)wsctx.getMessageContext().get("username");
        String password = (String)wsctx.getMessageContext().get("password");
        return logIn(username, password);
    }
    
    @WebMethod(operationName = "registerUser")
    public String registerUser(String username, String password)
    {
        if(username == null || password == null)
        {
            throw new RuntimeException("No empty strings allowed");
        }
        BlinkUser bu = new BlinkUser(username, password);
        buf.create(bu);
        return "OK";
    }
    
    @WebMethod(operationName = "listUsers")
    public Collection<BlinkUserWebservice> listUsers()
    {
        Collection <BlinkUser> users = buf.findAll();
        Collection <BlinkUserWebservice> usersStrings = new LinkedList<BlinkUserWebservice>();
        for(BlinkUser user : users)
        {
            usersStrings.add(BlinkUserWebservice.getInstance(user));
        }
        
        return usersStrings;
    }
    
    @WebMethod(operationName = "signToGroup")
    public String signToGroup(String groupName)
    {
        try {
            gl.signIn(getLogin(), gl.get(groupName));
        } catch (UserAlreadyRegisteredException e)
        {
            return e.getMessage();
        }
        return "OK";
    }
}
