package com.recommendation.dao.impl;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import javax.servlet.ServletContext;

import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import com.recommendation.dao.IsubcategoryDetailsdao;
import com.recommendation.dto.ListingResponseDTO;
import com.recommendation.services.IverticaConnection;

@Repository
public class subcategoryDetailsdaoimpl implements IsubcategoryDetailsdao {

	@Autowired
	ServletContext context;

	@Autowired
	IverticaConnection vconn;

	@Value("${subcat.list}")
	private String SubcategoryListQuery;

	@Value("${bestselling1}")
	private String bestSelling1;

	@Value("${bestselling2_1}")
	private String bestSelling2_1;
	
	@Value("${bestselling2_2}")
	private String bestSelling2_2;
	
	@Value("${bestselling2_3}")
	private String bestSelling2_3;

	@Value("${bestselling3}")
	private String bestSelling3;

	@Value("${bestselling4}")
	private String bestSelling4;

	@Value("${bestselling5}")
	private String bestSelling5;

	@Value("${bestselling6}")
	private String bestSelling6;

	@Value("${bestselling7}")
	private String bestSelling7;

	@Value("${bestselling8}")
	private String bestSelling8;

	@Value("${bestselling9}")
	private String bestSelling9;

	@Value("${bestselling10}")
	private String bestSelling10;

	@Value("${bestselling11}")
	private String bestSelling11;

	@Value("${bestselling12}")
	private String bestSelling12;

	@Value("${bestselling13}")
	private String bestSelling13;

	@Value("${bestselling14}")
	private String bestSelling14;

	@Value("${bestselling15}")
	private String bestSelling15;
	
	@Value("${bestselling16}")
	private String bestSelling16;

	@Value("${bestselling17}")
	private String bestSelling17;

	@Value("${bestselling18}")
	private String bestSelling18;

	@Value("${bestselling19}")
	private String bestSelling19;

	@Value("${Type}")
	private String type;
	
	@Value("${Title}")
	private String title;
	
	@Value("${Discription}")
	private String discription;
	
	@Value("${Duration}")
	private String duration;
	
	@Value("${truncateConversionTable}")
	private String truncateConversionTable;
	
	@Value("${insertConversionTable}")
	private String insertConversionTable;
	
	@Value("${conversionQuery}")
	private String conversionQuery;
	
	@Value("${conversionSheetPath}")
	private String conversionSheetPath;
	
