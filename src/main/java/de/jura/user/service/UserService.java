
package de.jura.user.service;

import java.util.List;

import de.jura.user.data.User;

// The UserService interface declares the method to be implemented from the implementing class. The method includes saving, deleting, updating, retrieving user.    

public interface UserService {

	
	public User retrieveUserWithLogin(String loginName);

	
	public User retriveUserWithId(Integer userId);


	public int saveUser(User user);

	public List<User> retrieveAllUsers();

	public int deleteUser(Integer userId);

	public int updateUser(User user);
}
