package de.jura.res.controller.form;

import java.util.ArrayList;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Component;

import de.jura.res.data.PrimaryResource;
import de.jura.res.service.ResourceServiceImplementor;
import de.jura.system.data.Field;
import de.jura.system.service.SystemServiceImpl;

// Some functionalities of controller class are written here inorder to make the code manageable.      

//@ManagedBean(name = "resEditorForm")
@Component("resEditorForm")
public class ResourceEditorForm {

	// @ManagedProperty(value = "#{resService}")
	@Autowired
	private ResourceServiceImplementor resService;

	// @ManagedProperty(value = "#{systemService}")
	@Autowired
	private SystemServiceImpl sysService;

	@Autowired
	HttpServletRequest request;

	private PrimaryResource resource;

	private PrimaryResource resource1;

	private List<Field> fieldList;

	private String rowResId;

	// @ManagedProperty(value = "#{param.resourceId}")
	// @Autowired(required = true)..it does not work
	// @Autowired
	public String resourceId;

	// @ManagedProperty(value = "#{param.action}")
	// @Autowired(required = true)..it does not work
	public String action;

	public String getRowResId() {
		return rowResId;
	}

	public void setRowResId(String rowResId) {
		this.rowResId = rowResId;
	}

	private List<SelectItem> resNameList = new ArrayList<SelectItem>();

	
	// It returns the resource object either for the edit/save purpose  
	public PrimaryResource getResource() {
		if (resource == null) {

			resourceId = request.getParameter("resourceId");

			// This is for edit purpose
			if (resourceId != null) {
				resource = resService.retrievePrimaryResource(resourceId);

			} else {

				// This is for save purpose
				resource = new PrimaryResource();
				resource.setFields(this.sysService.getFieldSets());

			}
		}

		return resource;

	}

	public void getResourceById() {

		resource1 = resService.retrievePrimaryResource(rowResId);

	}

	public PrimaryResource getResource1() {
		return resource1;
	}

	public void setResource1(PrimaryResource resource1) {
		this.resource1 = resource1;
	}

	
	 //Calls the saveResource method of the resourceServiceImpl class to save a new resource in the database. Only the users having the role create_resource are allowed to save a new resource in the database.   
	// After the successful save operation, the new resource could be seen in the table of the newindex.xhtml page. 
	
	@PreAuthorize("hasRole('create_resource')")
	public String save() {
		String message = "inserted successfully!!";
		String retString = "null";

		int retVal = resService.saveResource(resource, resource.getFieldList());

		if (retVal == 1) {

			System.out.println("retval");

			FacesContext.getCurrentInstance().addMessage(null,
					new FacesMessage(message));

			retString = "newindex?faces-redirect=true";
		}

		return retString;

	}

	public ResourceServiceImplementor getResService() {
		return resService;
	}

	public void setResService(ResourceServiceImplementor resService) {
		this.resService = resService;
	}

	public String cancelAction() {
		this.resource = null;
		this.action = null;

		return "newindex?faces-redirect=true";

	}

	public void setResource(PrimaryResource resource) {
		this.resource = resource;
	}

	public void setResNameList(List<SelectItem> resNameList) {
		this.resNameList = resNameList;
	}

	// enumerates the resource type
	private enum ResourceType {
		CPU("cpu"), Printer("printer"), TapeDrive("tape drive"), Scanner(
				"scanner");

		String value;

		ResourceType(String value) {
			this.value = value;
		}

		public String getValue() {
			return this.value;
		}
	}

	public List<SelectItem> getResNameList() {

		SelectItem item;
		resNameList.clear();

		for (ResourceType resource : ResourceType.values()) {

			item = new SelectItem(resource);
			resNameList.add(item);

		}

		return resNameList;
	}

	
	// The edit action loads the resource data into the form for the edit purpose. Users having the role 'edit_resource' allowed to edit the resource.    
	@PreAuthorize("hasRole('edit_resource')")
	public String editAction() {

		this.resource = null;
		this.action = null;

		String returnString = "insert.xhtml?faces-redirect=true&resourceId="
				+ getRowResId() + "&action=edit";

		return returnString;

	}

	
	// Used to delete a resource having the particular resource Id. Users having the role 'delete_resource' allowed to delete the resource  
	@PreAuthorize("hasRole('delete_resource')")
	public void deleteAction() {

		resService.deleteResource(rowResId);

	}

	public String getResourceId() {
		return resourceId;
	}

	public void setResourceId(String resourceId) {
		this.resourceId = resourceId;
	}

	@PreAuthorize("hasRole('edit_resource')")
	public String updateResource() {

		int retVal = resService.updateResource(this.resource);
		System.out.println("The retVal is:" + retVal);
		String retString = null;
		if (retVal == 1) {
			retString = "welcome_new.xhtml?faces-redirect=true";
		}
		return retString;
	}

	public SystemServiceImpl getSysService() {
		return sysService;
	}

	public void setSysService(SystemServiceImpl sysService) {
		this.sysService = sysService;
	}

	public String getAction() {

		if (action == null) {

			action = request.getParameter("action");
		}

		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public String detailedViewAction() {

		System.out.println("The rowresId is:" + rowResId);

		String returnString = "viewresource.xhtml?faces-redirect=true&resourceId="
				+ rowResId;

		return returnString;

	}

}
