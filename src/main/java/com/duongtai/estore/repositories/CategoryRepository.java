package com.duongtai.estore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duongtai.estore.entities.Category;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long>{

}
