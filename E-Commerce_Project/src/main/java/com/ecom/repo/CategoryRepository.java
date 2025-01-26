package com.ecom.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecom.model.Category;


@Repository
public interface CategoryRepository extends JpaRepository<Category, Integer> {

	
	public Boolean existsByName(String name);
	
}
