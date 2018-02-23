package de.jura.role.form;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import de.jura.role.data.Permission;
import de.jura.role.data.Role;
import de.jura.role.service.RoleServiceImpl;

// The editor form has been introduced in order to reduce the functionality of the controller, such that a work balance received. 
// It has direct connection with the front page that allows admin users to create a role and select the permissions the new role could hold. 
// Further, when an user deletes a role, it's the place where the delete request comes. The request then forwarded to the service layer.           

@Component("rolEditFrm")
public class RoleEditorForm {

	@Autowired
	private RoleServiceImpl roleServiceImpl;

	private List<String> allPermissions = new ArrayList<String>();

	@Autowired
	HttpServletRequest request;

	public String action;

	private Integer rowRoleId;

	private Role role;

	private List<String> selectedPermissions = new ArrayList<String>();

	public Role getRole() {

		if (null == role) { 

			if (rowRoleId != null) { 

				role = roleServiceImpl.getRole(rowRoleId);
				List<String> selectedPermission = new ArrayList<String>();

				for (Permission permission : role.getPermissions()) {
					selectedPermission.add(permission.getName());

				}
				this.setSelectedPermissions(selectedPermission);

				
			} else {
				
				role = new Role();
			}
		}

		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public List<String> getAllPermissions() {

		allPermissions = roleServiceImpl.getPermission();
		return allPermissions;
	}

	public RoleServiceImpl getRoleServiceImpl() {
		return roleServiceImpl;
	}

	public void setRoleServiceImpl(RoleServiceImpl roleServiceImpl) {
		this.roleServiceImpl = roleServiceImpl;
	}

	// Used for the creation of role. Only the users authorized with 'create_role' could create a new role.    
	@PreAuthorize("hasRole('create_role')")
	public String save() {

		String message = null;

		for (String permission : getSelectedPermissions()) {
			getRole().getPermissions().add(new Permission(permission));
		}
		int retval = this.roleServiceImpl.saveRole(this.role);

		if (retval == 0) {
			message = "can't create role";

		} else {

			

			message = "role creation successful";
		}

		FacesContext.getCurrentInstance().addMessage(null,
				new FacesMessage(message));

		this.role = null;

		return "roleresource.xhtml?faces-redirect=true";
	}

	
	// Used for the deletion of a role. Only the users authorized with 'delete_role' could execute it.  
	@PreAuthorize("hasRole('delete_role')")
	public void delete() {

		int retVal = roleServiceImpl.deleteRole(this.getRowRoleId());

	}

	public List<String> getSelectedPermissions() {
		
		return selectedPermissions;
	}

	public void setSelectedPermissions(List<String> selectedPermissions) {
		
		this.selectedPermissions.clear();
		this.selectedPermissions = selectedPermissions;
	}

	public List<String> getAllRoleNames() {
		return roleServiceImpl.retrieveAllRoleNames();
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

	// Forwards the edit role request to insertrole.xhtml page. When the request completed, the role data is loaded into the form such that editing could be performed.
    // The users authorized with 'edit_role' could execute the method.  	
	@PreAuthorize("hasRole('edit_role')")
	public String editAction() {

		this.role = null;
		this.action = null;

		

		String returnString = "insertrole.xhtml?faces-redirect=true&roleId="
				+ rowRoleId + "&action=edit";

		return returnString;

	}

	
	// When the role data loaded into the form and updated, the role object then forwarded to the service layer for the further operation.
   // Only the users authorized with 'edit_role' could execute the method.    
	@PreAuthorize("hasRole('edit_role')")
	public String updateRole() {

		String retString = null;
		
		for (String permission : getSelectedPermissions()) {
			getRole().getPermissions().add(new Permission(permission));
		}

		
		roleServiceImpl.updateRole(this.role);

		retString = "roleresource.xhtml?faces-redirect=true";
		return retString;

	}
    
	// Used for the deletion of a role.
   // Only the users authorized with 'delete_role' could execute the method. 	
	@PreAuthorize("hasRole('delete_role')")
	public void deleteRole() {

	int retVal = roleServiceImpl.deleteRole(getRowRoleId());

		

	}

	public String cancelAction() {
		this.role = null;
		return "roleresource.xhtml?faces-redirect=true";
	}

	public Integer getRowRoleId() {
		return rowRoleId;
	}

	public void setRowRoleId(Integer rowRoleId) {
		this.rowRoleId = rowRoleId;
	}

	public String addRoleAction() {

		return "insertrole.xhtml?faces-redirect=true&action=save";

	}

	public List<Role> getAllRoles() {

		return roleServiceImpl.getRoles();

	}

	public void setAllPermissions(List<String> allPermissions) {
		this.allPermissions = allPermissions;
	}

}
