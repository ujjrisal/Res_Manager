package de.jura.system.controller.form;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jura.system.data.Field;
import de.jura.system.service.SystemServiceImpl;



 
@Component
public class PropertiesForm {

	private Field field;
	private String fieldSetName;

	private List<String> typeList = new ArrayList<String>();
	private Integer selectedFieldId;
	private List<Field> fieldsets;

	
	@Autowired
	SystemServiceImpl systemService;

	public List<Field> getFieldsets() {
		if (fieldsets == null) {
			fieldsets = systemService.getFieldSets();
		}
		return fieldsets;
	}

	public void setFieldsets(List<Field> fieldsets) {
		this.fieldsets = fieldsets;
	}

	public SystemServiceImpl getSystemService() {
		return systemService;
	}

	public void setSystemService(SystemServiceImpl systemService) {
		this.systemService = systemService;
	}

	public int addFieldSet() {
		Field newField = new Field();
		newField.setName(this.fieldSetName);
		newField.setType(FieldType.FIELDSET.getValue());
		int retVal = this.systemService.addFieldSet(newField);
		return retVal;

	}

	public void addField() {
		Field newField = getField();

		Integer fieldSetId = getSelectedFieldId();

		if (fieldSetId != null) {

			newField.setFieldSet_Id(fieldSetId);
			this.systemService.addField(newField);
		}

	}

	public Field getField() {
		if (field == null) {
			field = new Field();
		}
		return field;
	}

	public void setField(Field field) {
		this.field = field;
	}

	public List<String> getTypeList() {

		typeList.clear();

		for (FieldType t : FieldType.values()) {
			typeList.add(t.getValue());
		}

		return typeList;

	}

	public void setTypeList(List<String> typeList) {
		this.typeList = typeList;
	}

	public Integer getSelectedFieldId() {
		return selectedFieldId;
	}

	private enum FieldType {
		FIELDSET("FIELDSET"), TEXT("TEXT");

		String value;

		FieldType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public void editAction() {

		Integer fieldId = getSelectedFieldId();
		
		if (fieldId != null) {

			for (Field field : fieldsets) {

				System.out.println("field.getId" + field.getId());

				if (field.getId() == fieldId) {

					this.systemService.editField(field);
					break;

				}

			}
		}

	}

	public void setSelectedFieldId(Integer selectedFieldId) {
		this.selectedFieldId = selectedFieldId;
	}

	public String getFieldSetName() {
		return fieldSetName;
	}

	public void setFieldSetName(String fieldSetName) {
		this.fieldSetName = fieldSetName;
	}

	public void deleteAction() {

		Integer fieldId = getSelectedFieldId();

		if (fieldId != null) {

			for (Field field : fieldsets) {

				if (field.getId() == fieldId) {

					int retVal = this.systemService.deleteFieldSet(field);
					System.out.println(retVal);
					break;

				}

			}
		}

	}
}
