package de.jura.util;

import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import de.jura.res.data.PrimaryResource;


// Exports the database into an excel sheet to be used for the official purpose. The column header of the sheets are the variable names of the resource object.       
public class ExportExcl {

	public static HSSFWorkbook exportToExcl(List<Resource> res) {

		HSSFWorkbook hwb = new HSSFWorkbook();

		HSSFSheet sheet = hwb.createSheet("new sheet");
		int rowNum = 0;
		HSSFRow rowhead = sheet.createRow((short) rowNum);
		rowhead.createCell((short) 0).setCellValue("inventory_id");
		rowhead.createCell((short) 1).setCellValue("resource_id");
		rowhead.createCell((short) 2).setCellValue("resource_name");
		rowhead.createCell((short) 3).setCellValue("resource_type");
		rowhead.createCell((short) 4).setCellValue("resource_company");
		
		rowhead.createCell((short) 6).setCellValue("ip_address");
		rowhead.createCell((short) 7).setCellValue("mac_address");
		rowhead.createCell((short) 8).setCellValue("faculty");

		for (Resource reso : res) {

			HSSFRow row = sheet.createRow((short) ++rowNum);
			

			row.createCell((short) 0).setCellValue((res.getInventory_id()));
			row.createCell((short) 1).setCellValue(res.getResource_id());
			row.createCell((short) 2).setCellValue(res.getResource_name());
			row.createCell((short) 3).setCellValue(res.getResource_type());
			row.createCell((short) 4).setCellValue(res.getResource_company());

			row.createCell((short) 6).setCellValue(res.getIp_address());
			row.createCell((short) 7).setCellValue(res.getMac_address());
			row.createCell((short) 8).setCellValue(res.getFaculty());

		}

		

		return hwb;

	}

}
