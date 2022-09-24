package com.duongtai.estore.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/master")
public class AdminController {

	
	@GetMapping("")
	public ModelAndView home(ModelMap model) {
		model.addAttribute("title", "Admin - Home");
		model.addAttribute("nav", "home");
		return new ModelAndView("master/views/index",model);
	}
}
