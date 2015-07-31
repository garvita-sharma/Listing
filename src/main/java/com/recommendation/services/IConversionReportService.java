package com.recommendation.services;

import org.springframework.web.multipart.MultipartFile;

public interface IConversionReportService {

	int getReport(MultipartFile file, String sDate, String eDate);
}
