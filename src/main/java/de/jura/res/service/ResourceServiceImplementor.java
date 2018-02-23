package de.jura.res.service;

import java.util.List;

import javax.xml.bind.JAXBException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jura.res.dao.ResourceDAOImplementor;
import de.jura.res.data.Resource;
import de.jura.system.data.Field;
import de.jura.util.SearchFilter;
import de.jura.util.XMLParser;

// An implementation class of the ResourceService interface. 

@Component
public class ResourceServiceImplementor implements ResourceService {

	@Autowired
	private ResourceDAOImplementor resDao;

	// Calls the getResource method of the dao layer to retrieve the resource objects in the form of list data structure.   
	public List<Resource> retrieveResource(SearchFilter filter) {

		return resDao.getResource(filter);
	}

	
	// Calls the getResource method of the dao layer to retrieve the resource having the particular resId. 
	public Resource retrieveResource(String resId) {
		
		Resource res = resDao.getResource(resId);
		String settings = res.getSettings();

		return pres;

	}

	// Calls the deleteResource method of the dao layer to delete the resource having the particular resId. 
	public int deleteResource(String resID) {

		int retVal = resDao.deleteResource(resID);
		return retVal;
	}

	//Calls the updateResource method of the dao layer to update the resource  
	public int updateResource(Resource res) {

		Field field = new Field();

		field.getFields().addAll(pres.getFieldList());
		int retVal = -1;
		try {
			String settings = XMLParser.getXML(field);
			pres.setSettings(settings);
			retVal = resDao.updateResource(res);
		} catch (JAXBException e) {
			e.printStackTrace();
		}

		return retVal;
	}

    // Returns the different companies that the resources belong to. An example could be two different resources might belong to the same company as well as to the different companies.   
	public List<String> retrieveDistinctResourceCompany() {

		return resDao.getDistinctResourceCompany();
	}

    // Calls the saveResource method of the dao layer to save the new resource object in the database. 
	public int saveResource(Resource res, List<Field> fieldList) {

		Field field = new Field();

		field.getFields().addAll(fieldList);

		String settings;

		try {

			settings = XMLParser.getXML(field);

			pres.setSettings(settings);

		} catch (JAXBException e) {

			e.printStackTrace();
		}

		int retVal = resDao.saveResource(res);

		return retVal;
	}

}
