package com.production.hrow.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.production.hrow.models.House;
import com.production.hrow.models.Image;
import com.production.hrow.repos.HouseRepo;
import com.production.hrow.services.HouseService;
import com.production.hrow.services.impl.ImageServiceImpl;

@RestController
@CrossOrigin("*")
@RequestMapping("/house")
public class HouseController {

    @Autowired
    HouseService houseService;

    @Autowired
    ImageServiceImpl imageService;

    @Autowired
    HouseRepo houseRepo;
    
    @PostMapping("/addhouse/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<?> addHouse(@RequestPart("house") House house, @RequestParam("images") MultipartFile[] images,@PathVariable("username") String username)
    {
        House houseWtImages = houseService.createHouseByAdmin(username, house);

        List<Image>houseImages = new ArrayList<Image>();
        for(MultipartFile file : images)
        {
           houseImages.add(imageService.uploadImageForHouse(file, username,houseWtImages));
        }
        houseWtImages.setImages(houseImages);
        houseRepo.save(houseWtImages);

        Map<String,Object>response = new HashMap<>();
        response.put("house", houseWtImages);
        response.put("imageBytes", imageService.downloadImage(houseImages.get(0).getId()));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/delete/{username}/{id}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<House> deleteHouse(@PathVariable("username") String username,@PathVariable("id") Long id)
    {
        return ResponseEntity.ok().body(houseService.deleteHouseByAdmin(username, id));
    }

    @PutMapping("/freehouse/{username}/{id}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<House> freeHouse(@PathVariable("username") String username,@PathVariable("id") Long id)
    {
        return ResponseEntity.ok().body(houseService.freeHouseByAdmin(username, id));
    }
    
    
    @GetMapping("/houses/{username}")
    @PreAuthorize("#username == authentication.principal.username")
    public ResponseEntity<?> housesByAdmin(@PathVariable("username") String username)
    {
        Map<String,Object>response = new HashMap<>();
        List<House>houses = houseService.getHousesByAdmin(username);
        response.put("houses", houses);
        List<byte[]> imagesByteArray = new ArrayList<>();
        for(House house : houses)
        {   
            imagesByteArray.add(imageService.downloadImage(house.getImages().get(0).getId()));
        }
        response.put("images", imagesByteArray);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/houses")
    public ResponseEntity<?> allHouses()
    {
        Map<String,Object>response = new HashMap<>();
        List<House>houses = houseService.getAllHouses();
        response.put("houses", houses);
        List<byte[]> imagesByteArray = new ArrayList<>();
        for(House house : houses)
        {   
            imagesByteArray.add(imageService.downloadImage(house.getImages().get(0).getId()));
        }
        response.put("images", imagesByteArray);
        return ResponseEntity.ok().body(response);
    }


    @GetMapping("/oneHouse/{id}")
    public ResponseEntity<?> oneHouse(@PathVariable("id") Long id)
    {
        Map<String,Object>response = new HashMap<>();
        House house = houseService.getOneHouse(id);
        response.put("house", house);
        List<byte[]> imagesByteArray = new ArrayList<>();
        for(Image image : house.getImages())
        {
            imagesByteArray.add(imageService.downloadImage(image.getId()));
        }
        response.put("images", imagesByteArray);
        return ResponseEntity.ok().body(response);
    }

    

    @GetMapping("/houses/available")
    public ResponseEntity<?> allFreeHouses()
    {
        Map<String,Object>response = new HashMap<>();
        List<House>houses = houseService.getFreeHouses();
        response.put("houses", houses);
        List<byte[]> imagesByteArray = new ArrayList<>();
        for(House house : houses)
        {   
            imagesByteArray.add(imageService.downloadImage(house.getImages().get(0).getId()));
        }
        response.put("images", imagesByteArray);
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/houses/city/{city}")
    public ResponseEntity<?> allHousesByCity(@PathVariable("city") String city)
    {
        Map<String,Object>response = new HashMap<>();
        List<House>houses = houseService.getHousesByCity(city);
        response.put("houses", houses);
        List<byte[]> imagesByteArray = new ArrayList<>();
        for(House house : houses)
        {   
            imagesByteArray.add(imageService.downloadImage(house.getImages().get(0).getId()));
        }
        response.put("images", imagesByteArray);
        return ResponseEntity.ok().body(response);
    }

    

}
