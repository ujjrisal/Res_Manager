package de.jura.user.controller;

import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import de.jura.user.data.User;
import de.jura.user.service.UserServiceImpl;

@Component("userController")
public class UserController {

	@Autowired
	UserServiceImpl userService;

	@Autowired
	private HttpServletRequest request;

	private Integer rowUserId;
	private String userId;

	private String userName;

	private String action;

	public Integer getRowUserId() {
		return rowUserId;
	}

	public void setRowUserId(Integer rowUserId) {
		this.rowUserId = rowUserId;
	}

	private User user;

	public UserController() {

	}

	
	// Returns an user object communicating with the service layer for editing purpose or a new user object for the saving purpose.     
	public User getUser() {

		if (user == null) {

			// for edit purpose
			
			if (getUserId() != null) {
				user = userService.retriveUserById(Integer.parseInt(userId));

			} 
			
			//  for save purpose

			else {
				user = new User();

			}

		}

		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	//Interacts with service layer to save the new user. Navigates to the userresource.xhtml page after the successful creation of user.   
	public String signUp() {

		int retVal;
		String message;
		retVal = userService.saveUser(user);

		if (retVal == 1) {

			message = "inserted successfully!!";

		} else {

			message = "user creation unsuccessful";

		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(message));

		this.user = null;

		return "userresource.xhtml?faces-redirect=true";
	}

	public UserServiceImpl getUserService() {
		return userService;
	}

	public void setUserService(UserServiceImpl userService) {
		this.userService = userService;
	}

	public List<User> getAllUsers() {

		return userService.retrieveAllUsers();

	}

	// Redirects to insertuser.xhtml page to create new user. Only the users possessing the 'create_user' role could execute insertUserAction method.  
	@PreAuthorize("hasRole('create_user')")
	public String insertUserAction() {

		return "insertuser.xhtml?faces-redirect=true&action=save";
	}

	//Redirects to the userresource.xhtml without editing or saving the user. 
	public String cancelAction() {

		this.user = null;

		return "userresource.xhtml?faces-redirect=true";
	}

	 
	public String editAction() {

		this.user = null;
		this.action = null;

		return "insertuser.xhtml?faces-redirect=true&userId=" + getRowUserId()
				+ "&action=edit";

	}

	
	// Interacts with service layer to delete a user. A user need to possess 'delete_user' role to execute the delete action.   
	@PreAuthorize("hasRole('delete_user')")
	public void deleteAction() {

		userService.deleteUser(this.getRowUserId());

	}

	public String getUserName() {
		return userName;
	}

	public String getUserId() {

		userId = (request.getParameter("userId"));
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getAction() {
		if (action == null) {
			action = request.getParameter("action");
		}
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	// Interacts with the service layer to update already created users. To execute the update action, a user need to have the role 'create_user'.    
	@PreAuthorize("hasRole('create_user')")
	public String updateAction() {

		int retVal = userService.updateUser(this.user);

		String retString = null;
		if (retVal == 1) {
			retString = "userresource.xhtml?faces-redirect=true";
		}
		return retString;
	}

}
