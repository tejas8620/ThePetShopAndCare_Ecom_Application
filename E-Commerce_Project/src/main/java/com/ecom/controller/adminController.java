package com.ecom.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.model.Category;
import com.ecom.service.CategoryService;
import com.ecom.service.CommonService;

import jakarta.servlet.http.HttpSession;

@Controller
@RequestMapping("/admin")
public class adminController {
	
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CommonService commonService;
	

	@GetMapping("/")
	public String admin() {
		return"admin/index";
	}
	
	
	@GetMapping("/loadAddProduct")
	public String loadAddProduct() {
		return"admin/add_product";
	}
	
	
	@GetMapping("/category")
	public String addCategory() {
		return "admin/category";
	}
	
	
	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file, HttpSession session) {
		
		String imageName= file!= null ? file.getOriginalFilename() : "default.jpg";
		category.setImageName(imageName);
		category.setUpdatedTime(commonService.getDateTime());
		
		Boolean existCategory = categoryService.existCategory(category.getName());
		
		if(existCategory) {
			session.setAttribute("errorMsg", "Category name already exist..");
		}
		else {
			Category saveCategory = categoryService.saveCategory(category);
			
			if(ObjectUtils.isEmpty(saveCategory)) {
				session.setAttribute("errorMsg", "Not saved! internal server error..");
			}
			else {
				session.setAttribute("successMsg", "Saved Successfully");
			}
		}
		
		return "redirect:/admin/category";
	}
	
}
