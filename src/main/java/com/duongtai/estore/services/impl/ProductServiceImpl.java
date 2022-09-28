package com.duongtai.estore.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duongtai.estore.configs.Snippets;
import com.duongtai.estore.entities.Category;
import com.duongtai.estore.entities.Product;
import com.duongtai.estore.entities.Vendor;
import com.duongtai.estore.repositories.ProductRepository;
import com.duongtai.estore.services.ProductService;
import static com.duongtai.estore.configs.MyUserDetail.getUsernameLogin;
@Service
public class ProductServiceImpl implements ProductService {

	@Autowired 
	private ProductRepository productRepository;
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@Override
	public Product saveProduct(Product product) {
		if(!isExistByName(product.getName())) {
			
			Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
	        product.setCreated_at(sdf.format(date));
	        product.setLast_edited(sdf.format(date));
	        product.setAdded_by(getUsernameLogin());
			productRepository.save(product);
			return product;
		}
		return null;
	}

	@Override
	public Product editProductById(Product product) {
		Product p_found = productRepository.findById(product.getId()).get();
		if(isExistByName(product.getName()) && !product.getName().toLowerCase().strip()
							.equals(p_found.getName().toLowerCase().strip())) {
			return null;
		}
		if(product.getName().toLowerCase().strip()
									.equals(p_found.getName().toLowerCase().strip())) {
			
				Date date = new Date();
		        SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
				product.setLast_edited(sdf.format(date));
				productRepository.save(product);
				return product;
			
		}
		
		return saveProduct(product);
	}

	@Override
	public void deleteProductById(Long id) {
		if(productRepository.existsById(id)) {
			productRepository.deleteById(id);
		}
	}

	@Override
	public List<Product> findProductByName(String name) {
		List<Product> products = productRepository.findAll();
		List<Product> products_found = new ArrayList<>();
			for(Product product : products) {
				if(product.getName().toLowerCase().strip()
						.contains(name.toLowerCase().strip())) {
					products_found.add(product);
				}
			}
			return products_found;
	}

	@Override
	public List<Product> findProductByCategory(Category category) {
		List<Product> products = productRepository.findAll();
		List<Product> products_found = new ArrayList<>();
		for(Product product : products) {
			if(product.getCategory().equals(category)) {
				products_found.add(product);
			}
		}
		return products_found;
	}

	@Override
	public List<Product> findProductByVendor(Vendor vendor) {
		List<Product> products = productRepository.findAll();
		List<Product> products_found = new ArrayList<>();
		for(Product product : products) {
			if(product.getVendor().equals(vendor)) {
				products_found.add(product);
			}
		}
		return products_found;
	}

	@Override
	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public Product findProductById(Long id) {
		return productRepository.findById(id).get();
	}

	@Override
	public boolean isExistByName(String name) {
		List<Product> products = productRepository.findAll();
		for(Product product : products) {
			if(product.getName().toLowerCase().strip()
				.equals(name.toLowerCase().strip())) {
				return true;
			}
		}
		return false;
	}

}
