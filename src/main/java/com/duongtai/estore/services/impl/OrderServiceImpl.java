package com.duongtai.estore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;

import com.duongtai.estore.entities.Order;
import com.duongtai.estore.repositories.OrderRepository;
import com.duongtai.estore.services.OrderService;

public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	
	@Override
	public Order saveOrder(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Order editOrderById(Order order) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void deleteOrderById(Long id) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public Order findOrderById(Long id) {
		// TODO Auto-generated method stub
		return null;
	}

}
