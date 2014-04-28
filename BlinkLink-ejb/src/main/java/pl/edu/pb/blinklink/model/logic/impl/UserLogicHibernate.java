/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.pb.blinklink.model.logic.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateful;
import javax.ejb.Stateless;
import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.beans.BlinkUserFacade;
import pl.edu.pb.blinklink.model.logic.UserLogic;
import pl.edu.pb.blinklink.model.logic.exceptions.UserAlreadyExistException;
import pl.edu.pb.blinklink.model.logic.exceptions.UserNotExistsException;

/**
 *
 * @author dawid
 */
@Stateless(name = "UserLogicHibernate")
@Local(UserLogic.class)
public class UserLogicHibernate implements UserLogic {
    
    @EJB
    BlinkUserFacade buf;
    
    @Override
    public void registerUser(BlinkUser user) throws UserAlreadyExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void permDelete(BlinkUser user) throws UserNotExistsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    
    private final static String loginQuery = 
            "SELECT user FROM BlinkUser user WHERE "
            + "user.password = :password AND user.email = :username";
    @Override
    public BlinkUser login(String username, String password) {
        Map<String, Object> m = new HashMap<String, Object>();
        m.put("username", username);
        m.put("password", password);
        List<BlinkUser> users = buf.select(loginQuery, m);
        if(users.isEmpty())
            return null;
        return users.get(0);
    }
    
}
