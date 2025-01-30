package com.ecom.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
	
	
	public String getDateTime();
	
	public void removeSessionMessage();
	
	public void copyImageFolder(String folderPath, MultipartFile file) throws IOException;
	
	
	
}
