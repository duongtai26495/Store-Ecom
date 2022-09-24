package com.duongtai.estore.services;

import java.util.List;

import com.duongtai.estore.entities.Category;

public interface CategoryService {

	Category saveCategory(Category category);
	
	Category editCategoryById(Category category);
	
	void deleteCategoryById(Category category);
	
	List<Category> findAllCategory();
	
	List<Category> findCategoryByName(String name);
	
	List<Category> findCategoryById(Long id);
	
	
}
