package com.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecom.model.Product;
import com.ecom.repo.ProductRepository;
import com.ecom.service.ProductService;


@Service
public class ProductServiceImpl implements ProductService{
	
	
	@Autowired
	private ProductRepository productRepo;
	
	
	@Override
	public Product saveProduct(Product product) {
		return productRepo.save(product);
	}
	
	
	@Override
	public List<Product> getAllProducts() {
		return productRepo.findAll();
	}

}
