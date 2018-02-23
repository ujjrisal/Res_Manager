package de.jura.role.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import de.jura.common.dao.BaseDAO;
import de.jura.role.data.Permission;
import de.jura.role.data.Role;


 
 // Implements all the methods contained in the RoleDAO interface.  
@Component
public class RoleDAOImpl extends BaseDAO implements RoleDAO {

	private Role role;

	// Interacts directly with the database to retrieve all the roles and return it in the form of a data structure. 
	public List<Role> retrieveRoles() {
		String sqlString = "select * from tbl_role";

		RowMapper mapper = new RowMapper() {

			public Role mapRow(ResultSet rs, int arg1) throws SQLException {

				Role role = new Role();
				role.setId(rs.getInt("id"));
				role.setName(rs.getString("name").toString());

				return role;
			}

		};

		return (List<Role>) this.jdbcTemplate.query(sqlString, mapper);

	}

	//Saves a role in the database. 
	public int saveRole(Role role) {

		String strSql = "insert into tbl_role(name) values (?)";

		int toReturn;

		int retVal1 = 0;

		int retVal = this.jdbcTemplate.update(strSql,
				new Object[] { role.getName() });

		
        for (Permission permission : role.getPermissions()) {

			String sqlString = "insert into role_permission (role_id,permission_id) "
					+ "(select r.id,p.id from tbl_role r, tbl_permission p where r.name=? and p.permission_name=?)";

			retVal1 = this.jdbcTemplate.update(sqlString,
					new Object[] { role.getName(), permission.getName() });
		}

		if (retVal == 1 && retVal1 == 1) {

			toReturn = 1;

		} else {
			toReturn = 0;
		}

		return toReturn;

	}

	// Deletes a role from the database having the id. It also deletes all the permissions that belongs to a role from the role_permission table.    
	public int deleteRole(Integer id) {

		String strSql = "DELETE from tbl_role, "
				+ "role_permission using tbl_role INNER JOIN role_permission  on tbl_role.id = role_permission.role_id where tbl_role.id=?";

		int retVal = this.jdbcTemplate.update(strSql, new Object[] { id });
		return retVal;

	}

	// Updates a role in the database. 
	public void updateRole(Role role) {

		

		String updateQueryRoleTable = "update tbl_role set name=? where id =?";
		int updatedRows1 = this.jdbcTemplate.update(updateQueryRoleTable,
				new Object[] { role.getName(), role.getId() });

		String deleteRowsQuery = "delete from role_permission where role_id=?";

		String insertRowsQuery = "insert into role_permission(role_id, permission_id) (select ?, id from tbl_permission where permission_name=?)";

		int retValDelete = this.jdbcTemplate.update(deleteRowsQuery,
				new Object[] { role.getId() });

		for (Permission permission : role.getPermissions()) {

			int retValInsert = this.jdbcTemplate.update(insertRowsQuery,
					new Object[] { role.getId(), permission.getName() });

		}

	}
    
    // Selects all permission name from tbl_permission. The returned value is a data structure that contains all the permission names retrieved from the select query.   	
	public List<String> getPermission() {

		String sqlString = "select permission_name from tbl_permission";

		return (List<String>) this.jdbcTemplate.queryForList(sqlString,
				String.class);
	}


    // Selects all role names from the table tbl_role. The returned value is a data structure that contains all the role names retrieved from the select SQL query. 
	public List<String> getAllRoleNames() {

		String sqlString = "select name from tbl_role";

		return (List<String>) this.jdbcTemplate.queryForList(sqlString,
				String.class);

	}

	// Retrieves role name from the database. 
	public Role getRole(String name) {

		String sqlString = "select name from tbl_role where name='" + name
				+ "'";

		RowMapper mapper = new RowMapper() {

			
			public Role mapRow(ResultSet rs, int arg1) throws SQLException {

				Role role = new Role();
				role.setName(rs.getString(1));

				return role;
			}

		};

		Role role = (Role) this.jdbcTemplate.queryForObject(sqlString, mapper);

		return role;

	}

	// Selects all the permission name from the database. The sub query here selects the permission id based on the roleId sent as method argument from the role_permission table.      
	public List<String> getPermissionByRole(Integer roleId) {
		String strSql = "select permission_name from tbl_permission where id in(select permission_id from role_permission where role_id=?)";

		List<String> permissions = (List<String>) this.jdbcTemplate
				.queryForList(strSql, new Object[] { roleId }, String.class);
		return permissions;
	}

	
	public Role getRole(Integer roleId) {

		String strSql = " select tbl_role.id as roleId,tbl_permission.id as permission_id,name, permission_name from tbl_role inner join role_permission inner join tbl_permission"
				+ " on tbl_role.id = role_permission.role_id "
				+ "and role_permission.permission_id = tbl_permission.id  where tbl_role.id ="
				+ roleId + ";";

		role = new Role();
		RowMapper mapper = new RowMapper() {

			public Role mapRow(ResultSet rs, int arg1) throws SQLException {

				role.setName(rs.getString("name"));
				role.setId(rs.getInt("roleId"));

				role.getPermissions().add(
						new Permission(rs.getString("permission_name")));

				
				return role;

			}
		};
		List<Role> role = (List<Role>) (this.jdbcTemplate.query(strSql, mapper));
		return role.get(0);

	}

}
