package com.ecom.controller;



import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ObjectUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.model.Category;
import com.ecom.model.Product;
import com.ecom.service.CategoryService;
import com.ecom.service.CommonService;
import com.ecom.service.ProductService;

import jakarta.servlet.http.HttpSession;
import jakarta.websocket.Session;
import jakarta.websocket.server.PathParam;

@Controller
@RequestMapping("/admin")
public class adminController {
	
	
	@Autowired
	private CategoryService categoryService;
	
	@Autowired
	private CommonService commonService;
	
	@Autowired
	private ProductService productService;
	

	@GetMapping("/")
	public String admin() {
		return"admin/index";
	}
	
	
	@GetMapping("/loadAddProduct")
	public String loadAddProduct(Model m) {
		List<Category> allCategories = categoryService.getAllCategory();
		m.addAttribute("allCategories", allCategories);
		return"admin/add_product";
	}
	
	
	@GetMapping("/category")
	public String addCategory(Model m) {
		m.addAttribute("categories", categoryService.getAllCategory());
		return "admin/category";
	}
	
	
	@PostMapping("/saveCategory")
	public String saveCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
		
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
				commonService.copyCategoryImage(file);
				
				session.setAttribute("successMsg", "Saved Successfully");
			}
		}
		
		return "redirect:/admin/category";
	}
	
	
	@GetMapping("/deleteCategory/{id}")
	public String deleteCategory(@PathVariable int id, HttpSession session) {
		Boolean deleteCategory = categoryService.deleteCategory(id);
		
		if(deleteCategory) {
			session.setAttribute("succMsg", "Category delete Successfully");
		}else {
			session.setAttribute("errMsg", "Something went wrong! plz try later..");
		}
		
		return "redirect:/admin/category";
	}
	
	
	@GetMapping("/loadEditCategory/{id}")
	public String loadEditCategory(@PathVariable int id, Model m) {
		m.addAttribute("category", categoryService.getCategoryById(id));
		return "admin/edit_category";
	}
	
	
	@PostMapping("/updateCategory")
	public String updateCategory(@ModelAttribute Category category, @RequestParam("file") MultipartFile file, HttpSession session) throws IOException {
		
		Category oldCategory = categoryService.getCategoryById(category.getId());
		String imageName = file.isEmpty() ? oldCategory.getImageName(): file.getOriginalFilename();
		
		if(!ObjectUtils.isEmpty(category)) {
			
			oldCategory.setName(category.getName());
			oldCategory.setIsActive(category.getIsActive());
			oldCategory.setImageName(imageName);
			oldCategory.setUpdatedTime(commonService.getDateTime());
		}
		
		Category updateCategory = categoryService.saveCategory(oldCategory);
		
		if(!ObjectUtils.isEmpty(updateCategory)) {
			
			commonService.copyCategoryImage(file);
			
			session.setAttribute("successMsg", "Category Update Successfully");
		}
		else {
			session.setAttribute("errorMsg", "Something wrong on server.!");
		}
		
		return "redirect:/admin/loadEditCategory/"+ category.getId();
	}
	
	
	@PostMapping("/saveProduct")
	public String saveProduct(@ModelAttribute Product product, @RequestParam("file") MultipartFile image, HttpSession session) throws IOException {
		
		String imageName = image.isEmpty() ? "default.jpg" : image.getOriginalFilename();
		product.setImage(imageName);
		product.setUpdatedTime(commonService.getDateTime());
		
		Product saveProduct = productService.saveProduct(product);
		
		if(!ObjectUtils.isEmpty(saveProduct)) {
			
			commonService.copyProductImage(image);
			
			session.setAttribute("successMsg", "Product Saved Successfully..!");
		}
		else {
			session.setAttribute("errorMsg", "Something wrong on server..!");
		}
		
		return "redirect:/admin/loadAddProduct";
	}
	
	
}
