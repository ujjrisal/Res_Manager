package de.jura.role.data;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;


// Contains the variable of the role object with the getters, setters methods. 
public class Role implements Serializable {

	private static final long serialVersionUID = 1420672609912364060L;

	Integer id;

	String name;

	Set<Permission> permissions = new HashSet<Permission>();

	public Role() {

	}

	public Role(int id, String name) {
		this.id = id;
		this.name = name;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<Permission> getPermissions() {

		return permissions;
	}

	public void setPermissions(Set<Permission> permissions) {
		this.permissions = permissions;
	}

}
