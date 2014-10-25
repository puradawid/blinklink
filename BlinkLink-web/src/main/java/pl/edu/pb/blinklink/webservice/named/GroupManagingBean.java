package pl.edu.pb.blinklink.webservice.named;

import java.io.Serializable;
import java.util.LinkedList;
import java.util.List;

import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.inject.Inject;
import javax.inject.Named;

import pl.edu.pb.blinklink.model.BlinkGroup;
import pl.edu.pb.blinklink.model.beans.BlinkGroupDao;
import pl.edu.pb.blinklink.model.logic.GroupLogic;
import pl.edu.pb.blinklink.model.logic.exceptions.UserAlreadyRegisteredException;
import pl.edu.pb.blinklink.model.logic.exceptions.UserUnregisteredException;

@Named
@SessionScoped
public class GroupManagingBean implements Serializable{

	private static final long serialVersionUID = 1L;

	@Inject UserBean userBean;
	
	@EJB BlinkGroupDao blinkGroupDao;
	@EJB GroupLogic groupLogic;
	
	private String name;
	private String description;

	public boolean canUserJoinToGroup(BlinkGroup group) {
		return !group.getRegistered().contains(userBean.getUserData());
	}

	public boolean canUserLeaveGroup(BlinkGroup group) {
		return group.getRegistered().contains(userBean.getUserData());
	}
	
	public boolean canUserDeleteGroup(BlinkGroup group) {
		if(group.getOwner() != null)
			return group.getOwner().equals(userBean.getUserData());
		else
			return false;
	}
	
	public void leaveGroup(BlinkGroup group) {
		try {
		groupLogic.signOff(userBean.getUserData(), group);
		} catch (UserUnregisteredException e) {
			throw new RuntimeException(e);
		}
	}
	
	public void joinGroup(BlinkGroup group) {
		try {
			groupLogic.signIn(userBean.getUserData(), group);
		} catch (UserAlreadyRegisteredException e)
		{
			throw new RuntimeException(e);
		}
	}
	
	public void removeGroup(BlinkGroup group) {
		try {
			blinkGroupDao.remove(group);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
	
	public boolean isLast(BlinkGroup group) {
		List<BlinkGroup> groups = new LinkedList<BlinkGroup>(blinkGroupDao.findAll());
		int index = groups.indexOf(group);
		int lastIndex = groups.size() - 1;
		return index == lastIndex;
	}

	public List<BlinkGroup> getGroups() {
		return new LinkedList<BlinkGroup>(blinkGroupDao.findAll());
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
	
	public String createNewGroup() {
		blinkGroupDao.create(new BlinkGroup(name, description, userBean.getUserData()));
		return "/";
	}
}
