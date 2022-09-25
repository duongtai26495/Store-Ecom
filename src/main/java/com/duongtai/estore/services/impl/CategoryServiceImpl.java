package com.duongtai.estore.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.duongtai.estore.configs.Snippets;
import com.duongtai.estore.entities.Category;
import com.duongtai.estore.repositories.CategoryRepository;
import com.duongtai.estore.services.CategoryService;
import static com.duongtai.estore.configs.MyUserDetail.getUsernameLogin;

public class CategoryServiceImpl implements CategoryService{

	@Autowired
	private CategoryRepository categoryRepository;
	
	@Override
	public Category saveCategory(Category category) {
		if(!isExistByName(category.getName())) {
			Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
	        category.setCreated_at(sdf.format(date));
	        category.setLast_edited(sdf.format(date));
	        category.setCreated_by(getUsernameLogin());
	        if(categoryRepository.save(category) != null) {
	        	return category;
	        }	
		}
		
        return null;
	}

	@Override
	public Category editCategoryById(Category category) {
		if(categoryRepository.existsById(category.getId())) {
			Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
	        category.setLast_edited(sdf.format(date));
	        if(categoryRepository.save(category)!=null) {
	        	return category;
	        };
		}
		return saveCategory(category);
	}

	@Override
	public void deleteCategoryById(Long id) {
		if(categoryRepository.existsById(id)){
			categoryRepository.deleteById(id);
		}
	}

	@Override
	public List<Category> findAllCategory() {
		return categoryRepository.findAll();
	}

	@Override
	public List<Category> findCategoryByName(String name) {
		List<Category> categories = categoryRepository.findAll();
		List<Category> cate_found = new ArrayList<>();
		
		for(Category cate : categories) {
			if(cate.getName().toLowerCase().strip()
					.equals(name.toLowerCase().strip())) {
				cate_found.add(cate);
			}
		}
		return cate_found;
	}

	@Override
	public Category findCategoryById(Long id) {
		return categoryRepository.findById(id).get();
	}

	@Override
	public boolean isExistByName(String name) {
		List<Category> categories = categoryRepository.findAll();
		for(Category cate : categories) {
			if(cate.getName().toLowerCase().strip()
				.equals(name.toLowerCase().strip())) {
				return true;
			}
		}
		return false;
	}

}
