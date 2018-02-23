package de.jura.system.data;

import javax.xml.bind.JAXBException;

import de.jura.util.XMLParser;

public class FieldMain {

	public static void main(String[] args) throws JAXBException {

		Field field = new Field();
		field.setFieldSet_Id(0);
		field.setId(0);
		field.setName("OS");
		field.setType("TEXT");
		field.setValue("Windows");

		XMLParser parser = new XMLParser();
		System.out.println(parser.getXML(field));

	}

}
