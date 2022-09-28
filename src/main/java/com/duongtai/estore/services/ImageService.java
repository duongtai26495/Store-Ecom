package com.duongtai.estore.services;

import com.duongtai.estore.entities.Image;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImageService {
    String saveImageWithName(String name);
    
    List<Image> getAllImage();

    void deleteImageById(Long id);

}
