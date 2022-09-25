package com.duongtai.estore.services;

import java.util.List;

import com.duongtai.estore.entities.Order;

public interface OrderService {

	Order saveOrder(Order order);
	
	Order editOrderById(Order order);
	
	void deleteOrderById(Long id);
	
	Order findOrderById(Long id);
	
	List<Order> findAllOrder();

	
}
