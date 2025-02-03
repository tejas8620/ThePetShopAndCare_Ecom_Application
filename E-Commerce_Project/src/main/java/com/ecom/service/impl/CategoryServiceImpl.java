package com.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import com.ecom.model.Category;
import com.ecom.repo.CategoryRepository;
import com.ecom.service.CategoryService;

@Service
public class CategoryServiceImpl implements CategoryService {

	@Autowired
	private CategoryRepository categoryRepo;

	@Override
	public List<Category> getAllCategory() {
		return categoryRepo.findAll();
	}

	@Override
	public Category saveCategory(Category category) {
		return categoryRepo.save(category);
	}

	@Override
	public Boolean existCategory(String name) {
		return categoryRepo.existsByName(name);
	}

	@Override
	public Boolean deleteCategory(Integer id) {

		Category category = categoryRepo.findById(id).orElse(null);

		if (!ObjectUtils.isEmpty(category)) {
			categoryRepo.delete(category);
			return true;
		}
		return false;
	}

	@Override
	public Category getCategoryById(Integer id) {
		Category category = categoryRepo.findById(id).orElse(null);
		return category;
	}

	@Override
	public List<Category> getAllActiveCategory() {

		List<Category> activeCategories = categoryRepo.findByIsActiveTrue();

		return activeCategories;
	}

}
