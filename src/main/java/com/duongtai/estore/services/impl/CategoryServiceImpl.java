package com.duongtai.estore.services.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duongtai.estore.configs.Snippets;
import com.duongtai.estore.controllers.AdminController;
import com.duongtai.estore.entities.Category;
import com.duongtai.estore.repositories.CategoryRepository;
import com.duongtai.estore.services.CategoryService;
import static com.duongtai.estore.configs.MyUserDetail.getUsernameLogin;
@Service
public class CategoryServiceImpl implements CategoryService{

	private static final Logger LOG = LoggerFactory.getLogger(CategoryServiceImpl.class);
	
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
			categoryRepository.save(category);
			LOG.info(String.format("Admin: '%s' have create new category name '%s' successfully",
					getUsernameLogin(),
					category.getName()));
			return category;
		}
		LOG.info(String.format("Admin: '%s' have create new category name '%s' failed", 
    			getUsernameLogin(),
    			category.getName()));
        return null;
	}

	@Override
	public Category editCategoryById(Category category) {
		
		if(categoryRepository.existsById(category.getId())) {
			Category cate_found = findCategoryById(category.getId());

			if(!category.getImage().isEmpty() && !category.getImage().equals(cate_found.getImage())) {
				cate_found.setImage(category.getImage());
			}
			
			if(!category.getName().isEmpty() 
					&& !category.getName().trim().toLowerCase()
					.equals(cate_found.getName().trim().toLowerCase())) {
		    	
		    	LOG.info(String.format("Admin: '%s' have changed category name from '%s' to '%s'", 
		    			getUsernameLogin(),
		    			cate_found.getName(), 
		    			category.getName()));
		    	
				cate_found.setName(category.getName());
			}
			
			if(!category.getCategory_dtails().isEmpty()) {
			
				LOG.info(String.format("Admin: '%s' have changed category details from '%s' to '%s'", 
		    			getUsernameLogin(),
		    			cate_found.getCategory_dtails(), 
		    			category.getCategory_dtails()));
				
				cate_found.setCategory_dtails(category.getCategory_dtails());
			}
			
			if(category.getImage() != null) {
				LOG.info(String.format("Admin: '%s' have changed category details from '%s' to '%s'", 
		    			getUsernameLogin(),
		    			cate_found.getImage(), 
		    			category.getImage()));
			
			}
			Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
	        category.setLast_edited(sdf.format(date));
			categoryRepository.save(cate_found);
			return cate_found;
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
