/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package pl.edu.pb.blinklink.model.logic.impl;

import javax.ejb.EJB;
import javax.ejb.Local;
import javax.ejb.Stateless;

import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.beans.BlinkUserDao;
import pl.edu.pb.blinklink.model.logic.UserLogic;
import pl.edu.pb.blinklink.model.logic.exceptions.UserAlreadyExistException;
import pl.edu.pb.blinklink.model.logic.exceptions.UserNotExistsException;

/**
 * @author Dawid Pura
 */
@Stateless(name = "UserLogicHibernate")
@Local(UserLogic.class)
public class UserLogicHibernate implements UserLogic {
    
    @EJB(beanName="BlinkUserDaoHibernate")
    BlinkUserDao bud;
    
    @Override
    public void registerUser(BlinkUser user) throws UserAlreadyExistException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void permDelete(BlinkUser user) throws UserNotExistsException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public BlinkUser login(String username, String password) {
        BlinkUser login = bud.login(username, password);
        return login;
    }
    
}
