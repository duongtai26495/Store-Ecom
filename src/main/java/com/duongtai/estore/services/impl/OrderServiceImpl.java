package com.duongtai.estore.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.duongtai.estore.configs.Snippets;
import com.duongtai.estore.entities.Order;
import com.duongtai.estore.repositories.OrderRepository;
import com.duongtai.estore.services.OrderService;
import static com.duongtai.estore.configs.MyUserDetail.getUsernameLogin;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{

	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private UserServiceImpl userService;
	
	@Override
	public Order saveOrder(Order order) {
		order.setCustomer(userService.findByUsername(getUsernameLogin()));
		Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
		order.setCreated_at(sdf.format(date));
		return orderRepository.save(order);
	}

	@Override
	public Order editOrderById(Order order) {
		if(orderRepository.existsById(order.getId())) {
			Date date = new Date();
	        SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
			order.setLast_edited_at(sdf.format(date));
		}
		return orderRepository.save(order);
	}

	@Override
	public void deleteOrderById(Long id) {
		if(orderRepository.existsById(id)) {
			orderRepository.deleteById(id);
		}
	}

	@Override
	public Order findOrderById(Long id) {
		if(orderRepository.existsById(id)) {
			return orderRepository.findById(id).get();
		}
		return null;
	}

	@Override
	public List<Order> findAllOrder() {
		return orderRepository.findAll();
	}

}
