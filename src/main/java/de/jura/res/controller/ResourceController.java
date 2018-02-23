package de.jura.res.controller;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.faces.context.FacesContext;
import javax.faces.event.AjaxBehaviorEvent;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.richfaces.component.UIExtendedDataTable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import de.jura.res.data.PrimaryResource;
import de.jura.res.service.ResourceServiceImplementor;
import de.jura.util.ExportExcel;
import de.jura.util.SearchFilter;

// The controller class that handles the request from the User interface.

@Component("resController")
public class ResourceController {

	@Autowired
	ResourceServiceImplementor resService;

	private static final String SELECTION_ALL = "ALL";

	private String rowId;

	private String resId;

	private PrimaryResource primaryResource;

	// it requires only setter method but not the getter
	private String link;

	// This list is for displaying primary resources
	// in the main table and also for the search operation
	private List<PrimaryResource> presList = new ArrayList<PrimaryResource>();

	private String currentCompanyItem = SELECTION_ALL;
	private String currentFacultyItem = SELECTION_ALL;

	private List<SelectItem> companyList = new ArrayList<SelectItem>();

	/* For the extended data table selection part */
	private Collection<Object> selection;

	// private List<PrimaryResource> selectionItems = new
	// ArrayList<PrimaryResource>();

	public Collection<Object> getSelection() {
		return selection;
	}

	public void setSelection(Collection<Object> selection) {
		this.selection = selection;
	}

	private List<SelectItem> facultyList = new ArrayList<SelectItem>();

	
	SearchFilter filter = new SearchFilter();

	public void setCurrentCompanyItem(String currentCompanyItem) {

		this.currentCompanyItem = currentCompanyItem;

	}

	public String getCurrentCompanyItem() {
		return currentCompanyItem;
	}

	public String getCurrentFacultyItem() {
		return currentFacultyItem;
	}

	public void setCurrentFacultyItem(String currentFacultyItem) {
		this.currentFacultyItem = currentFacultyItem;
	}

	// For company based filter
	public List<SelectItem> getCompanyList() {

		SelectItem item = new SelectItem(SELECTION_ALL);
		companyList.clear();
		companyList.add(item);
		for (String company : getDistinctResourceCompany()) {
			item = new SelectItem(company);
			companyList.add(item);
		}

		return companyList;
	}

	public String getRowId() {
		return rowId;
	}

	public void setRowId(String rowId) {
		this.rowId = rowId;
	}

	public void setFacultyList(List<SelectItem> facultyList) {
		this.facultyList = facultyList;
	}

	public List<SelectItem> getFacultyList() {

		SelectItem item = new SelectItem(SELECTION_ALL);
		facultyList.clear();
		facultyList.add(item);

		String[] facultyName = { "edvteam", "cip-pool", "bibliothek",
				"wirtschaftsrecht" };

		for (String name : facultyName) {

			item = new SelectItem(name);
			facultyList.add(item);

		}

		return facultyList;
	}

	public void setCompanyList(List<SelectItem> companyList) {
		this.companyList = companyList;
	}

	
    // retrieves the list of resources from the database. 
	public List<PrimaryResource> getPresList() {

		searchAction();
		return presList;

	}

	// This method listens the row selection of main table in the index page

	public List<String> getDistinctResourceCompany() {
		return resService.retrieveDistinctResourceCompany();
	}

	public void setResId(String resId) {
		this.resId = resId;
	}

	public String getResId() {
		return resId;
	}
// Method used for searching resources based on different filters.    
	public void searchAction() {

		if (currentCompanyItem.equals(SELECTION_ALL)) {
			filter.setCompanyFilter("");

		} else {
			filter.setCompanyFilter(currentCompanyItem);

		}

		if (currentFacultyItem.equals(SELECTION_ALL)) {
			filter.setFacultyFilter("");
		} else {
			filter.setFacultyFilter(currentFacultyItem);
		}

		System.out.println("the resId is:" + resId);
		filter.setResourceNameFilter(resId);
		presList = resService.retrieveResource(filter);

	}

	// It exports the resource table into an excel sheet.
	public void exportExcel() {

		List<PrimaryResource> pres = resService
				.retrieveResource(new SearchFilter());

		HSSFWorkbook workbook = ExportExcel.exportToExcel(pres);

		HttpServletResponse response = (HttpServletResponse) FacesContext
				.getCurrentInstance().getExternalContext().getResponse();
		response.reset();

		response.setContentType("application/excel");
		response.setHeader("Content-Disposition",
				"attachment;filename=\"test.xls\"");

		try {
			OutputStream outputStream = response.getOutputStream();
			workbook.write(outputStream);
			outputStream.close();
			FacesContext.getCurrentInstance().responseComplete();
		} catch (IOException exp) {
			exp.printStackTrace();
		}

	}

	public void editAction(AjaxBehaviorEvent event) {

		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();

	}

	public PrimaryResource getPrimaryResource() {
		return primaryResource;
	}

	public void setPrimaryResource(PrimaryResource primaryResource) {
		this.primaryResource = primaryResource;
	}

	public void valueChanged(ValueChangeEvent event) {

		if (null != event.getNewValue()) {

			currentCompanyItem = event.getNewValue().toString();

			System.out.println("The current item is:" + currentCompanyItem);

		}

	}

	public void selectionListener(AjaxBehaviorEvent event) {
		
		UIExtendedDataTable dataTable = (UIExtendedDataTable) event
				.getComponent();

		System.out.println("The dataTable is:" + dataTable.toString());

		Object originalKey = dataTable.getRowKey();
		System.out.println("The original key is:" + dataTable.getRows());
		System.out.println("The getrowkey is:" + originalKey);
		System.out.println("The selection is:" + selection);

		for (Object selectionKey : selection) {
			dataTable.setRowKey(selectionKey);
			if (dataTable.isRowAvailable()) {

				primaryResource = (PrimaryResource) dataTable.getRowData();

			}
		}
		dataTable.setRowKey(originalKey);
	}

	public String menuItemAction() {

		System.out.println(getLink());
		return getLink();
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
