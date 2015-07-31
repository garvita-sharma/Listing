package com.recommendation.dto;

import java.io.Serializable;

public class subcategoryDetailsDTO implements Serializable {

	private static final long serialVersionUID = 1L;

	private String subcategory_name;
	private String subcategory_id;

	public String getSubcategory_name() {
		return subcategory_name;
	}

	public void setSubcategory_name(String subcategory_name) {
		this.subcategory_name = subcategory_name;
	}

	public String getSubcategory_id() {
		return subcategory_id;
	}

	public void setSubcategory_id(String subcategory_id) {
		this.subcategory_id = subcategory_id;
	}

}
