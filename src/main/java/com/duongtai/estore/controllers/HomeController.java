package com.duongtai.estore.controllers;

import com.duongtai.estore.entities.User;
import com.duongtai.estore.services.impl.StorageServiceImpl;
import com.duongtai.estore.services.impl.UserServiceImpl;

import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/")
public class HomeController {

    @Autowired
    private StorageServiceImpl storageService;
    
    @Autowired
    private UserServiceImpl userService;

    @GetMapping("images/{fileName:.+}")
    public ResponseEntity<byte[]> readFile (@PathVariable String fileName){
        return storageService.readFile(fileName);
    }

    @GetMapping("")
    public ModelAndView homepage(ModelMap model) {
    	model.addAttribute("title", "Home Page - E-Store");
    	model.addAttribute("nav", "home");
    	return new ModelAndView("home", model);
    }
    
    @GetMapping("login")
    public ModelAndView login(ModelMap model, @PathParam("register") String register) {
    	model.addAttribute("register", register);
    	model.addAttribute("title", "Login - E-Store");
    	return new ModelAndView("user/views/login", model);
    }
    @GetMapping("register")
    public ModelAndView register(ModelMap model, User user) {
    	model.addAttribute("user", user);
    	model.addAttribute("title", "Register - E-Store");
    	return new ModelAndView("user/views/register", model);
    }
    
    @PostMapping("create_user")
    public String createUser(ModelMap model, @ModelAttribute User user) {
    	if(userService.saveUser(user)!=null) {
    		return ("redirect:/login?register=success") ;
    	}
    	model.addAttribute("register","duplicate");
    	model.addAttribute("user",user);
    	return "register" ;
    } 
}
