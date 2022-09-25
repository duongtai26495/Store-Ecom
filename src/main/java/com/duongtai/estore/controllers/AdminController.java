package com.duongtai.estore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import static com.duongtai.estore.configs.MyUserDetail.getUsernameLogin;

import javax.websocket.server.PathParam;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.duongtai.estore.services.impl.CategoryServiceImpl;
import com.duongtai.estore.services.impl.OrderServiceImpl;
import com.duongtai.estore.services.impl.ProductServiceImpl;
import com.duongtai.estore.services.impl.StorageServiceImpl;
import com.duongtai.estore.services.impl.UserServiceImpl;
import com.duongtai.estore.services.impl.VendorServiceImpl;

@Controller
@RequestMapping("/master")
public class AdminController {

	@Autowired
	private UserServiceImpl userService;
	
	@Autowired 
	private OrderServiceImpl orderService;
	
	@Autowired
	private CategoryServiceImpl categoryService;
	
	@Autowired
	private VendorServiceImpl vendorService;
	
	@Autowired
	private ProductServiceImpl productService;
	
	@Autowired
	private StorageServiceImpl storageService;
	
	@GetMapping("")
	public ModelAndView home(ModelMap model, 
			@PathParam("content") String content,
			@PathParam("username") String username) { 
		model.addAttribute("title", "Admin - Home");
		if(content == null) {
			content = "dashboard";
		}
		switch (content) {
		case "dashboard":
			model.addAttribute("orders",orderService.findAllOrder());	
			break;
		case "products":
			model.addAttribute("products", productService.findAllProduct());	
			break;
		case "users":
			model.addAttribute("users", userService.findAllUser());	
			break;
		case "vendors":
			model.addAttribute("vendors", vendorService.findAllVendor());
			break;
		case "categories:":
			model.addAttribute("categories", categoryService.findAllCategory());
			break;
		default:
			break;
		}
		if(username == null) {
			username = getUsernameLogin();
		}
		model.addAttribute("user_details", userService.findByUsername(username));
		model.addAttribute("content", content);
		return new ModelAndView("master/views/index",model);
	}
	

    @GetMapping("/images/{username}")
    public ResponseEntity<byte[]> getUserImage (@PathVariable String username){
        return storageService.readProfileImage(username);
    }
}
