/**
 * 
 */
package de.jura.ui.data;


public class JuraMenuItem {

	String label;
	
	String link;

	public JuraMenuItem(String label,String link) {
		this.label=label;
		this.link=link;
	}
	
	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}	
}
