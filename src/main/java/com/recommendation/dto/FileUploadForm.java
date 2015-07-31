package com.recommendation.dto;

import java.io.Serializable;
import java.util.List;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadForm implements Serializable{

	/**
	 * @author amit
	 */
	private static final long serialVersionUID = 1152340004650978544L;

	private List<MultipartFile> files;
	
	public FileUploadForm() {
		super();
		// TODO Auto-generated constructor stub
	}

	public FileUploadForm(List<MultipartFile> files) {
		super();
		this.files = files;
	}

	public List<MultipartFile> getFiles() {
		return files;
	}

	public void setFiles(List<MultipartFile> files) {
		this.files = files;
	}

	@Override
	public String toString() {
		return "FileUploadForm [files=" + files + "]";
	}
	
}
