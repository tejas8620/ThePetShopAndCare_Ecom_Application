package com.ecom.service;

import java.util.List;

import com.ecom.model.Category;

public interface CategoryService {
	
	public Category saveCategory(Category category);
	
	public Boolean existCategory(String name);
	
	public List<com.ecom.model.Category> getAllCategory();
	
	public Boolean deleteCategory(Integer id);
	
	public Category getCategoryById(Integer id);
	
	public List<Category> getAllActiveCategory();

}
