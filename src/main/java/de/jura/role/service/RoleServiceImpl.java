package de.jura.role.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jura.role.dao.RoleDAOImpl;
import de.jura.role.data.Role;


// Overrides all the methods that are declared in the RoleService interface. 
// The methods interact with methods in RoleDAOImpl with the help of injected object.   
@Component
public class RoleServiceImpl implements RoleService {

	
	@Autowired
	private RoleDAOImpl roleDao;

	public RoleDAOImpl getRoleDao() {
		return roleDao;
	}

	public void setRoleDao(RoleDAOImpl roleDao) {
		this.roleDao = roleDao;
	}

	
	public List<Role> getRoles() {

		return roleDao.retrieveRoles();
	}

	
	public int saveRole(Role role) {

		return roleDao.saveRole(role);
	}

	
	public int deleteRole(Integer id) {

		int retVal = roleDao.deleteRole(id);
		return retVal;

	}

	
	public void updateRole(Role role) {

		roleDao.updateRole(role);

	}

	
	public List<String> getPermission() {

		return roleDao.getPermission();
	}

	
	public Role getRole(String name) {

		return roleDao.getRole(name);

	}

	public List<String> retrieveAllRoleNames() {
		return roleDao.getAllRoleNames();
	}

	
	public Role getRole(Integer id) {
		return roleDao.getRole(id);

	}

	
	public List<String> retrievePermissionByRole(Integer roleId) {
		return this.roleDao.getPermissionByRole(roleId);

	}

}
