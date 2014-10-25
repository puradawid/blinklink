package pl.edu.pb.blinklink.webservice.named;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Named;

import pl.edu.pb.blinklink.model.BlinkUser;
import pl.edu.pb.blinklink.model.logic.UserLogic;
import pl.edu.pb.blinklink.model.logic.exceptions.UserAlreadyExistException;
import java.io.Serializable;

@Named
@SessionScoped
public class RegisterUserBean implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private String name;
	private String surname;
	private String password;
	private String email;
	
	@EJB UserLogic userLogic;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String register() {
		try {
			userLogic.registerUser(new BlinkUser(email, password, surname, name));
			return null;
		} catch (UserAlreadyExistException e) {
			return "User already exists!";
		}
	}
	
	
}
