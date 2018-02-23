
package de.jura.user.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jura.user.dao.UserDAOImpl;
import de.jura.user.data.User;


// Override the methods declared in the service interface 
// Interacts directly with the dao layer  


@Component("userService")
public class UserServiceImpl implements UserService {

	//@ManagedProperty(value = "#{userDao}")
	@Autowired
	private UserDAOImpl userDao;

	
	
	// Calls the getUser method of the dao layer to retrieve the user having the login name.
	public User retrieveUserWithLogin(String loginName) {
		
		return userDao.getUserByLogin(loginName);
	}

	
	// Calls the deleteUser method of the dao layer to delete a user having the id.  
	public int deleteUser(Integer userId) {

		return userDao.deleteUser(userId);

	}

	// Calls the updateUser method of the dao layer to update a user.
	public int updateUser(User user) {

		return userDao.updateUser(user);
	}

	
    // Calls the getUser method of the dao layer to retrieve the user having the userId.
	public User retrieveUser(Integer userId) {
		return userDao.getUser(userId);
	}

	
	// Calls the saveUser method of the dao layer to save the user. 
	public int saveUser(User user) {
		return userDao.saveUser(user);

	}

    public void setUserDao(UserDAOImpl userDao) {
		this.userDao = userDao;
	}
 
    // Calls thegetAllUsers method of the dao layer to retrieve all users in the form of a data structure.  
	public List<User> retrieveAllUsers() {
		return userDao.getAllUsers();
	}

	public UserDAOImpl getUserDao() {
		return userDao;
	}

}
