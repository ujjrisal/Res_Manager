package de.jura.role.service;

import java.util.List;

import de.jura.role.data.Role;

// RoleService interface that defines the methods to be implemented from the RoleServiceImpl class.  
public interface RoleService {

	public List<Role> getRoles();

	public Role getRole(Integer id);

	public int saveRole(Role role);

	public int deleteRole(Integer id);

	public void updateRole(Role role);

	public List<String> getPermission();

	public List<String> retrieveAllRoleNames();

	public Role getRole(String name);

	public List<String> retrievePermissionByRole(Integer roleId);

}
