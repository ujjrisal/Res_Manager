package de.jura.res.service;

import java.util.List;

import de.jura.res.data.Resource;
import de.jura.system.data.Field;
import de.jura.util.SearchFilter;


// ResourceService interface that declares the method to be implemented from the implementing class. The method includes save, delete, update, retrieve resources  
public interface ResourceService {

	public int deleteResource(String resID);

	public int saveResource(Resource res, List<Field> fieldList);

	public int updateResource(Resource res);

	public List<String> retrieveDistinctResourceCompany();

	public List<Resource> retrieveResource(SearchFilter filter);

	public Resource retrieveResource(String resId);

}
