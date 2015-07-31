package com.recommendation.dao;

import java.util.Map;

import com.recommendation.dto.ListingResponseDTO;

public interface IsubcategoryDetailsdao {
	
	public Map<String, StringBuilder> getSubcategoryDetails();
	
	public ListingResponseDTO downloadBestSellingDetails(String Subcategory_id, String subCatName, String title, String description, String brandIds, int brandRadio);
	
	public int insertSellerSupc(Map<String, String> sellerSupcMap, String sDate, String eDate);


}
