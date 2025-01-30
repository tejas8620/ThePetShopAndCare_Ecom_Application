package com.ecom.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.web.multipart.MultipartFile;

import com.ecom.model.Product;
import com.ecom.repo.ProductRepository;
import com.ecom.service.CommonService;
import com.ecom.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductRepository productRepo;

	@Autowired
	private CommonService commonService;

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

		if (!ObjectUtils.isEmpty(product)) {
			productRepo.deleteById(id);
			return true;
		}

		return false;
	}

	@Override
	public Product getProductById(Integer id) {

		Product product = productRepo.findById(id).orElse(null);

		return product;
	}

	@Override
	public Product updateProduct(Product product, MultipartFile image) {

		Product oldProduct = getProductById(product.getId());

		String imageName = image.isEmpty() ? oldProduct.getImage() : image.getOriginalFilename();

		oldProduct.setTitle(product.getTitle());
		oldProduct.setDescription(product.getDescription());
		oldProduct.setCategory(product.getCategory());
		oldProduct.setPrice(product.getPrice());
		oldProduct.setStock(product.getStock());
		oldProduct.setUpdatedTime(commonService.getDateTime());
		oldProduct.setImage(imageName);

		Product updateProduct = productRepo.save(oldProduct);

		if (!ObjectUtils.isEmpty(updateProduct)) {

			if (!image.isEmpty()) {

				try {
					commonService.copyImageFolder("product_img", image);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			
			return updateProduct;
		}

		return null;
	}

}
