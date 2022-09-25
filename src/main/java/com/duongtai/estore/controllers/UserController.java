package com.duongtai.estore.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.duongtai.estore.services.impl.StorageServiceImpl;

@Controller
@RequestMapping("/user/")
public class UserController {

	@Autowired
	private StorageServiceImpl storageService;
	
    @GetMapping("images/{username}")
    public ResponseEntity<byte[]> readUserImage (@PathVariable String username){
        return storageService.readProfileImage(username);
    }
	
}
