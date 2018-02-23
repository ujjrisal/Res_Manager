/**
 * 
 */
package de.jura.ui.data;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component("menuPanel")
public class MenuPanel {

	List<JuraMenu> items;

	private String currentSelection;

	public List<JuraMenu> getItems() {
		if (items != null)
			return items;

		items = new ArrayList<JuraMenu>();

		createHomeMenu();
		createResourceMenu();
		createSystemMenu();
		createReportMenu();
		createUserMenu();

		return items;
	}

	public void setItems(List<JuraMenu> items) {
		this.items = items;
	}

	private void createHomeMenu() {

		JuraMenu homeMenu = new JuraMenu("Home");

		JuraMenuItem viewResourceItem = new JuraMenuItem("View resource",
				"welcome_new.xhtml?faces-redirect=true");
		homeMenu.add(viewResourceItem);

		items.add(homeMenu);
	}

	private void createResourceMenu() {
		JuraMenu resourceMenu = new JuraMenu("Resource");

		JuraMenuItem viewResourceItem = new JuraMenuItem("View resource",
				"welcome_new.xhtml?faces-redirect=true");
		JuraMenuItem addResourceItem = new JuraMenuItem("Add new resource",
				"insert.xhtml?faces-redirect=true");

		resourceMenu.add(viewResourceItem);
		resourceMenu.add(addResourceItem);

		items.add(resourceMenu);
	}

	private void createSystemMenu() {

		JuraMenu systemMenu = new JuraMenu("System");

		JuraMenuItem systemSettingItem = new JuraMenuItem("System Properties",
				"properties.xhtml?faces-redirect=true");

		systemMenu.add(systemSettingItem);

		items.add(systemMenu);

	}

	private void createReportMenu() {

		JuraMenu reportMenu = new JuraMenu("Report");

		JuraMenuItem exportExcelItem = new JuraMenuItem("Export Excel",

		"exportexcel.xhtml?faces-redirect=true");
		// JuraMenuItem generateLogItem = new JuraMenuItem("Generate Log",
		// "exportexcel.xhtml?faces-redirect=true");

		reportMenu.add(exportExcelItem);
		// reportMenu.add(generateLogItem);

		items.add(reportMenu);

	}

	private void createUserMenu() {

		JuraMenu userMenu = new JuraMenu("User & Roles Management");

		JuraMenuItem addUserItem = new JuraMenuItem("Role Resource",
				"roleresource.xhtml?faces-redirect=true");
		JuraMenuItem addRoleItem = new JuraMenuItem("User Resource",
				"userresource.xhtml?faces-redirect=true");
		userMenu.add(addUserItem);
		userMenu.add(addRoleItem);

		items.add(userMenu);

	}

}
