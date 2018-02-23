package de.jura.system.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElementWrapper;
import javax.xml.bind.annotation.XmlRootElement;


// While introducing a new resource into the system, the admin users could define the settings that belongs to it. 
// The settings are defined in the form of Field that contains field id, name, type, fieldSet_id & the value. 
@XmlRootElement(name = "field")
@XmlAccessorType(XmlAccessType.FIELD)
public class Field {

	private int id;
	private String name;
	private String type;
	private int fieldSet_Id;
	private String value;

	@XmlElementWrapper(name = "fields")
	private List<Field> fields = new ArrayList<Field>();

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public int getFieldSet_Id() {
		return fieldSet_Id;
	}

	public void setFieldSet_Id(int fieldset_Id) {
		this.fieldSet_Id = fieldset_Id;
	}

	public List<Field> getFields() {
		return fields;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {

		this.value = value;
	}

}
