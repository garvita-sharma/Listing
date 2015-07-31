package com.recommendation.service.impl;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.recommendation.dao.IsubcategoryDetailsdao;
import com.recommendation.services.IConversionReportService;

@Service
public class ConversionReportImpl implements IConversionReportService{

	@Autowired
	IsubcategoryDetailsdao subcategoryDetailsdao;
	
	@Override
	public int getReport(MultipartFile file, String sDate, String eDate) {
		try {
			Workbook workbook;
			if(file.getOriginalFilename().endsWith("xlsx")){
				workbook = new XSSFWorkbook(file.getInputStream());
			}else if(file.getOriginalFilename().endsWith("xls")){
				workbook = new HSSFWorkbook(file.getInputStream());
			}else{
				return -1;
			}
			Sheet sheet = workbook.getSheetAt(0);
			int firstRowNum = sheet.getFirstRowNum();
			int lastRowNum = sheet.getLastRowNum();
			System.out.println("firstRow="+firstRowNum+"{}lastRow="+lastRowNum);
			Row row;
			String sellerCode, supcs;
			Map<String, String> sellerSupcMap = new HashMap<String, String>();
			int noOfEmptyRow = 0;
			for(int i=firstRowNum+1;i <= lastRowNum;i++){
				if(noOfEmptyRow > 10) break;
				row = sheet.getRow(i);
				if(row == null){
					noOfEmptyRow++;
					continue;
				}
				sellerCode = row.getCell(0)==null?"":row.getCell(0).toString();
				supcs = row.getCell(7)==null?"":row.getCell(7).toString();
				if(sellerCode == null || sellerCode.isEmpty()||supcs == null || supcs.isEmpty()){
					noOfEmptyRow++;
					continue;
				}
				sellerSupcMap.put(sellerCode, supcs);
			}
			System.out.println("total xsl size ="+sellerSupcMap.size());
			return subcategoryDetailsdao.insertSellerSupc(sellerSupcMap, sDate, eDate);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}

}
