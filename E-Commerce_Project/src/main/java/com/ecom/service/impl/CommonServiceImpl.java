package com.ecom.service.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.service.CommonService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;

@Service
public class CommonServiceImpl implements CommonService {

	@Override
	public String getDateTime() {

		LocalDateTime now = LocalDateTime.now().withNano(0);

		// Define the formatter to exclude fractional seconds
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMM yyyy  HH:mm:ss");

		// Format the LocalDateTime
		String formattedTime = now.format(formatter);

		return formattedTime;
	}

	// This method is used to remove session attribute (successMsg, errorMsg) while
	// category submitting
	@Override
	public void removeSessionMessage() {

		HttpServletRequest request = ((ServletRequestAttributes) (RequestContextHolder.getRequestAttributes()))
				.getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("successMsg");
		session.removeAttribute("errorMsg");
		session.removeAttribute("succMsg");
		session.removeAttribute("errMsg");

	}
	

	@Override
	public void copyCategoryImage(MultipartFile file) throws IOException {
		
		File saveFile = new ClassPathResource("static/img").getFile();
		
		Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+"category_img"+File.separator+file.getOriginalFilename());
		
		System.out.println(path);
		Files.copy(file.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);

	}
	
	
	
	@Override
	public void copyProductImage(MultipartFile image) throws IOException {
		
		File saveFile = new ClassPathResource("static/img").getFile();
		
		Path path = Paths.get(saveFile.getAbsolutePath()+File.separator+"product_img"+File.separator+image.getOriginalFilename());
		
		System.out.println(path);
		Files.copy(image.getInputStream(), path, StandardCopyOption.REPLACE_EXISTING);
		
	}
	
	
	

}
