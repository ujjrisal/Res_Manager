package de.jura.role.data;


// Contains the variable of the permission Object with the getters, setters methods.
public class Permission {

	private int id;
	private String name;

	public Permission(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String permissionName) {
		this.name = permissionName;
	}

}
