package com.duongtai.estore.services.impl;

import com.duongtai.estore.configs.Snippets;
import com.duongtai.estore.entities.Image;
import com.duongtai.estore.repositories.ImageRepository;
import com.duongtai.estore.services.ImageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import static com.duongtai.estore.configs.MyUserDetail.getUsernameLogin;

@Service
public class ImageServiceImpl implements ImageService {

    @Autowired
    private ImageRepository imageRepository;
    
    
    @Override
    public List<Image> getAllImage() {
        return imageRepository.findAll();
    }

    @Override
    public void deleteImageById(Long id) {
        if(imageRepository.existsById(id)){
            imageRepository.deleteById(id);
        }
    }



	@Override
	public String saveImageWithName(String name) {
		  if(name != null){
	            Image image = new Image();
	            Date date = new Date();
	            SimpleDateFormat sdf = new SimpleDateFormat(Snippets.TIME_PATTERN);
	            image.setAdded_at(sdf.format(date));
	            image.setAdded_by(getUsernameLogin());
	            image.setName(name);
	            imageRepository.save(image);
	           return image.getName();
	        }
	        return null;
	}
}
