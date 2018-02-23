package de.jura.util;

public class SearchFilter {

	private String companyFilter = null;

	private String resourceNameFilter = null;
	
	private String facultyFilter = null;

	private String filterBy=null;
	
	public String getFacultyFilter() {
		return facultyFilter;
	}

	public void setFacultyFilter(String facultyFilter) {
		this.facultyFilter = facultyFilter;
	}

	public String getFilterBy() {
		return filterBy;
	}

	public void setFilterBy(String filterBy) {
		this.filterBy = filterBy;
	}

	

	public String getCompanyFilter() {
		return companyFilter;
	}

	public void setCompanyFilter(String companyFilter) {
		this.companyFilter = companyFilter;
	}

	public String getResourceNameFilter() {
		return resourceNameFilter;
	}

	public void setResourceNameFilter(String resourceNameFilter) {
		this.resourceNameFilter = resourceNameFilter;
	}

}
