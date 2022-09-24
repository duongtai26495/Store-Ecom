package com.duongtai.estore.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.duongtai.estore.entities.Order;

@Repository
public interface OrderRepository extends JpaRepository<Order, Long>{

}
