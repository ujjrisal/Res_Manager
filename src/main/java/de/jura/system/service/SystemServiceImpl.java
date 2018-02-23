package de.jura.system.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jura.system.dao.FieldDaoImpl;
import de.jura.system.data.Field;

//@ManagedBean(name = "systemService")
@Component
public class SystemServiceImpl implements SystemService {

	// @ManagedProperty(value = "#{fieldDaoImpl}")
	@Autowired
	FieldDaoImpl fieldDaoImpl;

	@Override
	public List<Field> getFieldSets() {
		// TODO Auto-generated method stub
		List<Field> fieldSets = fieldDaoImpl.getFieldSets();
		for (Field fs : fieldSets) {

			fs.setFields(fieldDaoImpl.getFields(fs.getId()));

		}

		return fieldSets;
	}

	@Override
	public List<Field> getFields(int fielsetId) {

		return fieldDaoImpl.getFields(fielsetId);
	}

	@Override
	public int addFieldSet(Field field) {
		int retVal = this.fieldDaoImpl.addFieldset(field);
		return retVal;

	}

	@Override
	public int addField(Field field) {
		int retVal = this.fieldDaoImpl.addField(field);
		return retVal;
	}

	public int editField(Field field) {

		int retVal = this.fieldDaoImpl.editField(field);
		if (!field.getFields().isEmpty()) {

			for (Field f : field.getFields()) {
				this.fieldDaoImpl.editField(f);
			}
		}
		return retVal;

	}

	@Override
	public int deleteFieldSet(Field field) {
		int retVal = this.fieldDaoImpl.deleteFieldSet(field);
		return retVal;
	}

}
