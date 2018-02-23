/**
 * 
 */
package de.jura.ui.data;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Pawan
 * 
 */
public class JuraMenu {

	String image;

	String label;

	String link;

	List<JuraMenuItem> items = new ArrayList<JuraMenuItem>();

	public JuraMenu(String label, String link) {

		this.label = label;
		this.link = link;

	}

	public JuraMenu(String label) {
		this.label = label;
	}

	public void add(JuraMenuItem item) {
		this.items.add(item);
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public List<JuraMenuItem> getItems() {
		return items;
	}

	public void setItems(List<JuraMenuItem> items) {
		this.items = items;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
