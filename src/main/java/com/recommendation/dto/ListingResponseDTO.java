package com.recommendation.dto;

import java.io.Serializable;

public class ListingResponseDTO implements Serializable{

	private static final long serialVersionUID = -216599162380779409L;

	private String message;
	
	private Boolean success;
	
	private String mobilePath;
	
	public String getMobilePath() {
		return mobilePath;
	}

	public void setMobilePath(String mobilePath) {
		this.mobilePath = mobilePath;
	}

	public String getEmailPath() {
		return emailPath;
	}

	public void setEmailPath(String emailPath) {
		this.emailPath = emailPath;
	}

	private String emailPath;

	public ListingResponseDTO() {
		super();
		// TODO Auto-generated constructor stub
	}


	public ListingResponseDTO(String message, Boolean success,
			String mobilePath, String emailPath) {
		super();
		this.message = message;
		this.success = success;
		this.mobilePath = mobilePath;
		this.emailPath = emailPath;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getSuccess() {
		return success;
	}

	public void setSuccess(Boolean success) {
		this.success = success;
	}

	@Override
	public String toString() {
		return "ListingResponseDTO [message=" + message + ", success="
				+ success + ", mobilePath=" + mobilePath + ", emailPath="
				+ emailPath + "]";
	}

	
}
