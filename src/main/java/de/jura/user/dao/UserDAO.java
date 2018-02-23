/**
 * 
 */
package de.jura.user.dao;

import java.util.List;

import de.jura.user.data.User;


// Interface that declares method to be overriden from the implementing class.  

public interface UserDAO {

	
	public User getUserWithLogin(String login);

	
	public User getUser(Integer id);

	
	public int deleteUser(Integer userId);

	
	public int saveUser(User user);

	
	public int updateUser(User user);

	public List<User> getAllUsers();

}
