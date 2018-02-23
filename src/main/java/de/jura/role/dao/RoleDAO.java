package de.jura.role.dao;

import java.util.List;

import de.jura.role.data.Role;

// Interface that defines methods to be implemented from implementing class. The method includes save, delete, update, retrieve role. 
// It contains also the method that retrieves all the permissions from the database in the form of data structure. 

public interface RoleDAO {

	public List<Role> retrieveRoles();

	public int saveRole(Role role);

	public int deleteRole(Integer id);

	public void updateRole(Role role);

	public List<String> getPermission();

	public List<String> getPermissionByRole(Integer id);

	public List<String> getAllRoleNames();

	public Role getRole(String name);

	public Role getRole(Integer id);

}
