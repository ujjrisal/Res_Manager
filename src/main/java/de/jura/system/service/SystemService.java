package de.jura.system.service;

import java.util.List;

import de.jura.system.data.Field;

public interface SystemService {
	
public List<Field> getFieldSets();
	
	public List<Field> getFields(int fieldSetId);
	
	public int addFieldSet(Field field);
	
	public int addField(Field field);
	
	public int editField(Field field);
	
	public int deleteFieldSet(Field field);

}
