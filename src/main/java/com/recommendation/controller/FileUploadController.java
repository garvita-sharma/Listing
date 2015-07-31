package com.recommendation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.recommendation.services.IConversionReportService;

@Controller
public class FileUploadController {

	@Autowired
	IConversionReportService conversionReportService;
	
	@Autowired
	ServletContext context;
	
	@ResponseBody
	@RequestMapping(value="/upload", method = RequestMethod.POST)
	private int upload(@RequestParam MultipartFile uploadFile, HttpServletResponse response, @RequestParam String sDate, @RequestParam String eDate){
		System.out.println(uploadFile.getOriginalFilename()+sDate+eDate);
		try{
			return conversionReportService.getReport(uploadFile, sDate, eDate);
		}catch(Exception e){
			e.printStackTrace();
			return -1;
		}
	}
	
	@RequestMapping(value = "/DownloadConversionReport", method = RequestMethod.GET)
	public void DownloadEmailNot(HttpServletResponse response, @RequestParam("reportPath") String reportPath) {
		try {
			System.out.println("Downloading email listing.........");

			InputStream fileStream = new FileInputStream(new File(
					context.getRealPath("resources")
							+ reportPath));
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition",
					"attachment; filename="+reportPath.substring(11));
			IOUtils.copy(fileStream, response.getOutputStream());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
