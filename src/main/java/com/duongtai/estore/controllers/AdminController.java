package com.duongtai.estore.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import static com.duongtai.estore.configs.MyUserDetail.getUsernameLogin;

import java.util.Collection;
import java.util.Collections;import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import javax.websocket.server.PathParam;

import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.duongtai.estore.configs.CustomAccessDeniedHandler;
import com.duongtai.estore.configs.Snippets;
import com.duongtai.estore.entities.Category;
import com.duongtai.estore.entities.ConvertEntity;
import com.duongtai.estore.entities.ResponseObject;
import com.duongtai.estore.entities.User;
import com.duongtai.estore.entities.UserDTO;
import com.duongtai.estore.entities.Vendor;
import com.duongtai.estore.services.impl.CategoryServiceImpl;
import com.duongtai.estore.services.impl.OrderServiceImpl;
import com.duongtai.estore.services.impl.ProductServiceImpl;
import com.duongtai.estore.services.impl.StorageServiceImpl;
import com.duongtai.estore.services.impl.UserServiceImpl;
import com.duongtai.estore.services.impl.VendorServiceImpl;

@Controller
@RequestMapping("/master")
public class AdminController {
	private static final Logger LOG = LoggerFactory.getLogger(AdminController.class);
	
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
			@PathParam("username") String username,
			@PathParam("cate_id") String cate_id,
			@PathParam("vendor_id") String vendor_id) { 
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
			if(vendor_id != null) {
				model.addAttribute("edit_vendor", vendorService.findVendorById(Long.parseLong(vendor_id)));
			}else {
				model.addAttribute("edit_vendor", vendorService.findAllVendor().size() > 0 
						? vendorService.findAllVendor().get(0)
						: new Vendor());
			}
			model.addAttribute("vendors", vendorService.findAllVendor());
			break;
		case "categories":
			if(cate_id != null) {
			model.addAttribute("edit_category", categoryService.findCategoryById(Long.parseLong(cate_id)));
			}else {
			model.addAttribute("edit_category", categoryService.findAllCategory().size() > 0 
					? categoryService.findAllCategory().get(0) 
							: new Category());
			}
			List<Category> categories = categoryService.findAllCategory();
			categories.sort((c1, c2) -> c1.getLast_edited().compareToIgnoreCase(c2.getLast_edited()));
			Collections.reverse(categories);
			model.addAttribute("categories", categories);
			break;
		default:
			break;
		}
		if(username == null) {
			username = getUsernameLogin();
		}
		
		model.addAttribute("title", "Admin - Home");
		model.addAttribute("category", new Category());
		model.addAttribute("vendor", new Vendor());
		model.addAttribute("user_details", userService.findByUsername(username));
		model.addAttribute("content", content);
		return new ModelAndView("master/views/index",model);
	}
	
	

    @PostMapping("edit/{username}")
    public ModelAndView editByUsername(ModelMap model, @PathVariable String username, @ModelAttribute User user){
    	LOG.info("ADMIN: "+getUsernameLogin() + " have changed infor of user: "+username );
        user.setUsername(username);
        if(userService.findByUsername(username) != null) {
        	UserDTO userDTO = ConvertEntity.convertToDTO(userService.editByUsername(user));
        	model.addAttribute("user_details", userDTO);
        	return new ModelAndView("redirect:/master?content=users&username="+username,model);
        }
        return new ModelAndView("redirect:/master?content=users&username="+username);
    }

    @GetMapping("/images/{username}")
    public ResponseEntity<byte[]> getUserImage (@PathVariable String username){
        return storageService.readProfileImage(username);
    }
    
    
    @PostMapping("save_cate")
    public ModelAndView saveCategory(ModelMap model, @ModelAttribute Category category) {
    	LOG.info("ADMIN: "+getUsernameLogin() + " added a categories "+category.getName());
    	if(category != null) {
    		categoryService.saveCategory(category);
    	}
    	return new ModelAndView("redirect:/master?content=categories");
    }
    
	@PostMapping("edit_cate/{cate_id}")
    public ModelAndView editCateById(
    		ModelMap model, 
    		@PathVariable String cate_id, 
    		@ModelAttribute Category category){
    		if(categoryService.findCategoryById(Long.parseLong(cate_id)) != null) {
        	
        		category.setId(Long.parseLong(cate_id));
        		categoryService.editCategoryById(category);
        		return new ModelAndView("redirect:/master?content=categories&cate_id="+cate_id);
        	}
		
    	return new ModelAndView("redirect:/master?content=categories&cate_id="+cate_id);
    }
    
 
    
    @PostMapping("delete_cate/{cate_id}")
    public ModelAndView deleteCate(ModelMap model,
    		@PathVariable String cate_id) {
    	if(categoryService.findCategoryById(Long.parseLong(cate_id)) != null) {
    		categoryService.deleteCategoryById(Long.parseLong(cate_id));
    	}
    	return new ModelAndView("redirect:/master?content=categories");	
    }
    
    @PostMapping("uploadImage")
    public ResponseEntity<ResponseObject> uploadImage(@RequestParam("img") MultipartFile file){
    	String filename = storageService.storeFile(file, "noname");
		if(filename != null) {
			return ResponseEntity.status(HttpStatus.OK).body(
	    			new ResponseObject(Snippets.SUCCESS, Snippets.UPLOAD_IMAGE_SUCCESS, filename)
	    			);
		}
		return ResponseEntity.status(HttpStatus.OK).body(
    			new ResponseObject(Snippets.FAILED, Snippets.STORE_FILE_FAILED, null)
    			);

    }
    
    @PostMapping("add_vendor")
    public ModelAndView add_vendor(ModelMap model, @ModelAttribute Vendor vendor) {
    	System.out.println("IMAGE : "+vendor.getImage());
    	LOG.info("ADMIN: "+getUsernameLogin() + " added a categories "+vendor.getName());
    	if(vendor != null) {
    		vendorService.saveVendor(vendor);
    	}
    	return new ModelAndView("redirect:/master?content=vendors");
    }
}
