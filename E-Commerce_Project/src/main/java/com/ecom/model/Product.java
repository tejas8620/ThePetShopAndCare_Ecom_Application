package com.ecom.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Product {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(length = 500)
	private String title;
	
	@Column(length = 5000)
	private String description;
	
	private String category;
	
	private String image;
	
	private int stock;
	
	private Double price;
	
	private String updatedTime;
	

}
