package de.jura.user.data;

import java.io.Serializable;

import de.jura.role.data.Role;

// Represents the data of the user object. The data includes the id, username, name of the user, role id that points to the role object.  

public class User implements Serializable {
	
	 private static final long serialVersionUID = 1420672609912364060L;

	private Integer id;
	private String username;
	private String password;
	private String name;
	private String email;
	private int roleId;

	private Role role;

	public User() {
		role = new Role();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Role getRole() {

		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public int getRoleId() {
		return roleId;
	}

	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}

	public int getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