	@Override
	public Map<String, StringBuilder> getSubcategoryDetails() {

		LinkedHashMap<String, StringBuilder> subcatnameIdmap = new LinkedHashMap<>();
		Connection conn = vconn.getverticaConnection();

		try {

			Statement stmt = null;

			stmt = conn.createStatement();
			System.out.println("Getting List from vertica");
			ResultSet rs = stmt.executeQuery(SubcategoryListQuery);

			System.out.println("Size of reslut fetched:" + rs.getFetchSize());

			while (rs.next()) {
				System.out.println(rs.getString(1));
				StringBuilder subcatId;
				if (subcatnameIdmap.containsKey(rs.getString(1))) {
					subcatId = subcatnameIdmap.get(rs.getString(1));
					subcatnameIdmap.put(rs.getString(1), subcatId.append(","
							+ String.valueOf(rs.getLong(2))));
				} else {
					subcatId = new StringBuilder();
					subcatnameIdmap.put(rs.getString(1),
							subcatId.append(String.valueOf(rs.getLong(2))));
				}
			}

			System.out.println("Size of map:" + subcatnameIdmap.size());

		} catch (Exception e) {
			e.printStackTrace();

		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return subcatnameIdmap;
	}

	@Override
	synchronized public ListingResponseDTO downloadBestSellingDetails(String Subcategory_id, String subCatName, String title, String description, String brandIds, int brandRadio) {

		if(title!=null && !title.isEmpty()){
			this.title = title;
		}
		if(description != null && !description.isEmpty()){
			this.discription = description;
		}
		HSSFWorkbook emailWorkbook = new HSSFWorkbook();

		HSSFSheet emailSheet = emailWorkbook.createSheet("ListingDetails");

		HSSFCellStyle emailCellStyle = emailWorkbook.createCellStyle();
		emailCellStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
		emailCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		
		HSSFWorkbook mobileWorkbook = new HSSFWorkbook();

		HSSFSheet mobileSheet = mobileWorkbook.createSheet("ListingDetails");

		HSSFCellStyle cellStyle = mobileWorkbook.createCellStyle();
		cellStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
		cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);

		Connection conn = vconn.getverticaConnection();

		ResultSet rs = null;

		int rownum = 0;
		
		ListingResponseDTO response = null;
		String emailPath = "";
		String mobilePath = "";
		try {
			PreparedStatement stmt = null;

			stmt = conn.prepareStatement(bestSelling1);
			System.out.println(bestSelling1 + " executed 1:" + stmt.executeUpdate());
			stmt.close();

			if(brandRadio == 1){
				stmt = conn.prepareStatement(bestSelling2_1.replaceFirst("\\?", Subcategory_id));
			}else if(brandRadio == 2){
				stmt = conn.prepareStatement(bestSelling2_2.replaceFirst("\\?", Subcategory_id).replaceFirst("\\?", brandIds));
			}else{
				stmt = conn.prepareStatement(bestSelling2_3.replaceFirst("\\?", Subcategory_id).replaceFirst("\\?", brandIds));
			}
			System.out.println(bestSelling2_1 +" ids "+brandIds+" radio "+brandRadio+"{}"+ Subcategory_id+" executed 2:" + stmt.executeUpdate());
			stmt.close();

			stmt = conn.prepareStatement(bestSelling3);
			System.out.println(bestSelling3 + " executed 3:" + stmt.executeUpdate());
			stmt.close();

			stmt = conn.prepareStatement(bestSelling4.replace("?", Subcategory_id));
			System.out.println(bestSelling4 + Subcategory_id+" executed 4:" + stmt.executeUpdate());
			stmt.close();

			stmt = conn.prepareStatement(bestSelling5);
			System.out.println(stmt + " executed 5:" + stmt.executeUpdate());
			stmt.close();

			stmt = conn.prepareStatement(bestSelling6);
			System.out.println(stmt + " executed 6:" + stmt.executeUpdate());
			stmt.close();

			stmt = conn.prepareStatement(bestSelling7);
			System.out.println(stmt + " executed 7:" + stmt.executeUpdate());
			stmt.close();

			stmt = conn.prepareStatement(bestSelling8);
			System.out.println(stmt + " executed 8:" + stmt.executeUpdate());
			stmt.close();

			stmt = conn.prepareStatement(bestSelling9);
			System.out.println(stmt + " executed 9:" + stmt.executeUpdate());
			stmt.close();

			System.out.println("executing :"+bestSelling10);
			stmt = conn.prepareStatement(bestSelling10);
			System.out.println(stmt + " executed 10:" + stmt.executeUpdate());
			stmt.close();

			stmt = conn.prepareStatement(bestSelling11);
			System.out.println(stmt + " executed 11:" + stmt.executeUpdate());
			stmt.close();

			stmt = conn.prepareStatement(bestSelling12);
			System.out.println(stmt + " executed 12:" + stmt.executeUpdate());
			stmt.close();

			stmt = conn.prepareStatement(bestSelling13);
			System.out.println(stmt + " executed 13:" + stmt.executeUpdate());
			stmt.close();

			stmt = conn.prepareStatement(bestSelling14);
			System.out.println(stmt + " executed 14:" + stmt.executeUpdate());
			stmt.close();
			
			stmt = conn.prepareStatement(bestSelling15);
			System.out.println(stmt + " executed 15:" + stmt.executeUpdate());
			stmt.close();
			
			stmt = conn.prepareStatement(bestSelling16);
			System.out.println(stmt + " executed 16:" + stmt.executeUpdate());
			stmt.close();
			
			stmt = conn.prepareStatement(bestSelling17);
			System.out.println(stmt + " executed 17:" + stmt.executeUpdate());
			stmt.close();
			
			stmt = conn.prepareStatement(bestSelling18);
			System.out.println(stmt + " executed 18:" + stmt.executeUpdate());
			stmt.close();

			stmt = conn.prepareStatement(bestSelling19);
			rs = stmt.executeQuery();
			System.out.println("Final Query executed");
			Row EmailRow = null;
			Cell cell = null;

			EmailRow = emailSheet.createRow(rownum);
			String[] Emailheaders = {"Seller Code", "SUPC1", "SUPC Name1", "SUPC Price1", "SUPC IMG1", "SUPC Detail1", 
					"SUPC Sell1", "SUPC2", "SUPC Name2", "SUPC Price2", "SUPC IMG2", "SUPC Detail2", "SUPC Sell2",
					"SUPC3", "SUPC Name3", "SUPC Price3", "SUPC IMG3", "SUPC Detail3", "SUPC Sell3", 
					"SUPC4", "SUPC Name4", "SUPC Price4", "SUPC IMG4", "SUPC Detail4", "SUPC Sell4",
					"SUPC5", "SUPC Name5", "SUPC Price5", "SUPC IMG5", "SUPC Detail5", "SUPC Sell5",
					"SUPC6", "SUPC Name6", "SUPC Price6", "SUPC IMG6", "SUPC Detail6", "SUPC Sell6"};
			int cellnum1 = 0;
			for(String header: Emailheaders){
				EmailRow.createCell(cellnum1++).setCellValue(header);
			}

			Row mobileRow = mobileSheet.createRow(rownum++);
			String[] mobileheaders={"Seller Code","Type", "Title", "Discription", "Time Stamp", "Duration", "Image URL", "SUPC"};
			cellnum1 = 0;
			for(String header: mobileheaders){
				mobileRow.createCell(cellnum1++).setCellValue(header);
			}
			Date d = new Date();
			SimpleDateFormat ft = new SimpleDateFormat ("yyyy-MMM-dd hh:mm:ss");
			while (rs.next()) {
				/*System.out.println(rownum);*/
				EmailRow = emailSheet.createRow(rownum);
				mobileRow = mobileSheet.createRow(rownum++);
				int EmailCellNum = 0;
				int mobileCellNum = 0;
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(1));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(2));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(3));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getInt(4));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(5));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(6));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(7));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(8));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(9));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getInt(10));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(11));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(12));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(13));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(14));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(15));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getInt(16));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(17));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(18));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(19));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(20));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(21));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getInt(22));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(23));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(24));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(25));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(26));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(27));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getInt(28));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(29));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(30));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(31));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(32));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(33));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getInt(34));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(35));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(36));
				cell = EmailRow.createCell(EmailCellNum++);
				cell.setCellValue(rs.getString(37));
				
				mobileRow.createCell(mobileCellNum++).setCellValue(rs.getString(1));
				mobileRow.createCell(mobileCellNum++).setCellValue(type);
				mobileRow.createCell(mobileCellNum++).setCellValue(this.title);
				mobileRow.createCell(mobileCellNum++).setCellValue(discription);
				mobileRow.createCell(mobileCellNum++).setCellValue(ft.format(d));
				mobileRow.createCell(mobileCellNum++).setCellValue(duration);
				mobileCellNum++;
				mobileRow.createCell(mobileCellNum).setCellValue(rs.getString(2)+","+rs.getString(8)+","+rs.getString(14)+","+rs.getString(20)+","
						+rs.getString(26)+","+rs.getString(32));

			}
			Calendar cal = Calendar.getInstance();
	    	SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy_HH-mm-ss");
	    	String ctDateTime = sdf.format(cal.getTime());
	    	emailPath = "/downloads/ListingRecommendationEmail_"+subCatName+"_"+ctDateTime+".xlsx";
	    	mobilePath = "/downloads/ListingRecommendationMobile_"+subCatName+"_"+ctDateTime+".xlsx";
	    	// Write the workbook in file system
			FileOutputStream out = new FileOutputStream(new File(
					context.getRealPath("resources")
							+ emailPath));
			emailWorkbook.write(out);
			out.close();
			System.out.println("Email File written successfully on disk.");
			
			FileOutputStream out1 = new FileOutputStream(new File(
					context.getRealPath("resources")
							+ mobilePath));
			mobileWorkbook.write(out1);
			out1.close();
			System.out.println("Mobile File written successfully on disk.");
			rs.close();
			stmt.close();
			if(rownum < 2){
				response = new ListingResponseDTO("Empty response returned", false, "", "");
			}
			
		} catch (Exception e) {
			response = new ListingResponseDTO("Some internal error, try after some time", false, "", "");
			e.printStackTrace();

		}
		try {
			conn.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(response == null){
			response = new ListingResponseDTO("success:"+rownum, true, mobilePath, emailPath);
		}
		return response;
	}

	@Override
	public int insertSellerSupc(Map<String, String> sellerSupcsMap, String SDate, String eDate) {
		Connection conn = vconn.getverticaConnection();
		PreparedStatement stmt = null;
		ResultSet rs = null;
		try {
			
			stmt = conn.prepareStatement(truncateConversionTable);
			System.out.println("executing "+stmt);
			stmt.executeUpdate();
			stmt.close();
			StringBuilder query = new StringBuilder(insertConversionTable);
			int noOfRow = 0;
			int noOfRowInserted = 0;
			int noOfRowWrittenonFile = 0 ;
			for(String seller: sellerSupcsMap.keySet()){
				if(noOfRow >= 200 ){
					System.out.println("executing"+query);
					stmt = conn.prepareStatement(query.substring(0, query.length()-9));
					noOfRowInserted+=stmt.executeUpdate();
					noOfRow = 0;
					query = new StringBuilder(insertConversionTable);
				}
				noOfRow++;
				String supcString = sellerSupcsMap.get(seller);
				String[] supcs = supcString.split(",");
				for(String supc : supcs){
					query.append(" select '"+seller+"', '"+supc+"' union all");
				}
			}
			if(noOfRow > 0){
				System.out.println("executingo"+query);
				stmt = conn.prepareStatement(query.substring(0, query.length()-9));
				noOfRowInserted+=stmt.executeUpdate();
			}
			if(noOfRowInserted > 0){
				HSSFWorkbook conversionSheetWorkbook = new HSSFWorkbook();

				HSSFSheet conversionSheet = conversionSheetWorkbook.createSheet("ConversionSheet");

				HSSFCellStyle conversionSheetCellStyle = conversionSheetWorkbook.createCellStyle();
				conversionSheetCellStyle.setFillForegroundColor(HSSFColor.BLUE_GREY.index);
				conversionSheetCellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
				int rowNum = 0;
				stmt = conn.prepareStatement(conversionQuery);
				stmt.setString(1, SDate);
				stmt.setString(2, eDate);
				System.out.println("executing query "+conversionQuery);
				rs = stmt.executeQuery();
				Cell cell;
				Row row;
				while(rs.next()){
					row = conversionSheet.createRow(rowNum++);
					cell = row.createCell(0);
					cell.setCellValue(rs.getString(1));
					cell = row.createCell(1);
					cell.setCellValue(rs.getString(2));
					noOfRowWrittenonFile++;
				}
				FileOutputStream out = null;
				try {
					out = new FileOutputStream(new File(
							context.getRealPath("resources")
									+ conversionSheetPath));
					conversionSheetWorkbook.write(out);
				} catch (IOException e) {
					e.printStackTrace();
				}finally{
					try {
						if(out != null)
						out.close();
					} catch (IOException e) {
						e.printStackTrace();
					}
				}
				System.out.println("conversion File written successfully on disk.");
			}
			return noOfRowWrittenonFile;
		} catch (SQLException e) {
			e.printStackTrace();
			return -1;
		}finally{
			try {
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
	}

	/*private String getInsertQuery(Map<String, String> sellerSupcsMap) {
		// TODO Auto-generated method stub
		StringBuilder query = new StringBuilder(insertConversionTable);
		for(String seller: sellerSupcsMap.keySet()){
			String supcString = sellerSupcsMap.get(seller);
			String[] supcs = supcString.split(",");
			for(String supc : supcs){
				query.append(" select '"+seller+"', '"+supc+"' union all");
			}
		}
		return query.substring(0, query.length()-9);
	}*/

}
