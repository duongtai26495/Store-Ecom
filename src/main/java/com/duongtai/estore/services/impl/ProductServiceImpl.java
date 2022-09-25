package com.duongtai.estore.services.impl;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.duongtai.estore.configs.Snippets;
import com.duongtai.estore.entities.Category;
import com.duongtai.estore.entities.Product;
import com.duongtai.estore.entities.Vendor;
import com.duongtai.estore.repositories.ProductRepository;
import com.duongtai.estore.services.ProductService;
import static com.duongtai.estore.configs.MyUserDetail.getUsernameLogin;

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
	        if(productRepository.save(product) != null) {
	        	return product;
	        }
		}
		return null;
	}

	@Override
	public Product editProductById(Product product) {
		Product p_found = productRepository.findById(product.getId()).get();
		if(p_found!=null && product.getName().toLowerCase().strip()
							.equals(p_found.getName().toLowerCase().strip())) {
			
			Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
			product.setLast_edited(sdf.format(date));
			productRepository.save(product);
			return product;
		}
		return null
	}

	@Override
	public void deleteProductById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public List<Product> findProductByName(String name) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductByCategory(Category category) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findProductByVendor(Vendor vendor) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Product> findAllProduct() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Product findProductById(Long id) {
		// TODO Auto-generated method stub
		return null;
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
