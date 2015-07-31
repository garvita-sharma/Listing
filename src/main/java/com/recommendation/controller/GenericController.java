package com.recommendation.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Map;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.util.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.recommendation.dao.IsubcategoryDetailsdao;
import com.recommendation.dto.ListingResponseDTO;

@Controller
public class GenericController {

	@Autowired
	ServletContext context;

	@Autowired
	IsubcategoryDetailsdao subcatdetaildao;

	@RequestMapping("/home")
	public String getSubcategoryDetails(Model model) {
		Map<String, StringBuilder> subcatnameidmap = subcatdetaildao
				.getSubcategoryDetails();
//		Map<String, StringBuilder> subcatnameidmap = new HashMap<String, StringBuilder>();
		System.out.println(subcatnameidmap);

		model.addAttribute("subcatmap", subcatnameidmap);
		return "home";
	}

	@ResponseBody
	@RequestMapping(value = "/getRecommendation", method = RequestMethod.POST)
	public ListingResponseDTO getRecommendation(
			@RequestParam("selectedoption") String checkbox,
			@RequestParam("subcat_id") String subcategory_id, @RequestParam("subCatName") String subCatName, @RequestParam("title") String title, @RequestParam("description") String description, @RequestParam("brandIds") String brandIds, @RequestParam("brandRadio") int brandRadio) {

		System.out.println("Checkbox :" + checkbox + "   Subcat:"
				+ subcategory_id+" title "+title+" description "+description+" brandIds "+brandIds+" brandRadio "+brandRadio);

		ListingResponseDTO response = null;
		try {
			if (checkbox.equalsIgnoreCase("BestSelling"))
				response = subcatdetaildao
						.downloadBestSellingDetails(subcategory_id, subCatName, title, description, brandIds, brandRadio);
		} catch (Exception e) {
			e.printStackTrace();
			response = new ListingResponseDTO(
					"Internal error, try after some time:", false, "", "");
		}
		System.out.println(response);
		return response;
	}

	@RequestMapping(value = "/DownloadEmailNot", method = RequestMethod.GET)
	public void DownloadEmailNot(HttpServletResponse response, @RequestParam("emailPath") String emailPath) {
		try {
			System.out.println("Downloading email listing...");

			InputStream fileStream = new FileInputStream(new File(
					context.getRealPath("resources")
							+ emailPath));
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition",
					"attachment; filename="+emailPath.substring(11));
			IOUtils.copy(fileStream, response.getOutputStream());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@RequestMapping(value = "/DownloadMobileNot", method = RequestMethod.GET)
	public void DownloadMobileNot(HttpServletResponse response, @RequestParam("mobilePath") String mobilePath) {
		try {
			System.out.println("Downloading mobile listing...");

			InputStream fileStream = new FileInputStream(new File(
					context.getRealPath("resources")
							+ mobilePath));
			response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet");
			response.setHeader("Content-Disposition",
					"attachment; filename="+mobilePath.substring(11));
			IOUtils.copy(fileStream, response.getOutputStream());
			response.flushBuffer();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
