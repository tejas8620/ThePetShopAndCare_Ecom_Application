package com.ecom.service.impl;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

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
	
	
	//This method is used to remove session attribute (successMsg, errorMsg) while category submitting
	@Override
	public void removeSessionMessage() {
		
		HttpServletRequest request = ((ServletRequestAttributes)(RequestContextHolder.getRequestAttributes())).getRequest();
		HttpSession session = request.getSession();
		session.removeAttribute("successMsg");
		session.removeAttribute("errorMsg");
		session.removeAttribute("succMsg");
		session.removeAttribute("errMsg");
		
	}

}
