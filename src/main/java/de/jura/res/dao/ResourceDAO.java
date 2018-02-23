package de.jura.res.dao;

import java.util.List;

import de.jura.res.data.Resource;
import de.jura.util.SearchFilter;

// The interface that declares methods to be implemented from the implementing class. The methods in the interfaces are considered as a contract that provides the application functionalities.     

public interface ResourceDAO {

	public int deleteResource(String resId);

	public int saveResource(Resource pres);

	public int updateResource(Resource pres);

	public List<Resource> getResource(SearchFilter filter);

	public List<String> getDistinctResourceCompany();
	
	public Resource getResource(String resId);
	
}
