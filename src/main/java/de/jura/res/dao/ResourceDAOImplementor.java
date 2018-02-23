package de.jura.res.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import de.jura.common.dao.BaseDAO;
import de.jura.res.data.PrimaryResource;
import de.jura.util.SearchFilter;

// ResourceDAOImplementor class that interacts directly with the database to save, delete, update and retrieve the resources.  

@Component
public class ResourceDAOImplementor extends BaseDAO implements ResourceDAO {

// Deletes the resource from the database having the id sent as method argument.

	@Override
	public int deleteResource(String resId) {

		String strSql = "delete from tbl_resource where resource_id='" + resId
				+ "'";

		int retVal = this.jdbcTemplate.update(strSql);
		return retVal;

	}

	
	// Saves a new resource in the database. 
	@Override
	public int saveResource(Resource res) {
		String strSql = "insert into tbl_resource( resource_id, resource_name, resource_type, "
				+ "resource_company, settings, ip_address, mac_address, faculty, inventory_id) values(?,?,?,?,?,?,?,?,?)";

		// SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MMM-dd");

		int retVal = this.jdbcTemplate.update(
				strSql,
				new Object[] { res.getResource_id(), res.getResource_name(),
						res.getResource_type(), res.getResource_company(),
						res.getSettings(), res.getIp_address(),
						res.getMac_address(), res.getFaculty(),
						res.getInventory_id() });
		return retVal;

	}

	
	
	// Retrieves the resource from the database having the id sent as method argument. 

	public Resource getResource(String resId) {

		String strSql = "select * from tbl_resource where resource_id='"
				+ resId + "'";
		
		RowMapper mapper = new RowMapper() {

			@Override
			public Resource mapRow(ResultSet rs, int arg1)
					throws SQLException {

				Resource resource = new Resource();
			    resource.setResource_id(rs.getString(2));
				resource.setResource_name(rs.getString(3));
				resource.setResource_type(rs.getString(4));
				resource.setResource_company(rs.getString(5));

				// primary_Resource.setInstallation_date(rs.getDate(6).toString());

				resource.setIp_address(rs.getString(8));
				resource.setMac_address(rs.getString(9));
				resource.setFaculty(rs.getString(10));
				resource.setInventory_id(rs.getString(11));

				java.sql.Blob settings = rs.getBlob(7);
				byte[] bdata = settings.getBytes(1, (int) (settings.length()));

				String s = new String(bdata);

				resource.setSettings(s);

				return resource;
			}

		};

		Resource res = (Resource) this.jdbcTemplate
				.queryForObject(strSql, mapper);

		return res;

	}

	
	// Retrieves the resources from the database and returns them in the form of data structure.  
	
	@Override
	public List<Resource> getResource(SearchFilter filter) {

		
		
		String sql = null;

		String where = " where 1=1 ";

		// company filter
		if (filter.getCompanyFilter() != null
				&& filter.getCompanyFilter() != "") {

			where += " and resource_company='" + filter.getCompanyFilter()
					+ "'";
		}

		// resource id
		if (filter.getResourceNameFilter() != null
				&& !filter.getResourceNameFilter().isEmpty()) {
			where += " and resource_id like'" + filter.getResourceNameFilter()
					+ "%" + "'";
		}

		// faculty filter
		if (filter.getFacultyFilter() != null
				&& !filter.getFacultyFilter().isEmpty()) {
			where += " and faculty='" + filter.getFacultyFilter() + "'";
		}

		sql = "select * from tbl_resource " + where;

		RowMapper mapper = new RowMapper() {

			public Resource mapRow(ResultSet rs, int rowNum)
					throws SQLException {

				Resource resource = new Resource();

				resource.setResource_id(rs.getString(2));
				resource.setResource_name(rs.getString(3));
				resource.setResource_type(rs.getString(4));
				resource.setResource_company(rs.getString(5));

				resource.setIp_address(rs.getString(8));
				resource.setMac_address(rs.getString(9));
				resource.setFaculty(rs.getString(10));
				resource.setInventory_id(rs.getString(11));

				return resource;

			}

		};

		List<Resource> res = (List<Resource>) this.jdbcTemplate
				.query(sql, mapper);

		return res;

	}

	//Returns distinct resource company from the database in the form of a data structure.      
	public List<String> getDistinctResourceCompany() {

		String sqlQuery = "select distinct resource_company from tbl_resource";

		List<String> companyList = (List<String>) jdbcTemplate.queryForList(
				sqlQuery, String.class);
		return companyList;
	}

	//Updates an already existing resource in the database.
	public int updateResource(Resource res) {

		
             String updateQuery = "update tbl_resource set resource_name=?, resource_type=?,resource_company=?,resource_id=?, settings=?,ip_address=?,mac_address=?,faculty=?,inventory_id=? where resource_id=?";
             int rows = this.jdbcTemplate.update(
				updateQuery,

				new Object[] { pres.getResource_name(),
						pres.getResource_type(), pres.getResource_company(),
						pres.getResource_id(), pres.getSettings(),
						pres.getIp_address(), pres.getMac_address(),
						pres.getFaculty(), pres.getInventory_id(),
						pres.getResource_id() });

		return rows;
	}

}
