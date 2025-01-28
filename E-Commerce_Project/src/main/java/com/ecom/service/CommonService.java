package com.ecom.service;

import java.io.IOException;

import org.springframework.web.multipart.MultipartFile;

public interface CommonService {
	
	
	public String getDateTime();
	
	public void removeSessionMessage();
	
	public void copyCategoryImage(MultipartFile file) throws IOException;

	public void copyProductImage(MultipartFile image) throws IOException;
	
	
	
}
