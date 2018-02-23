/**
 * 
 */
package de.jura.user.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import de.jura.common.dao.BaseDAO;
import de.jura.role.data.Role;
import de.jura.user.data.User;

// The methods in UserDAOImpl interacts directly with the database using sql queries.  

@Component("userDao")
public class UserDAOImpl extends BaseDAO implements UserDAO {

	
	//Returns the user object having the login name. 
	public User getUserWithLogin(String login) {
		String strSql = "select * from tbl_user where username=?";

		RowMapper mapper = new RowMapper() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setName(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.setRoleId(rs.getInt(6));
				return user;
			}
		};

		User user = (User) this.jdbcTemplate.queryForObject(strSql,
				new Object[] { login }, mapper);

		return user;
	}

	
	// Returns the user from the database having the id. 
	public User getUser(Integer id) {

		String strSql = "select tbl_user.id,tbl_user.username, "
				+ "tbl_user.password, tbl_user.name, tbl_user.email, "
				+ "tbl_role.name from tbl_user "
				+ "inner join tbl_role on tbl_role.id = tbl_user.role where tbl_user.id =?";

		RowMapper mapper = new RowMapper() {
			public User mapRow(ResultSet rs, int rowNum) throws SQLException {
				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setName(rs.getString(4));
				user.setEmail(rs.getString(5));
				user.getRole().setName((rs.getString(6)));
				return user;
			}
		};

		User user = (User) this.jdbcTemplate.queryForObject(strSql,
				new Object[] { id }, mapper);

		return user;
	}

	
// Deletes the user from the database having the id.
	public int deleteUser(Integer userId) {

		String strSql = "delete from tbl_user where id='" + userId + "'";

		int retVal = this.jdbcTemplate.update(strSql);
		return retVal;

	}

	// Saves a new user in the database. Returns the integer value after the successful operation.    
	public int saveUser(User user) {

		String strSql = "insert into tbl_user(username,password,name,email,role) (select ?,?,?,?, id from tbl_role where name=?)";

		System.out.println(strSql);

		int retVal = this.jdbcTemplate.update(
				strSql,
				new Object[] { user.getUsername(), user.getPassword(),
						user.getName(), user.getEmail(),
						user.getRole().getName() });
		return retVal;

	}

	
	// Updates an already existing user in the database. 
	public int updateUser(User user) {
		String updateQuery = "update tbl_user set username=?,name=?,email=?,role=(select id from tbl_role where name =? ) where id=?";
		int rows = this.jdbcTemplate.update(updateQuery,

		new Object[] { user.getUsername(), user.getName(), user.getEmail(),
				user.getRole().getName(), user.getId() });

		return rows;
	}

	public List<User> getAllUsers() {

		String sqlString = "select tbl_user.id, tbl_user.username, tbl_user.name,"
				+ " tbl_user.email, tbl_role.id,tbl_role.name as role from tbl_user "
				+ "left join tbl_role on tbl_user. role = tbl_role.id order by tbl_user.name";

		RowMapper mapper = new RowMapper() {

			public User mapRow(ResultSet rs, int rowNum) throws SQLException {

				User user = new User();
				user.setId(rs.getInt(1));
				user.setUsername(rs.getString(2));
				user.setName(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setRole(new Role(rs.getInt(5), rs.getString(6)));
				return user;

			}

		};

		List<User> userList = (List<User>) this.jdbcTemplate.query(sqlString,
				mapper);

		return userList;

	}

}
