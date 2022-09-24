package com.duongtai.estore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duongtai.estore.entities.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long>{

}
