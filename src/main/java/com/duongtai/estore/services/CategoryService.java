package com.duongtai.estore.services;

import java.util.List;

import com.duongtai.estore.entities.Category;

public interface CategoryService {

	Category saveCategory(Category category);
	
	Category editCategoryById(Category category);
	
	void deleteCategoryById(Long id);
	
	List<Category> findAllCategory();
	
	List<Category> findCategoryByName(String name);
	
	Category findCategoryById(Long id);
	
	boolean isExistByName(String name);
	
	
}
