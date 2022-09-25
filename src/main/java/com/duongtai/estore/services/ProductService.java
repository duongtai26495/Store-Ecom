package com.duongtai.estore.services;

import java.util.List;

import com.duongtai.estore.entities.Category;
import com.duongtai.estore.entities.Product;
import com.duongtai.estore.entities.Vendor;

public interface ProductService {
	
	Product saveProduct(Product product);
	
	Product editProductById(Product product);
	
	void deleteProductById(Long id);
	
	List<Product> findProductByName(String name);
	
	List<Product> findProductByCategory(Category category);

	List<Product> findProductByVendor(Vendor vendor);
	
	List<Product> findAllProduct();
	
	Product findProductById(Long id);
	
	boolean isExistByName(String name);
}
