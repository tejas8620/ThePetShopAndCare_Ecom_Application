package com.ecom.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

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
	
	@Override
	public Boolean deleteProduct(Integer id) {
		
		Product product = productRepo.findById(id).orElse(null);
		
		if(! ObjectUtils.isEmpty(product)) {
			productRepo.deleteById(id);
			return true;
		}
		
		return false;
	}

}
