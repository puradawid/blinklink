package pl.edu.pb.blinklink.webservice.named;

import java.io.Serializable;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.logic.UserLogic;
import pl.edu.pb.blinklink.model.logic.exceptions.UserAlreadyExistException;

@Named
@SessionScoped
public class UserBean implements Serializable {
	
	private static final long serialVersionUID = 1L;

	@EJB UserLogic userLogic;
	
	BlinkUser loggedUser;
	boolean logged;
	
	String username;
	String password;
	
	public void register(BlinkUser user) {
		try {
		userLogic.registerUser(user);
		} catch (UserAlreadyExistException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void login(String username, String password) {
		BlinkUser user = userLogic.login(username, password);
		if(user != null) {
			loggedUser = user;
			logged = true;
		} else
			throw new RuntimeException();
	}
	
	public void login() {
		login(username, password);
	}
	
	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
		return "/?faces-redirect=true";
	}
	
	public boolean isLogged() {
		return logged;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public String getUsername() {
		return this.username;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getPassword() {
		return this.password;
	}
	
	public BlinkUser getUserData() { 
		return loggedUser;
	}
}
