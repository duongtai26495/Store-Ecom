package com.duongtai.estore.services;

import com.duongtai.estore.entities.Order;
import com.duongtai.estore.entities.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

public interface UserService {

    User findByUsername(String username);

    User findByEmail(String email);

    User getUserByUsername(String username);

    User saveUser(User user);

    User getById(Long id);

    User editByUsername(User user);

    boolean updatePassword(String newPassword);

    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;

	List<Order> findOrderByUsername(String username);
	
	List<User> findAllUser();
}
