package de.jura.res.data;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.JAXBException;
import javax.xml.bind.UnmarshalException;

import de.jura.system.data.Field;
import de.jura.util.XMLParser;


// Represents the properties of the resource object. The properties includes identifier, name, type, resource company, inventory identifier, ip address, etc. 
public class Resource {

	private int id;
	private String resource_id;
	private String resource_name;
	private String resource_type;
	private String resource_company;
	private String installation_date;
	private String ip_address;
	private String mac_address;
	private String faculty;
	private String settings;
	private String inventory_id;

	private List<Field> fields;
    
	// Method returns the fields in the form of a data structure.
	public List<Field> getFieldList() {
		if (settings != null && fields == null) {
			fields = new ArrayList<Field>();
			// Parse n return
			try {
				Field field = XMLParser.parseResource(this.settings);
				fields.addAll(field.getFields());
				
			} catch (UnmarshalException e) {

				e.printStackTrace();
			} catch (JAXBException e) {

			}

		}
		if (fields == null) {

			fields = new ArrayList<Field>();
		}

		return fields;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getResource_id() {
		return resource_id;
	}

	public void setResource_id(String resource_id) {
		this.resource_id = resource_id;
	}

	public String getResource_name() {
		return resource_name;
	}

	public void setResource_name(String resource_name) {
		this.resource_name = resource_name;
	}

	public String getResource_type() {
		return resource_type;
	}

	public void setResource_type(String resource_type) {
		this.resource_type = resource_type;
	}

	public String getResource_company() {
		return resource_company;
	}

	public void setResource_company(String resource_company) {
		this.resource_company = resource_company;
	}

	public String getInstallation_date() {
		return installation_date;
	}

	public void setInstallation_date(String installation_date) {
		this.installation_date = installation_date;
	}

	public String getIp_address() {
		return ip_address;
	}

	public void setIp_address(String ip_address) {
		this.ip_address = ip_address;
	}

	public String getMac_address() {
		return mac_address;
	}

	public void setMac_address(String mac_address) {
		this.mac_address = mac_address;
	}

	public String getFaculty() {
		return faculty;
	}

	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}

	public String getSettings() {
		return settings;
	}

	public void setSettings(String settings) {
		this.settings = settings;
	}

	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	public String getInventory_id() {
		return inventory_id;
	}

	public void setInventory_id(String inventory_id) {
		this.inventory_id = inventory_id;
	}

	

}