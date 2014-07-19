package pl.edu.pb.blinklink.model.beans;

import pl.edu.pb.blinklink.model.BlinkUser;

public interface BlinkUserDao extends DaoInterface<BlinkUser> {
	public BlinkUser login(String username, String password);
}
