package de.jura.system.dao;

import java.util.List;

import de.jura.system.data.Field;


//FieldDao interface declares the method that are overriden from the fieldDAOImpl.
public interface FieldDao {

	public List<Field> getFieldSets();
	
	public List<Field> getFields(int fieldSetId);
	
	public int addFieldset(Field field);
	
	public int addField(Field field);
	
	public int editField(Field field);
	
	public int deleteFieldSet(Field field);
}
