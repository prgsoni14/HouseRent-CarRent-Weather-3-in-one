package com.production.hrow.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.production.hrow.models.House;
import com.production.hrow.models.Image;
import com.production.hrow.repos.ImageRepo;

@Service
public class ImageServiceImpl {
    
    @Autowired
    ImageRepo imageRepo;

    String folderPath = "C:\\Users\\Lenovo\\Desktop\\Production\\HomeRent-Ola-Weather\\hrow\\images";
    public Image uploadImage(MultipartFile img, String username)
    {
        String filePath = folderPath + "/" + username + "-" + img.getOriginalFilename();
        Image image = new Image();
        image.setName(img.getOriginalFilename());
        image.setType(img.getContentType());
        image.setPath(filePath);

        try {
            img.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return imageRepo.save(image);
    }

    public Image uploadImageForHouse(MultipartFile img, String username, House house)
    {
        String filePath = folderPath + "/" + username + "-" + img.getOriginalFilename();
        Image image = new Image();
        image.setName(img.getOriginalFilename());
        image.setType(img.getContentType());
        image.setPath(filePath);
        image.setHouse(house);

        try {
            img.transferTo(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
        return imageRepo.save(image);
    }

    

    
    public byte[] downloadImage(Long id)
    {
        Image cur_img = imageRepo.findById(id).orElse(null);
        if(cur_img!=null)
        {
            String filePath = cur_img.getPath();
            try {
                byte[] image = Files.readAllBytes(new File(filePath).toPath());
                return image;
            } catch (IOException e) {
                e.printStackTrace();
                return null;
            }
        }
        else
        {
            return null;
        }
    }

}
